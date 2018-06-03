package cn.ananyz.cp.service.data.collection.parse.impl;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 王晶 on 2018/6/3.
 */
public class CpApi163 implements CpApi {

    private String parseDateFormat = "yyyyMMdd";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parseDateFormat);


    private Document getTodayDocument(Date date) throws IOException {
        String format = simpleDateFormat.format(date);
        Document doc = Jsoup.connect("http://caipiao.163.com/award/cqssc/" + format + ".html").get();
        return doc;
    }
    private Elements getTodayElements(Document document){
        Elements select = document.select(".start");
        Elements select1 = select.select("[data-win-number]");
        return select1;
    }
    public List<CPDataModel> getTodayAllData(Date date) throws IOException {
        ArrayList<CPDataModel> cpDataModels = new ArrayList<CPDataModel>();
        Document document = getTodayDocument(date);
        Elements todayElements = getTodayElements(document);
        Iterator<Element> iterator = todayElements.iterator();
        while (iterator.hasNext()){
            Element next = iterator.next();
            String dataWinNumber = next.attr("data-win-number");
            String dataPeriod = next.attr("data-period");
            String qiHao = next.text();

            CPDataModel cPDataModel = new CPDataModel();
            cPDataModel.setLongDateAndQiHao(dataPeriod);
            cPDataModel.setShortQiHao(qiHao);
            String[] split = dataWinNumber.split(" ");
            if(split != null && split.length == 5){
                cPDataModel.setWan(Integer.parseInt(split[0]));
                cPDataModel.setQian(Integer.parseInt(split[1]));
                cPDataModel.setBai(Integer.parseInt(split[2]));
                cPDataModel.setShi(Integer.parseInt(split[3]));
                cPDataModel.setGe(Integer.parseInt(split[4]));
            }
            cpDataModels.add(cPDataModel);
        }
        return cpDataModels;
    }
}
