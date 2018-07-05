package cn.ananyz.cp.service.sms.service;

import java.util.List;

public interface SmsService {
    public String sendMesage(String phone,String context);
    public List<String> sendMesage(List<String> phone, String context);
}
