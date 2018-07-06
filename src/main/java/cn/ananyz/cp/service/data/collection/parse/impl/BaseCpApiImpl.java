package cn.ananyz.cp.service.data.collection.parse.impl;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public abstract class BaseCpApiImpl implements CpApi {

    protected String parseDateFormat = "yyyyMMdd";
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parseDateFormat);

    public List<CPDataModel> getTodayAllData(Date date) throws IOException, ParseException {
        return null;
    }

    public CPDataModel getTodayLastData(Date date) throws IOException, ParseException {
        List<CPDataModel> todayAllDatas = getTodayAllData(date);
        if(todayAllDatas == null || todayAllDatas.size() == 0){
            return null;
        }
        Collections.sort(todayAllDatas, new Comparator<CPDataModel>() {
            @Override
            public int compare(CPDataModel o1, CPDataModel o2) {
                return o1.getLongDateAndQiHao().compareTo(o2.getLongDateAndQiHao());
            }
        });

        return todayAllDatas.get(todayAllDatas.size()-1);
    }

    public CPDataModel getDataByDateAndQiHao(Date date, Integer qiHao) throws IOException, ParseException {
        List<CPDataModel> todayAllDatas = getTodayAllData(date);

        for(CPDataModel todayAllData : todayAllDatas ){
            if(qiHao == Integer.parseInt(todayAllData.getShortQiHao())){
                return todayAllData;
            }
        }

        return null;
    }


    protected void setOpenNum(CPDataModel cPDataModel, String[] split) {
        if(split != null && split.length == 5){
            cPDataModel.setWan(Integer.parseInt(split[0]));
            cPDataModel.setQian(Integer.parseInt(split[1]));
            cPDataModel.setBai(Integer.parseInt(split[2]));
            cPDataModel.setShi(Integer.parseInt(split[3]));
            cPDataModel.setGe(Integer.parseInt(split[4]));
        }
    }
}
