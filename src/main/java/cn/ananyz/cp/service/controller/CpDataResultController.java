package cn.ananyz.cp.service.controller;

import cn.ananyz.cp.service.config.CpDataResultConfig;
import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import cn.ananyz.cp.service.data.collection.parse.CpApi163;
import cn.ananyz.cp.service.data.collection.parse.impl.CpApi163Impl;
import cn.ananyz.cp.service.enums.GameTypeEnum;
import cn.ananyz.cp.service.model.CpDataResult;
import cn.ananyz.cp.service.model.CpDataSysConfig;
import cn.ananyz.cp.service.service.CpDataResultService;
import cn.ananyz.cp.service.service.CpDataResultViewsService;
import cn.ananyz.cp.service.service.CpDataSysConfigService;
import cn.ananyz.cp.service.utils.DateUtil;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CpDataResultController {
    private static Logger logger = Logger.getLogger(CpDataResultController.class);
    @Autowired
    private CpApi163 cpApi163;
    @Autowired
    private CpDataResultService cpDataResultService;
    @Autowired
    private CpDataResultConfig cpDataResultConfig;

    @Autowired
    private CpDataResultViewsService cpDataResultViewsService;
    @Autowired
    private CpDataSysConfigService cpDataSysConfigService;


    public CpDataResultConfig getCpDataResultConfig() {
        return cpDataResultConfig;
    }


    /**
     * 初始化今天的号码
     * @throws IOException
     * @throws ParseException
     */
    public void initToday() throws IOException, ParseException {

        List<CPDataModel> todayAllData = cpApi163.getTodayAllData(new Date());
        insertCpDatas(todayAllData);

    }

    /**
     * 批量插入cp数据
     */
    public void initAllCpData() throws ParseException, IOException {
        int day = 2;
        Date add = DateUtil.add(new Date(), Calendar.DAY_OF_MONTH, -day);
        for(int i = 0 ; i < day ; i++){
            Date add2 = DateUtil.add(add, Calendar.DAY_OF_MONTH, i+1);
            System.out.println(DateUtil.formatDate(add2,DateUtil.PATTERN_DATE_TIME));
            List<CPDataModel> todayAllData = cpApi163.getTodayAllData(add2);
            insertCpDatas(todayAllData);
        }
    }


    /**
     * 批量排序插入数据并分析
     * @param todayAllData
     * @throws ParseException
     */
    private void insertCpDatas(List<CPDataModel> todayAllData) throws ParseException {

        Collections.sort(todayAllData, new Comparator<CPDataModel>() {
            @Override
            public int compare(CPDataModel o1, CPDataModel o2) {
                return o1.getLongDateAndQiHao().compareTo(o2.getLongDateAndQiHao());
            }
        });

        for(CPDataModel cpDataModel : todayAllData){
            convertCpApiToCpDataResult(cpDataModel);
            List<CpDataResultView> analyzi = cpDataResultService.analyzi(cpDataResultConfig.getListIndex(), cpDataResultConfig.getStart(), cpDataResultConfig.getEnd(), cpDataResultConfig.getDiffNum(),cpDataResultConfig.getOneDayLastQihao());
            cpDataResultViewsService.insert(analyzi);
        }
    }

    /**
     * 获取当前的号码
     * @throws IOException
     * @throws ParseException
     */
    public void selectCruNum() throws IOException, ParseException {
        Date add = DateUtil.add(new Date(), Calendar.MINUTE, -3);

        //获取该日期下的最大数据
        CpDataResult cpDataResult = cpDataResultService.selectByDateLastData(add);

        String cpQihao = null;

        if(cpDataResult != null){
            cpQihao = cpDataResult.getCpQiHao();
        }

        List<CPDataModel> todayLastDatasByQihao = cpApi163.getTodayLastDatasByQihao(add, cpQihao);

        if(todayLastDatasByQihao !=null){
            for (CPDataModel todayLastData : todayLastDatasByQihao) {
                convertCpApiToCpDataResult(todayLastData);
            }
        }
    }

    /**
     * 分析结果号码
     * @throws IOException
     * @throws ParseException
     */
    public void analyz() throws IOException, ParseException {
        List<CpDataResultView> cpDataResultViews = cpDataResultService.analyzi(cpDataResultConfig.getListIndex(), cpDataResultConfig.getStart(), cpDataResultConfig.getEnd(),cpDataResultConfig.getDiffNum(),cpDataResultConfig.getOneDayLastQihao());
        cpDataResultViewsService.insert(cpDataResultViews);

        logger.info("分析出的号....:" + cpDataResultViews);

        /**
         * 更新预警配置
         */
        cpDataSysConfigService.updateMaxCount(GameTypeEnum.GAME_TYPE_ENUM_CQ.getGameType());

        /**
         * 查询预警配置
         */
        CpDataSysConfig cpDataSysConfig = cpDataSysConfigService.selectCpDataSysConfigByGameTypeOne(GameTypeEnum.GAME_TYPE_ENUM_CQ.getGameType());

        List<CpDataResultView> collect = cpDataResultViews.stream().filter(x -> {
            return x.getCishu() >= (cpDataSysConfig.getMaxCount() + cpDataSysConfig.getLgMaxCount() - cpDataSysConfig.getPlanCount());
        }).collect(Collectors.toList());

        logger.info("邮件预警号....:" + collect);

        cpDataResultService.sendMails(collect);
    }



    private void convertCpApiToCpDataResult(CPDataModel cpDataModel) throws ParseException {
        String shortQiHao = cpDataModel.getShortQiHao();
        String longDateAndQiHao = cpDataModel.getLongDateAndQiHao();
        String qiHao = "20" + longDateAndQiHao.substring(0, 6);

        CpDataResult cpDataResult = new CpDataResult();

        cpDataResult.setCreateTime(new Date());
        cpDataResult.setCpDate(DateUtil.formatDate(DateUtil.parseDate(qiHao,DateUtil.PATTERN_DATE_NOT),DateUtil.PATTERN_DATE));
        cpDataResult.setCpQiHao(shortQiHao);

        cpDataResult.setId(null);
        cpDataResult.setCpIndex("1");
        cpDataResult.setCpNum(cpDataModel.getWan() + "");
        cpDataResultService.insert(cpDataResult);

        cpDataResult.setId(null);
        cpDataResult.setCpIndex("2");
        cpDataResult.setCpNum(cpDataModel.getQian() + "");
        cpDataResultService.insert(cpDataResult);

        cpDataResult.setId(null);
        cpDataResult.setCpIndex("3");
        cpDataResult.setCpNum(cpDataModel.getBai() + "");
        cpDataResultService.insert(cpDataResult);

        cpDataResult.setId(null);
        cpDataResult.setCpIndex("4");
        cpDataResult.setCpNum(cpDataModel.getShi() + "");
        cpDataResultService.insert(cpDataResult);

        cpDataResult.setId(null);
        cpDataResult.setCpIndex("5");
        cpDataResult.setCpNum(cpDataModel.getGe() + "");
        cpDataResultService.insert(cpDataResult);
    }


}
