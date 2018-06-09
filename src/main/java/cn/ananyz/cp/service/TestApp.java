package cn.ananyz.cp.service;

import cn.ananyz.cp.service.mapper.CpDataMapper;
import cn.ananyz.cp.service.model.CpData;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApp {
    static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) {
        CpDataMapper bean = classPathXmlApplicationContext.getBean(CpDataMapper.class);
        CpData cpData = bean.selectByPrimaryKey(5l);
        System.out.println(bean);
        System.out.println(cpData);
    }
}
