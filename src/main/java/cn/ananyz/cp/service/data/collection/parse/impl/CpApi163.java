package cn.ananyz.cp.service.data.collection.parse.impl;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 王晶 on 2018/6/3.
 */
@Component
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

    @Override
    public CPDataModel getTodayLastData(Date date) throws IOException {

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
    public CPDataModel getDataByDateAndQiHao(Date date, Integer qiHao) throws IOException {
        List<CPDataModel> todayAllDatas = getTodayAllData(date);

        for(CPDataModel todayAllData : todayAllDatas ){
            if(qiHao == Integer.parseInt(todayAllData.getShortQiHao())){
                return todayAllData;
            }
        }
        return null;
    }
}
