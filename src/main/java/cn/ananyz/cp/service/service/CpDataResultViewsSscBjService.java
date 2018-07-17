package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpDataResultViewsSscBj;
import cn.ananyz.cp.service.model.anlyz.MaxCountView;
import cn.ananyz.cp.service.view.CpDataResultView;

import java.util.Date;
import java.util.List;

public interface CpDataResultViewsSscBjService extends BaseService<CpDataResultViewsSscBj> {

    public int insert(CpDataResultView cpDataResultView);

    public int insert(List<CpDataResultView> cpDataResultView);


    /**
     * 统计指定时间范围内的   连续最大次数    和最大次数出现的次数
     * @param startTime   起始时间
     * @param endTime     结束时间
     * @return            连续最大次数    和最大次数出现的次数
     */
    public MaxCountView selectMaxCountViewByStartAndEndTime(Date startTime, Date endTime);

}
