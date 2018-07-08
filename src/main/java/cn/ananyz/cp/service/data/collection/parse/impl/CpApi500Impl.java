package cn.ananyz.cp.service.data.collection.parse.impl;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi500;
import cn.ananyz.cp.service.utils.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Component
public class CpApi500Impl extends BaseCpApiImpl implements CpApi500 {

    @Override
    public CPDataModel getTodayLastData(Date date) throws IOException, ParseException {
        return super.getTodayLastData(date);
    }

    @Override
    public CPDataModel getDataByDateAndQiHao(Date date, Integer qiHao) throws IOException, ParseException {
        return super.getDataByDateAndQiHao(date, qiHao);
    }

    private Document getTodayDocument(Date date) throws IOException {
        Document doc = Jsoup.connect("https://kaijiang.aicai.com/open/difangIssueDetailByKc.do?gameIndex=325&province=tianjin").get();
        return doc;
    }
    private Elements getTodayElements(Document document){
        Element jq_body_kc_result = document.getElementById("jq_body_kc_result");
        Elements tr = jq_body_kc_result.getElementsByTag("tr");
        return tr;
    }

    public static void main(String[] args) throws IOException, ParseException {
        CpApi500Impl cpApi500 = new CpApi500Impl();
        List<CPDataModel> todayAllData = cpApi500.getTodayAllData(new Date());
        System.out.println(todayAllData);
    }
    @Override
    public List<CPDataModel> getTodayAllData(Date date) throws IOException, ParseException {
        ArrayList<CPDataModel> cpDataModels = new ArrayList<CPDataModel>();
        Document document = getTodayDocument(date);
        Elements todayElements = getTodayElements(document);
        Iterator<Element> iterator = todayElements.iterator();
        while (iterator.hasNext()){
            Element next = iterator.next();
            Elements td = next.getElementsByTag("td");

            String dataWinNumber = td.get(2).text();
            String dataPeriod = td.get(0).text();
            String opentime = td.get(1).text() + ":00";
            String qiHao = "0" + dataPeriod.substring(8,dataPeriod.length()-1);

            CPDataModel cPDataModel = new CPDataModel();
            cPDataModel.setLongDateAndQiHao(dataPeriod.substring(0,dataPeriod.length()-1));
            cPDataModel.setShortQiHao(qiHao);
            cPDataModel.setOpenTime(DateUtil.parseDate(opentime,DateUtil.PATTERN_DATE_TIME));

            String[] split = dataWinNumber.trim().split(",");
            setOpenNum(cPDataModel, split);
            cpDataModels.add(cPDataModel);
        }
        return cpDataModels;
    }




}
