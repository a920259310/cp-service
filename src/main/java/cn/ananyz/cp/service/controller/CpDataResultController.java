package cn.ananyz.cp.service.controller;

import cn.ananyz.cp.service.config.CpDataResultConfig;
import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import cn.ananyz.cp.service.model.CpDataResult;
import cn.ananyz.cp.service.service.CpDataResultService;
import cn.ananyz.cp.service.utils.DateUtil;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Component
public class CpDataResultController {
    @Autowired
    private CpApi cpApi;
    @Autowired
    private CpDataResultService cpDataResultService;
    @Autowired
    private CpDataResultConfig cpDataResultConfig;

    static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * 初始化今天的号码
     * @throws IOException
     * @throws ParseException
     */
    public void initToday() throws IOException, ParseException {
        List<CPDataModel> todayAllData = cpApi.getTodayAllData(new Date());
        Collections.sort(todayAllData, new Comparator<CPDataModel>() {
            @Override
            public int compare(CPDataModel o1, CPDataModel o2) {
                return o1.getLongDateAndQiHao().compareTo(o2.getLongDateAndQiHao());
            }
        });
        for(CPDataModel cpDataModel : todayAllData){
            convertCpApiToCpDataResult(cpDataModel);
        }
    }

    /**
     * 获取当前的号码
     * @throws IOException
     * @throws ParseException
     */
    public void selectCruNum() throws IOException, ParseException {

        CPDataModel todayLastData = cpApi.getTodayLastData(new Date());
        convertCpApiToCpDataResult(todayLastData);
    }

    /**
     * 分析结果号码
     * @throws IOException
     * @throws ParseException
     */
    public void analyz() throws IOException, ParseException {
        List<CpDataResultView> cpDataResultViews = cpDataResultService.analyzi(cpDataResultConfig.getListIndex(), cpDataResultConfig.getStart(), cpDataResultConfig.getEnd(),cpDataResultConfig.getDiffNum());
        cpDataResultService.sendMails(cpDataResultViews);
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

    public static void main(String[] args) throws IOException, ParseException {
        CpDataResultController bean = classPathXmlApplicationContext.getBean(CpDataResultController.class);

//        bean.initToday();
        CpDataResultService bean1 = classPathXmlApplicationContext.getBean(CpDataResultService.class);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        //[CpDataResultView{cpIndex='2', endQihao='083', startQihao='077', cruHaoMa='6', createTime=Wed Jun 20 19:53:19 CST 2018, cishu=7, yichu=[0, 1, 2, 3, 6], weichu=[4, 5, 7, 8, 9]}]
        List<CpDataResultView> analyzi = bean1.analyzi(strings,0,7,5);

        bean1.sendMails(analyzi);
        System.out.println(analyzi);

    }
}
