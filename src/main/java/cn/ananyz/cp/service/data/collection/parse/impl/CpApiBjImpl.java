package cn.ananyz.cp.service.data.collection.parse.impl;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.model.CPDataModel2;
import cn.ananyz.cp.service.data.collection.parse.CpApiBj;
import cn.ananyz.cp.service.utils.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Component
public class CpApiBjImpl extends BaseCpApiImpl implements CpApiBj {

    @Override
    public CPDataModel getTodayLastData(Date date) throws IOException, ParseException {
        return super.getTodayLastData(date);
    }

    @Override
    public CPDataModel getDataByDateAndQiHao(Date date, Integer qiHao) throws IOException, ParseException {
        return super.getDataByDateAndQiHao(date, qiHao);
    }

    private Document getTodayDocument(Date date) throws IOException {
        Document doc = Jsoup.connect("http://www.bwlc.net/bulletin/prevtrax.html").get();
        return doc;
    }

    private Elements getTodayElements(Document document){
        Element tbody = document.getElementsByTag("tbody").last();
        Elements tr = tbody.getElementsByTag("tr");
        tr.remove(0);
        return tr;
    }

    public static void main(String[] args) throws IOException, ParseException {
        CpApiBjImpl cpApibj = new CpApiBjImpl();
//        List<CPDataModel2> todayAllDatas = cpApibj.getTodayAllDatas(new Date());
//        System.out.println(todayAllDatas);
        List<CPDataModel2> dataByQiHao = cpApibj.getDataByQiHao(692781);
        System.out.println(dataByQiHao);
    }


    @Override
    public List<CPDataModel2> getDataByQiHao(int qiHao) throws IOException, ParseException {
        //http://www.bwlc.net/bulletin/prevtrax.html?num=692781
        List<CPDataModel2> cpDataModels = new ArrayList<CPDataModel2>();
        Document dateDocumentByQiHao = getDateDocumentByQiHao(qiHao);
        Elements todayElements = getTodayElements(dateDocumentByQiHao);

        getCpDataModel2ByElements(cpDataModels, todayElements);

        if(cpDataModels.size() == 0){
            return null;
        }
        return cpDataModels;
    }

    private Document getDateDocumentByQiHao(int qiHao) throws IOException {
        Document doc = Jsoup.connect("http://www.bwlc.net/bulletin/prevtrax.html?num=" + qiHao).get();
        return doc;
    }


    @Override
    public List<CPDataModel2> getTodayAllDatas(Date date) throws IOException, ParseException {
        List<CPDataModel2> cpDataModels = new ArrayList<CPDataModel2>();
        Document document = getTodayDocument(date);
        Elements todayElements = getTodayElements(document);

        getCpDataModel2ByElements(cpDataModels, todayElements);

        return cpDataModels;
    }

    private void getCpDataModel2ByElements(List<CPDataModel2> cpDataModels, Elements todayElements) throws ParseException {
        Iterator<Element> iterator = todayElements.iterator();
        while (iterator.hasNext()){
            Element next = iterator.next();
            Elements td = next.getElementsByTag("td");

            String dataPeriod = td.get(0).text();
            String dataWinNumber = td.get(1).text();
            String opentime = td.get(2).text() + ":00";

            CPDataModel2 cPDataModel2 = new CPDataModel2();
            cPDataModel2.setLongDateAndQiHao(dataPeriod);
            cPDataModel2.setShortQiHao(dataPeriod);
            cPDataModel2.setOpenTime(DateUtil.parseDate(opentime,DateUtil.PATTERN_DATE_TIME));

            String[] split = dataWinNumber.trim().split(",");

            List<CPDataModel2> cpDataModel2s = setOpenNum(cPDataModel2, split);
            cpDataModels.addAll(cpDataModel2s);
        }

        Collections.sort(cpDataModels, new Comparator<CPDataModel2>() {
            @Override
            public int compare(CPDataModel2 o1, CPDataModel2 o2) {
                return o1.getShortQiHao().compareTo(o2.getShortQiHao());
            }
        });
    }

    public List<CPDataModel2> setOpenNum(CPDataModel2 cPDataModel2,String[] split){
        List<CPDataModel2> cpDataModels = new ArrayList<CPDataModel2>();

        if(split == null || split.length == 0){
            return cpDataModels;
        }

        for (int i = 0; i < split.length; i++) {
            CPDataModel2 cpDataModel2New = new CPDataModel2();

            cpDataModel2New.setIndex(i+1);
            cpDataModel2New.setLongDateAndQiHao(cPDataModel2.getLongDateAndQiHao());
            cpDataModel2New.setShortQiHao(cPDataModel2.getShortQiHao());
            cpDataModel2New.setOpenTime(cPDataModel2.getOpenTime());
            cpDataModel2New.setNum(Integer.parseInt(split[i]));


            cpDataModels.add(cpDataModel2New);
        }
        return cpDataModels;
    }




}
