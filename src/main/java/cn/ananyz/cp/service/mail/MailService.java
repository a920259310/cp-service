package cn.ananyz.cp.service.mail;

import java.util.List;

/**
 * Created by 王晶 on 2018/6/10.
 */
public interface MailService {
    public Boolean send(String from,String to,String subject,String text);


    public Boolean sendMorePerson(String from, List<String> tos, String subject, String text);
}
