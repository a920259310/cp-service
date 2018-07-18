package cn.ananyz.cp.service.data.collection.parse.impl;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.model.CPDataModel2;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BaseCpApiImpl implements CpApi {

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

    @Override
    public List<CPDataModel> getTodayLastDatasByQihao(Date date,String  cpQihao) throws IOException, ParseException {

        List<CPDataModel> todayAllDatas = getTodayAllData(date);

        if(todayAllDatas == null || todayAllDatas.size() == 0){
            return null;
        }


        List<CPDataModel> collect = todayAllDatas.stream().filter(x -> {
            if(StringUtils.isEmpty(cpQihao)){
                return Boolean.TRUE;
            }else {
                return x.getShortQiHao().compareTo(cpQihao) > 0 ? Boolean.TRUE : Boolean.FALSE;
            }
        }).collect(Collectors.toList());

        if(collect == null || collect.size() == 0){
            return null;
        }

        Collections.sort(collect, new Comparator<CPDataModel>() {
            @Override
            public int compare(CPDataModel o1, CPDataModel o2) {
                return o1.getLongDateAndQiHao().compareTo(o2.getLongDateAndQiHao());
            }
        });

        return collect;
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

    @Override
    public List<CPDataModel2> getTodayAllDatas(Date date) throws IOException, ParseException {
        return null;
    }

    @Override
    public List<CPDataModel2> getDataByQiHao(int qiHao) throws IOException, ParseException {
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
