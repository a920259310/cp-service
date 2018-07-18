package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi163;
import cn.ananyz.cp.service.model.CpDataResult;
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
public class CpDataResultServiceTest {
    @Autowired
    private CpDataResultService cpDataResultService;

    @Autowired
    private CpApi163 cpApi163;

    @Test
    public void selectByDateLastData() throws IOException, ParseException {
        CpDataResult cpDataResult = cpDataResultService.selectByDateLastData(new Date());
        System.out.println(cpDataResult);
        List<CPDataModel> todayLastDatasByQihao = cpApi163.getTodayLastDatasByQihao(new Date(), "002");
        System.out.println(todayLastDatasByQihao);
    }
}
