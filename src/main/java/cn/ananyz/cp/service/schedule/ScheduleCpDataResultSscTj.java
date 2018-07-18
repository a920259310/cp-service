package cn.ananyz.cp.service.schedule;

import cn.ananyz.cp.service.controller.CpDataResultSscTjController;
import cn.ananyz.cp.service.listener.InstantiationTracingBeanPostProcessor;
import cn.ananyz.cp.service.schedule.config.ScheduleConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class ScheduleCpDataResultSscTj {
    private static Logger logger = Logger.getLogger(ScheduleCpDataResultSscTj.class);
    @Autowired
    private CpDataResultSscTjController cpDataResultSscTjController;

    public void analyz() throws IOException, ParseException {
        if(!ScheduleConfig.IS_COMPLATE_START_BOOT_SCHEDULE){
            logger.info("天津的配置信息:" + cpDataResultSscTjController.getCpDataResultSscTjConfig());
            if(cpDataResultSscTjController.getCpDataResultSscTjConfig().getSchedule()){
                cpDataResultSscTjController.selectCruNum();
                cpDataResultSscTjController.analyz();
                logger.info("天津的调度方法执行了......");
            }
        }
    }
}
