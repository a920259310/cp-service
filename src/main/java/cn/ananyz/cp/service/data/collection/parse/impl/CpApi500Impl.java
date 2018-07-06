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
        String format = simpleDateFormat.format(date);
        Document doc = Jsoup.connect("http://kaijiang.500.com/static/info/kaijiang/xml/tjssc/" + format + ".xml").get();
        return doc;
    }
    private Elements getTodayElements(Document document){
        Elements row = document.getElementsByTag("row");
        return row;
    }

    @Override
    public List<CPDataModel> getTodayAllData(Date date) throws IOException, ParseException {
        ArrayList<CPDataModel> cpDataModels = new ArrayList<CPDataModel>();
        Document document = getTodayDocument(date);
        Elements todayElements = getTodayElements(document);
        Iterator<Element> iterator = todayElements.iterator();
        while (iterator.hasNext()){
            Element next = iterator.next();
            String dataWinNumber = next.attr("opencode");
            String dataPeriod = next.attr("expect");
            String opentime = next.attr("opentime");
            String qiHao = dataPeriod.substring(8,dataPeriod.length());

            CPDataModel cPDataModel = new CPDataModel();
            cPDataModel.setLongDateAndQiHao(dataPeriod);
            cPDataModel.setShortQiHao(qiHao);
            cPDataModel.setOpenTime(DateUtil.parseDate(opentime,DateUtil.PATTERN_DATE_TIME));

            String[] split = dataWinNumber.split(",");
            setOpenNum(cPDataModel, split);
            cpDataModels.add(cPDataModel);
        }
        return cpDataModels;
    }




}
