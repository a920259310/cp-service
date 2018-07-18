package cn.ananyz.cp.service.schedule;

import cn.ananyz.cp.service.controller.CpDataResultSscBjController;
import cn.ananyz.cp.service.schedule.config.ScheduleConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class ScheduleCpDataResultSscBj {

    private static Logger logger = Logger.getLogger(ScheduleCpDataResultSscBj.class);

    @Autowired
    private CpDataResultSscBjController cpDataResultSscBjController;

    public void analyz() throws IOException, ParseException {
        if(!ScheduleConfig.IS_COMPLATE_START_BOOT_SCHEDULE){
            logger.info("北京的配置信息:" + cpDataResultSscBjController.getCpDataResultSscBjConfig());
            if(cpDataResultSscBjController.getCpDataResultSscBjConfig().getSchedule()){
                cpDataResultSscBjController.getLastNumInsertMysql();
                logger.info("北京的调度方法执行了......");
            }
        }
    }
}
