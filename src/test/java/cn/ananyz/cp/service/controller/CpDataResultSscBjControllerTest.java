package cn.ananyz.cp.service.controller;

import cn.ananyz.cp.service.service.CpDataResultSscBjService;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class CpDataResultSscBjControllerTest {
    @Autowired
    private CpDataResultSscBjController cpDataResultSscBjController;

    @Autowired
    private CpDataResultSscBjService cpDataResultSscBjService;
    @Test
    public void getLastNumInsertMysql() throws ParseException, IOException {
        cpDataResultSscBjController.getLastNumInsertMysql();
    }

    @Test
    public void anyla() throws ParseException, IOException {
        ArrayList<String> strings = new ArrayList<>();

        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");
        strings.add("8");
        strings.add("9");
        strings.add("10");

        List<CpDataResultView> analyzi = cpDataResultSscBjService.analyzi(strings, 0, 7, 5);

        System.out.println(analyzi);
    }

}
