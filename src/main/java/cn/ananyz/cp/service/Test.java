package cn.ananyz.cp.service;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import cn.ananyz.cp.service.data.collection.parse.impl.CpApi163;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by 王晶 on 2018/6/3.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        CpApi cpApi = new CpApi163();
        CPDataModel todayAllData = cpApi.getTodayLastData(new Date());
        System.out.println(todayAllData);
    }
}
