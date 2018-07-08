package cn.ananyz.cp.service.schedule;

import cn.ananyz.cp.service.controller.CpDataResultController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class ScheduleCpDataResult {
    private static Logger logger = Logger.getLogger(ScheduleCpDataResult.class);
    @Autowired
    private CpDataResultController cpDataResultController;

    public void analyz() throws IOException, ParseException {
        logger.info("重庆的配置信息:" + cpDataResultController.getCpDataResultConfig());
        if(cpDataResultController.getCpDataResultConfig().getSchedule()){
            cpDataResultController.selectCruNum();
            cpDataResultController.analyz();
            logger.info("重庆的调度方法执行了......");
        }

    }
}
