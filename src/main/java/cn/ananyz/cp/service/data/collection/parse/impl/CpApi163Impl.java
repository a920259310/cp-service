package cn.ananyz.cp.service.data.collection.parse.impl;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import cn.ananyz.cp.service.data.collection.parse.CpApi163;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 王晶 on 2018/6/3.
 */
@Component
public class CpApi163Impl extends BaseCpApiImpl implements CpApi163 {

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
            cPDataModel.setOpenTime(new Date());
            String[] split = dataWinNumber.split(" ");
            setOpenNum(cPDataModel, split);
            cpDataModels.add(cPDataModel);
        }
        return cpDataModels;
    }

    @Override
    public CPDataModel getTodayLastData(Date date) throws IOException, ParseException {
        return super.getTodayLastData(date);
    }


    @Override
    public CPDataModel getDataByDateAndQiHao(Date date, Integer qiHao) throws IOException, ParseException {
        return super.getDataByDateAndQiHao(date,qiHao);
    }
}
