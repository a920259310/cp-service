package cn.ananyz.cp.service.data.collection.parse;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by 王晶 on 2018/6/3.
 */
public interface CpApi {
    public List<CPDataModel> getTodayAllData(Date date) throws IOException;

    public CPDataModel getTodayLastData(Date date) throws IOException;

    public CPDataModel getDataByDateAndQiHao(Date date, Integer qiHao) throws IOException;
}
