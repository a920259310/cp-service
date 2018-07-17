package cn.ananyz.cp.service.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class CpDataSysConfigServiceTest {
    @Autowired
    private CpDataSysConfigService cpDataSysConfigService;

    @Test
    public void updateMaxCount() throws ParseException {
        String gameType = "2";
        System.out.println(cpDataSysConfigService.selectCpDataSysConfigByGameTypeOne(gameType));
        cpDataSysConfigService.updateMaxCount(gameType);
        System.out.println(cpDataSysConfigService.selectCpDataSysConfigByGameTypeOne(gameType));
    }
}
