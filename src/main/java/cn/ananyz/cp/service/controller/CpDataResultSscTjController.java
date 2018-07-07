package cn.ananyz.cp.service.controller;

import cn.ananyz.cp.service.config.CpDataResultSscTjConfig;
import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi500;
import cn.ananyz.cp.service.model.CpDataResultSscTj;
import cn.ananyz.cp.service.service.CpDataResultSscTjService;
import cn.ananyz.cp.service.service.CpDataResultViewsSscTjService;
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
public class CpDataResultSscTjController {
    private static Logger logger = Logger.getLogger(CpDataResultSscTjController.class);
    @Autowired
    private CpApi500 cpApi500;
    @Autowired
    private CpDataResultSscTjService cpDataResultSscTjService;
//    @Autowired
    private CpDataResultSscTjConfig cpDataResultSscTjConfig;

    @Autowired
    private CpDataResultViewsSscTjService cpDataResultViewsSscTjService;

    /**
     * 初始化今天的号码
     * @throws IOException
     * @throws ParseException
     */
    public void initToday() throws IOException, ParseException {

        List<CPDataModel> todayAllData = cpApi500.getTodayAllData(new Date());
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
            List<CPDataModel> todayAllData = cpApi500.getTodayAllData(add2);
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
            List<CpDataResultView> analyzi = cpDataResultSscTjService.analyzi(cpDataResultSscTjConfig.getListIndex(), cpDataResultSscTjConfig.getStart(), cpDataResultSscTjConfig.getEnd(), cpDataResultSscTjConfig.getDiffNum(),cpDataResultSscTjConfig.getOneDayLastQihao());
            cpDataResultViewsSscTjService.insert(analyzi);
        }
    }

    /**
     * 获取当前的号码
     * @throws IOException
     * @throws ParseException
     */
    public void selectCruNum() throws IOException, ParseException {
        Date add = DateUtil.add(new Date(), Calendar.MINUTE, -3);
        CPDataModel todayLastData = cpApi500.getTodayLastData(add);
        convertCpApiToCpDataResult(todayLastData);
    }

    /**
     * 分析结果号码
     * @throws IOException
     * @throws ParseException
     */
    public void analyz() throws IOException, ParseException {

        List<CpDataResultView> cpDataResultViews = cpDataResultSscTjService.analyzi(cpDataResultSscTjConfig.getListIndex(), cpDataResultSscTjConfig.getStart(), cpDataResultSscTjConfig.getEnd(),cpDataResultSscTjConfig.getDiffNum(),cpDataResultSscTjConfig.getOneDayLastQihao());
        cpDataResultViewsSscTjService.insert(cpDataResultViews);

        logger.info("天津分析出的号....:" + cpDataResultViews);

        List<CpDataResultView> collect = cpDataResultViews.stream().filter(x -> {
            return x.getCishu() > cpDataResultSscTjConfig.getWarnCount();
        }).collect(Collectors.toList());

        logger.info("天津邮件预警号....:" + collect);

        cpDataResultSscTjService.sendMails(collect);

    }



    private void convertCpApiToCpDataResult(CPDataModel cpDataModel) throws ParseException {
        String shortQiHao = cpDataModel.getShortQiHao();
        String longDateAndQiHao = cpDataModel.getLongDateAndQiHao();
        String qiHao = longDateAndQiHao.substring(0, 8);

        CpDataResultSscTj cpDataResultSscTj = new CpDataResultSscTj();

        cpDataResultSscTj.setCreateTime(new Date());
        cpDataResultSscTj.setCpDate(DateUtil.formatDate(DateUtil.parseDate(qiHao,DateUtil.PATTERN_DATE_NOT),DateUtil.PATTERN_DATE));
        cpDataResultSscTj.setCpQiHao(shortQiHao);

        cpDataResultSscTj.setId(null);
        cpDataResultSscTj.setCpIndex("1");
        cpDataResultSscTj.setCpNum(cpDataModel.getWan() + "");
        cpDataResultSscTjService.insert(cpDataResultSscTj);

        cpDataResultSscTj.setId(null);
        cpDataResultSscTj.setCpIndex("2");
        cpDataResultSscTj.setCpNum(cpDataModel.getQian() + "");
        cpDataResultSscTjService.insert(cpDataResultSscTj);

        cpDataResultSscTj.setId(null);
        cpDataResultSscTj.setCpIndex("3");
        cpDataResultSscTj.setCpNum(cpDataModel.getBai() + "");
        cpDataResultSscTjService.insert(cpDataResultSscTj);

        cpDataResultSscTj.setId(null);
        cpDataResultSscTj.setCpIndex("4");
        cpDataResultSscTj.setCpNum(cpDataModel.getShi() + "");
        cpDataResultSscTjService.insert(cpDataResultSscTj);

        cpDataResultSscTj.setId(null);
        cpDataResultSscTj.setCpIndex("5");
        cpDataResultSscTj.setCpNum(cpDataModel.getGe() + "");
        cpDataResultSscTjService.insert(cpDataResultSscTj);
    }


}
