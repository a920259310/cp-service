package cn.ananyz.cp.service.mail;

import java.util.List;

/**
 * Created by 王晶 on 2018/6/10.
 */
public class MailConfig {
    public String from;
    public List<String> to;
    public String subject;
    public String text;


//    public String from = "lian920259310@163.com";
//    public List<String> to = "920259310@qq.com";
//    public String subject = "自定义小游戏";
//    public String text = "告警提示:";


    @Override
    public String toString() {
        return "MailConfig{" +
                "from='" + from + '\'' +
                ", to=" + to +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
