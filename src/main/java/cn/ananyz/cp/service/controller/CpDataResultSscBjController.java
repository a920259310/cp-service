package cn.ananyz.cp.service.controller;

import cn.ananyz.cp.service.config.CpDataResultSscBjConfig;
import cn.ananyz.cp.service.data.collection.model.CPDataModel2;
import cn.ananyz.cp.service.data.collection.parse.CpApiBj;
import cn.ananyz.cp.service.enums.GameTypeEnum;
import cn.ananyz.cp.service.model.CpDataResultSscBj;
import cn.ananyz.cp.service.model.CpDataSysConfig;
import cn.ananyz.cp.service.service.CpDataResultSscBjService;
import cn.ananyz.cp.service.service.CpDataResultViewsSscBjService;
import cn.ananyz.cp.service.service.CpDataSysConfigService;
import cn.ananyz.cp.service.service.MailSendService;
import cn.ananyz.cp.service.utils.DateUtil;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class CpDataResultSscBjController {
    private static Logger logger = Logger.getLogger(CpDataResultSscBjController.class);

    @Autowired
    private CpDataResultSscBjConfig cpDataResultSscBjConfig;

    @Autowired
    private CpDataResultSscBjService cpDataResultSscBjService;

    @Autowired
    private CpDataResultViewsSscBjService cpDataResultViewsSscBjService;

    @Autowired
    private CpDataSysConfigService cpDataSysConfigService;

    @Autowired
    private MailSendService mailSendService;

    public CpDataResultSscBjConfig getCpDataResultSscBjConfig() {
        return cpDataResultSscBjConfig;
    }

    public void setCpDataResultSscBjConfig(CpDataResultSscBjConfig cpDataResultSscBjConfig) {
        this.cpDataResultSscBjConfig = cpDataResultSscBjConfig;
    }

    /**
     * 获取最新的数据插入
     * @throws ParseException
     * @throws IOException
     */
    public void getLastNumInsertMysql() throws ParseException, IOException {
        /**
         * 今天页面所有数据插入
         */
        Boolean aBoolean = cpDataResultSscBjService.insertLastNumInsertMysqlByDate(new Date(), -3);
        /**
         * 搜索隐藏数据插入
         */
//        Boolean aBoolean1 = cpDataResultSscBjService.insertByQihaoInsertMysql(cpDataResultSscBjConfig.getSleepTime());
        /**
         * 分析
         */
        List<CpDataResultView> analyzi = cpDataResultSscBjService.analyzi(
                cpDataResultSscBjConfig.getListIndex(),
                cpDataResultSscBjConfig.getStart(),
                cpDataResultSscBjConfig.getEnd(),
                cpDataResultSscBjConfig.getDiffNum());
        logger.info("北京分析出的号....:" + analyzi);


        /**
         * 插入分析结果
         */
        cpDataResultViewsSscBjService.insert(analyzi);

        /**
         * 更新预警配置
         */
        cpDataSysConfigService.updateMaxCount(GameTypeEnum.GAME_TYPE_ENUM_BJ.getGameType());
        /**
         * 查询预警配置
         */
        CpDataSysConfig cpDataSysConfig = cpDataSysConfigService.selectCpDataSysConfigByGameTypeOne(GameTypeEnum.GAME_TYPE_ENUM_BJ.getGameType());

        /**
         * 邮件发送分析结果
         */
        List<CpDataResultView> collect = analyzi.stream().filter(x -> {
            return x.getCishu() >= (cpDataSysConfig.getMaxCount() + cpDataSysConfig.getLgMaxCount() - cpDataSysConfig.getPlanCount());
        }).collect(Collectors.toList());

        logger.info("北京邮件预警号....:" + collect);
        mailSendService.sendMails(collect,cpDataResultSscBjConfig);
    }






}
