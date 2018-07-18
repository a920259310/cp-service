package cn.ananyz.cp.service.mapper;

import cn.ananyz.cp.service.model.CpDataResultViews;
import cn.ananyz.cp.service.model.anlyz.MaxCountView;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

public interface CpDataResultViewsMapper extends Mapper<CpDataResultViews> {
    MaxCountView selectMaxCishuViewByStartAndEndTime(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}