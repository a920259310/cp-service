package cn.ananyz.cp.service.listener;

import cn.ananyz.cp.service.controller.CpDataResultController;
import cn.ananyz.cp.service.controller.CpDataResultSscTjController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CpDataResultController cpDataResultController;
    @Autowired
    CpDataResultSscTjController cpDataResultSscTjController;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        try {
////            cpDataResultController.initToday();
////            cpDataResultController.initAllCpData();
//            cpDataResultSscTjController.initToday();
//            System.out.println("初始化方法运行了.............");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
