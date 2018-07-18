package cn.ananyz.cp.service.data.collection.parse;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.model.CPDataModel2;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by 王晶 on 2018/6/3.
 */
public interface CpApi {
    public List<CPDataModel> getTodayAllData(Date date) throws IOException, ParseException;

    public CPDataModel getTodayLastData(Date date) throws IOException, ParseException;

    public List<CPDataModel> getTodayLastDatasByQihao(Date date,String cpQihao) throws IOException, ParseException;

    public CPDataModel getDataByDateAndQiHao(Date date, Integer qiHao) throws IOException, ParseException;



    public List<CPDataModel2> getTodayAllDatas(Date date) throws IOException, ParseException;

    public List<CPDataModel2> getDataByQiHao(int qiHao) throws IOException, ParseException;
}
