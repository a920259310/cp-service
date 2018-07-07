package cn.ananyz.cp.service.data.collection.parse.impl;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi500;
import cn.ananyz.cp.service.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class CpApi500Test {
    @Autowired
    private CpApi500 cpApi500;

    @Test
    public void getTodayAllData() throws IOException, ParseException {
        List<CPDataModel> todayAllData = cpApi500.getTodayAllData(DateUtil.parseDate("2018-07-06",DateUtil.PATTERN_DATE));
        System.out.println(todayAllData);
    }

    @Test
    public void getTodayLastData() throws IOException, ParseException {
        CPDataModel todayLastData = cpApi500.getTodayLastData(new Date());
        System.out.println(todayLastData);
    }

    @Test
    public void getDataByDateAndQiHao() throws IOException, ParseException {
        CPDataModel todayLastData = cpApi500.getDataByDateAndQiHao(new Date(),49);
        System.out.println(todayLastData);
    }


}

