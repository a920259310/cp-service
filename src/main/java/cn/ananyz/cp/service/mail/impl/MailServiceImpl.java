package cn.ananyz.cp.service.mail.impl;

import cn.ananyz.cp.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 王晶 on 2018/6/10.
 */
@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Boolean send(String from,String to,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom(from);//发件人
        message.setTo(to);//收件人
        message.setSubject(subject);//主题
        message.setText(text);//正文
        javaMailSender.send(message);
        return Boolean.TRUE;
    }

    @Override
    public Boolean sendMorePerson(String from, List<String> tos, String subject, String text) {

        for(String to : tos){
            send(from,to,subject,text);
        }

        return Boolean.TRUE;
    }


}
