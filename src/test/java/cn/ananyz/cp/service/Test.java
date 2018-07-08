package cn.ananyz.cp.service;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * Created by 王晶 on 2018/7/8.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://kaijiang.aicai.com/open/difangIssueDetailByKc.do?gameIndex=325&province=tianjin&searchDate=2018-07-07").get();
        System.out.println(doc);
    }
}
