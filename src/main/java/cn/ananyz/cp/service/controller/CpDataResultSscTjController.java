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
    @Autowired
    private CpDataResultSscTjConfig cpDataResultSscTjConfig;

    @Autowired
    private CpDataResultViewsSscTjService cpDataResultViewsSscTjService;

    public CpDataResultSscTjConfig getCpDataResultSscTjConfig() {
        return cpDataResultSscTjConfig;
    }

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
        List<CPDataModel> todayAllData = cpApi500.getTodayAllData(add);
        List<CpDataResultSscTj> cpDataResultSscTjs = convertBatchCpApiToCpDataResult(todayAllData);
        cpDataResultSscTjService.insertBatch(cpDataResultSscTjs);
//        CPDataModel todayLastData = cpApi500.getTodayLastData(add);
//        convertCpApiToCpDataResult(todayLastData);
//        convertBatchCpApiToCpDataResult
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

    public List<CpDataResultSscTj> convertBatchCpApiToCpDataResult(List<CPDataModel> cpDataModel) throws ParseException {
        List<CpDataResultSscTj> cpDataResultSscTjs = new ArrayList<>();
        for(CPDataModel cpDataModel1 : cpDataModel){
            List<CpDataResultSscTj> cpDataResultSscTjs1 = convertCpApiToCpDataResult(cpDataModel1);
            cpDataResultSscTjs.addAll(cpDataResultSscTjs1);
        }

        return cpDataResultSscTjs;
    }


    public List<CpDataResultSscTj> convertCpApiToCpDataResult(CPDataModel cpDataModel) throws ParseException {

        List<CpDataResultSscTj> cpDataResultSscTjs = new ArrayList<>();

        CpDataResultSscTj cpDataResultSscTjWan = new CpDataResultSscTj();

        String shortQiHao = cpDataModel.getShortQiHao();
        String longDateAndQiHao = cpDataModel.getLongDateAndQiHao();
        String qiHao = longDateAndQiHao.substring(0, 8);

        cpDataResultSscTjWan.setCreateTime(new Date());
        cpDataResultSscTjWan.setCpDate(DateUtil.formatDate(DateUtil.parseDate(qiHao,DateUtil.PATTERN_DATE_NOT),DateUtil.PATTERN_DATE));
        cpDataResultSscTjWan.setCpQiHao(shortQiHao);
        cpDataResultSscTjWan.setCpIndex("1");
        cpDataResultSscTjWan.setCpNum(cpDataModel.getWan() + "");

        cpDataResultSscTjs.add(cpDataResultSscTjWan);

        CpDataResultSscTj cpDataResultSscTjQian = new CpDataResultSscTj();
        cpDataResultSscTjQian.setCreateTime(new Date());
        cpDataResultSscTjQian.setCpDate(DateUtil.formatDate(DateUtil.parseDate(qiHao,DateUtil.PATTERN_DATE_NOT),DateUtil.PATTERN_DATE));
        cpDataResultSscTjQian.setCpQiHao(shortQiHao);
        cpDataResultSscTjQian.setCpIndex("2");
        cpDataResultSscTjQian.setCpNum(cpDataModel.getQian() + "");
        cpDataResultSscTjs.add(cpDataResultSscTjQian);

        CpDataResultSscTj cpDataResultSscTjBai = new CpDataResultSscTj();
        cpDataResultSscTjBai.setCreateTime(new Date());
        cpDataResultSscTjBai.setCpDate(DateUtil.formatDate(DateUtil.parseDate(qiHao,DateUtil.PATTERN_DATE_NOT),DateUtil.PATTERN_DATE));
        cpDataResultSscTjBai.setCpQiHao(shortQiHao);
        cpDataResultSscTjBai.setCpIndex("3");
        cpDataResultSscTjBai.setCpNum(cpDataModel.getBai() + "");
        cpDataResultSscTjs.add(cpDataResultSscTjBai);

        CpDataResultSscTj cpDataResultSscTjShi = new CpDataResultSscTj();
        cpDataResultSscTjShi.setCreateTime(new Date());
        cpDataResultSscTjShi.setCpDate(DateUtil.formatDate(DateUtil.parseDate(qiHao,DateUtil.PATTERN_DATE_NOT),DateUtil.PATTERN_DATE));
        cpDataResultSscTjShi.setCpQiHao(shortQiHao);
        cpDataResultSscTjShi.setCpIndex("4");
        cpDataResultSscTjShi.setCpNum(cpDataModel.getShi() + "");
        cpDataResultSscTjs.add(cpDataResultSscTjShi);

        CpDataResultSscTj cpDataResultSscTjGe = new CpDataResultSscTj();
        cpDataResultSscTjGe.setCreateTime(new Date());
        cpDataResultSscTjGe.setCpDate(DateUtil.formatDate(DateUtil.parseDate(qiHao,DateUtil.PATTERN_DATE_NOT),DateUtil.PATTERN_DATE));
        cpDataResultSscTjGe.setCpQiHao(shortQiHao);
        cpDataResultSscTjGe.setCpIndex("5");
        cpDataResultSscTjGe.setCpNum(cpDataModel.getGe() + "");
        cpDataResultSscTjs.add(cpDataResultSscTjGe);

        return cpDataResultSscTjs;
    }


}
