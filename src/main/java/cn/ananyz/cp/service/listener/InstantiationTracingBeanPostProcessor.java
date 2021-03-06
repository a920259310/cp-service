package cn.ananyz.cp.service.listener;

import cn.ananyz.cp.service.controller.CpDataResultController;
import cn.ananyz.cp.service.controller.CpDataResultSscBjController;
import cn.ananyz.cp.service.controller.CpDataResultSscTjController;
import cn.ananyz.cp.service.schedule.config.ScheduleConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger = Logger.getLogger(InstantiationTracingBeanPostProcessor.class);
    @Autowired
    CpDataResultController cpDataResultController;
    @Autowired
    CpDataResultSscTjController cpDataResultSscTjController;
    @Autowired
    CpDataResultSscBjController cpDataResultSscBjController;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {

            if(cpDataResultController.getCpDataResultConfig().getInitTodayData()){
                cpDataResultController.initToday();
                logger.info("重庆今日数据初始化方法运行了.............");
            }
            if(cpDataResultSscTjController.getCpDataResultSscTjConfig().getInitTodayData()){
                cpDataResultSscTjController.initToday();
                logger.info("天津今日数据初始化方法运行了.............");
            }

            if(cpDataResultSscBjController.getCpDataResultSscBjConfig().getInitTodayData()){
                cpDataResultSscBjController.getLastNumInsertMysql();
                logger.info("北京今日数据初始化方法运行了.............");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ScheduleConfig.IS_COMPLATE_START_BOOT_SCHEDULE = Boolean.FALSE;
    }
}
