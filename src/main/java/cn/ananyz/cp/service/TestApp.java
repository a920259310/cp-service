package cn.ananyz.cp.service;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi;
import cn.ananyz.cp.service.model.CpData;
import cn.ananyz.cp.service.model.CpParityAnalysisResult;
import cn.ananyz.cp.service.service.AnalysisEngineService;
import cn.ananyz.cp.service.service.CpDataService;
import cn.ananyz.cp.service.service.CpParityAnalysisService;
import cn.ananyz.cp.service.utils.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TestApp {
    static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) throws Exception {
        AnalysisEngineService bean = classPathXmlApplicationContext.getBean(AnalysisEngineService.class);
//        insertCpData(bean);

//        String[] strings = {"1", "2","3", "4","5"};
//        for(String s : strings){
//            bean.analys(s);
//        }

        /*CpApi cpApi = classPathXmlApplicationContext.getBean(CpApi.class);
        CPDataModel dataByDateAndQiHao = cpApi.getDataByDateAndQiHao(new Date(), 41);
        System.out.println(dataByDateAndQiHao);*/

        Thread.sleep(100000000l);

//        mailTest();
//        jiOuCiShuCount();

//        CpDataService bean = classPathXmlApplicationContext.getBean(CpDataService.class);
//        CpData cpData = bean.queryLastData();
//        System.out.println(cpData);

    }

    private static void jiOuCiShuCount() throws Exception {
        CpParityAnalysisService bean = classPathXmlApplicationContext.getBean(CpParityAnalysisService.class);
        CpParityAnalysisResult cpParityAnalysisResult = bean.queryCountByIndexNum("" + 1);
        System.out.println(cpParityAnalysisResult);
    }

    private static void mailTest() {
        MailSender mailSender = classPathXmlApplicationContext.getBean(MailSender.class);
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom("lian920259310@163.com");//发件人
        message.setTo("920259310@qq.com");//收件人
        message.setSubject("系统发送");//主题
        message.setText("系统发送");//正文
        mailSender.send(message);
        System.out.println("邮件发送完毕");
    }

    private static void insertCpData(AnalysisEngineService bean) throws Exception {
        CpData cpData = new CpData();

        cpData.setCreateTime(new Date());
        cpData.setId(UUID.randomUUID().toString().replaceAll("-",""));
        cpData.setCpDate(DateUtil.formatDate(new Date(),DateUtil.PATTERN_DATE));
        cpData.setCpQiHao(22);

        cpData.setWan(2);
        cpData.setQian(1);
        cpData.setBai(3);
        cpData.setShi(4);
        cpData.setGe(5);

        bean.insert(cpData);
    }
}
