package cn.ananyz.cp.service.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class CpDataResultControllerTest {
    @Autowired
    private CpDataResultController cpDataResultController;
    @Test
    public void selectCruNum() throws IOException, ParseException {
        cpDataResultController.selectCruNum();
    }
}
