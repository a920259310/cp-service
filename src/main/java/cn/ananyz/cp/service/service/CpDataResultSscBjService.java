package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpDataResultSscBj;
import cn.ananyz.cp.service.view.CpDataResultView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface CpDataResultSscBjService extends BaseService<CpDataResultSscBj>{

    public List<CpDataResultView> analyzi(List<String> listIndex, int start, int end, int diffNum) throws ParseException;

    /**
     * 获取指定日期的最后一个值
     * @return
     */
    public CpDataResultSscBj selectLastNum(Date date);

    /**
     * 批量插入
     * @param cpDataResultSscTj
     * @return
     */
    public Boolean insertBatch(List<CpDataResultSscBj> cpDataResultSscTj);

    /**
     * 查询今日页面的所有数据插入数据库
     * @param date
     * @param delayMinute
     * @return
     */
    public Boolean insertLastNumInsertMysqlByDate(Date date,int delayMinute) throws IOException, ParseException;

    /**
     * 根据最大期号查询隐藏延迟号
     * @return
     */
    public Boolean insertByQihaoInsertMysql(Long sleepTime) throws IOException, ParseException;

    /**
     * 通过数据库最大值查找下一次的
     * @param cpDataResultSscBj
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public List<CpDataResultSscBj> insertPreDataByLastData(CpDataResultSscBj cpDataResultSscBj) throws IOException, ParseException;
}
