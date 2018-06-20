package cn.ananyz.cp.service.schedule;

import cn.ananyz.cp.service.controller.CpDataResultController;
import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import cn.ananyz.cp.service.model.CpData;
import cn.ananyz.cp.service.service.AnalysisEngineService;
import cn.ananyz.cp.service.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 王晶 on 2018/6/10.
 */
@Component
public class ScheduleComment {
    @Autowired
    private CpApi cpApi;
    @Autowired
    private AnalysisEngineService analysisEngineService;



    public void queryCpData() throws Exception {
        CPDataModel todayLastData = cpApi.getTodayLastData(new Date());
        CpData cpData = convertCPDataModelToCpData(todayLastData);
        analysisEngineService.insert(cpData);

        String[] strings = {"1", "2","3", "4","5"};
        send(strings);
    }

    private void send(String[] str) throws Exception {
        for(String s : str){
            analysisEngineService.analys(s);
        }
    }

    private CpData convertCPDataModelToCpData(CPDataModel todayLastData) throws Exception {
        CpData cpData = new CpData();
        cpData.setCreateTime(new Date());
        cpData.setId(UUID.randomUUID().toString().replaceAll("-",""));
        String substring = "20" + todayLastData.getLongDateAndQiHao().substring(0, 6);
        cpData.setCpDate(DateUtil.formatDate(DateUtil.parseDate(substring,DateUtil.PATTERN_DATE_NOT),DateUtil.PATTERN_DATE));
        cpData.setCpQiHao(Integer.parseInt(todayLastData.getShortQiHao()));
        cpData.setWan(todayLastData.getWan());
        cpData.setQian(todayLastData.getQian());
        cpData.setBai(todayLastData.getBai());
        cpData.setShi(todayLastData.getShi());
        cpData.setGe(todayLastData.getGe());
        return cpData;
    }


}
