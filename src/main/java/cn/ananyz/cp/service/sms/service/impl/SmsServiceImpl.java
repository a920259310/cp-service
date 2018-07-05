package cn.ananyz.cp.service.sms.service.impl;

import cn.ananyz.cp.service.sms.SmsUtils;
import cn.ananyz.cp.service.sms.config.SmsConfig;
import cn.ananyz.cp.service.sms.service.SmsService;

import java.util.ArrayList;
import java.util.List;

public class SmsServiceImpl implements SmsService {

    private SmsConfig smsConfig = new SmsConfig();
    @Override
    public String sendMesage(String phone, String context) {
        StringBuffer stringBuffer = SmsUtils.getStringBuffer(smsConfig.getTestUsername(), smsConfig.getTestPassword(), phone, context);
        String request = SmsUtils.request(smsConfig.getHttpUrl(), stringBuffer.toString());

        return request;
    }

    @Override
    public List<String> sendMesage(List<String> phone, String context) {
        ArrayList<String> strings = new ArrayList<>();
        for(String phone1 : phone){
            String sendMesage = sendMesage(phone1, context);
            strings.add(sendMesage);
        }

        return strings;
    }

    public static void main(String[] args) {
        SmsServiceImpl smsService = new SmsServiceImpl();

        sends(smsService);
//        send(smsService);
    }

    private static void send(SmsServiceImpl smsService) {
        String sendMesage = smsService.sendMesage("18520767259", "测试");
        System.out.println(sendMesage);
    }

    private static void sends(SmsServiceImpl smsService) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("13725098409");
//        arrayList.add("18520767259");
        arrayList.add("15119496852");
        List<String> list = smsService.sendMesage(arrayList, "测试1111");
        System.out.println(list);
    }

}
