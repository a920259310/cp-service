package cn.ananyz.cp.service.mapper;

import cn.ananyz.cp.service.model.anlyz.MaxCountView;
import cn.ananyz.cp.service.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class CpDataResultViewsSscBjMapperTest {

    @Autowired
    private CpDataResultViewsSscBjMapper cpDataResultViewsSscBjMapper;

    @Test
    public void selectMaxCountViewByStartAndEndTime() throws ParseException {
        Date date = new Date();
        System.out.println(DateUtil.formatDate(date,DateUtil.PATTERN_DATE_TIME));
        Date add = DateUtil.add(date, Calendar.DAY_OF_MONTH, -3);
        System.out.println(DateUtil.formatDate(add,DateUtil.PATTERN_DATE_TIME));
        MaxCountView maxCountView = cpDataResultViewsSscBjMapper.selectMaxCishuViewByStartAndEndTime(add, date);
        System.out.println(maxCountView);
    }
}
