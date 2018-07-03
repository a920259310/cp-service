package cn.ananyz.cp.service.sms.config;

public class SmsConfig {

    //private String testUsername;
    //private String testPassword;
    //private String httpUrl;
//
    //private String testPhone;
    //private String testContent;

//private String testUsername = "a920259310"; //在短信宝注册的用户名
private String testUsername = "18520767259"; //在短信宝注册的用户名
private String testPassword = "a19931006"; //在短信宝注册的密码
private String testPhone = "18520767259";
private String testContent = "【万千购】您的验证码是1234,５分钟内有效。若非本人操作请忽略此消息。"; // 注意测试时，也请带上公司简称或网站签名，发送正规内容短信。千万不要发送无意义的内容：例如 测一下、您好。否则可能会收不到
private String httpUrl = "http://api.smsbao.com/sms";

    @Override
    public String toString() {
        return "SmsConfig{" +
                "testUsername='" + testUsername + '\'' +
                ", testPassword='" + testPassword + '\'' +
                ", testPhone='" + testPhone + '\'' +
                ", testContent='" + testContent + '\'' +
                ", httpUrl='" + httpUrl + '\'' +
                '}';
    }

    public String getTestUsername() {
        return testUsername;
    }

    public void setTestUsername(String testUsername) {
        this.testUsername = testUsername;
    }

    public String getTestPassword() {
        return testPassword;
    }

    public void setTestPassword(String testPassword) {
        this.testPassword = testPassword;
    }

    public String getTestPhone() {
        return testPhone;
    }

    public void setTestPhone(String testPhone) {
        this.testPhone = testPhone;
    }

    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }



}
