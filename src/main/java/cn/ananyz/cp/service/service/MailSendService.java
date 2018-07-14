package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.config.BaseConfig;
import cn.ananyz.cp.service.view.CpDataResultView;

import java.util.List;

public interface MailSendService {
    /**
     * 邮件发送
     * @param cpDataResultViews
     * @return
     */
    public Boolean sendMails(List<CpDataResultView> cpDataResultViews, BaseConfig baseConfig);
}
