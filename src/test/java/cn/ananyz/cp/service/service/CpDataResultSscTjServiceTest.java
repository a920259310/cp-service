package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.data.collection.model.CPDataModel;
import cn.ananyz.cp.service.data.collection.parse.CpApi500;
import cn.ananyz.cp.service.model.CpDataResultSscTj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class CpDataResultSscTjServiceTest {
    @Autowired
    private CpApi500 cpApi500;
    @Autowired
    private CpDataResultSscTjService cpDataResultSscTjService;

    @Test
    public void insert() throws IOException, ParseException {
        CpDataResultSscTj cpDataResultSscTj = new CpDataResultSscTj();

        cpDataResultSscTj.setCpDate("2018-07-09");
        cpDataResultSscTj.setCpQiHao("001");
        cpDataResultSscTj.setCpIndex("1");
        cpDataResultSscTj.setCpNum("5");
        cpDataResultSscTj.setCreateTime(new Date());

        cpDataResultSscTjService.insert(cpDataResultSscTj);
    }
}
