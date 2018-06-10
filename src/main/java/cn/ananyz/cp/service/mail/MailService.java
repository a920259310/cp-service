package cn.ananyz.cp.service.mail;

/**
 * Created by 王晶 on 2018/6/10.
 */
public interface MailService {
    public Boolean send(String from,String to,String subject,String text);
}
