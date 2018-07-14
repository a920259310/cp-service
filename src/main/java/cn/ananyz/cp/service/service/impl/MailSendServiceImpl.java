package cn.ananyz.cp.service.service.impl;


import cn.ananyz.cp.service.config.BaseConfig;
import cn.ananyz.cp.service.mail.MailConfig;
import cn.ananyz.cp.service.mail.MailService;
import cn.ananyz.cp.service.service.MailSendService;
import cn.ananyz.cp.service.utils.DateUtil;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailSendServiceImpl implements MailSendService {

    @Autowired
    private MailService mailService;

    @Autowired
    private MailConfig mailConfig;

    @Override
    public Boolean sendMails(List<CpDataResultView> cpDataResultViews, BaseConfig baseConfig) {
        if(cpDataResultViews == null || cpDataResultViews.size() == 0){
            return Boolean.TRUE;
        }
        String mailSubjectByCpDataResultView = getMailSubjectByCpDataResultView(cpDataResultViews.get(0),baseConfig);
        String text = "";

        for(CpDataResultView cpDataResultView : cpDataResultViews){
            text = text + getMailTextByCpDataResultView(cpDataResultView);
        }
        mailService.sendMorePerson(mailConfig.getFrom(),mailConfig.getTo(), mailSubjectByCpDataResultView,text);
        return Boolean.TRUE;
    }


    private String getMailSubjectByCpDataResultView(CpDataResultView cpDataResultView, BaseConfig baseConfig) {
        return baseConfig.getSubJect() + ":" + mailConfig.getSubject() + ",日期:" + DateUtil.formatDate(cpDataResultView.getCreateTime(),DateUtil.PATTERN_DATE) +
                ",期号:" + cpDataResultView.getEndQihao();
    }

    private String getMailTextByCpDataResultView(CpDataResultView cpDataResultView) {
        return "位置:" + cpDataResultView.getCpIndex() + ",开始期号:" + cpDataResultView.getStartQihao() + ",结束期号:" +
                cpDataResultView.getEndQihao() + ",号码:" + cpDataResultView.getCruHaoMa() + ",采集时间:" +
                DateUtil.formatDate(cpDataResultView.getCreateTime(),DateUtil.PATTERN_DATE_TIME) + "连续次数:" + cpDataResultView.getCishu()
                + "已经出现的号:" + cpDataResultView.getYichu() + ",没有出现的号:" + cpDataResultView.getWeichu() + "\r\n\r\n";
    }
}
