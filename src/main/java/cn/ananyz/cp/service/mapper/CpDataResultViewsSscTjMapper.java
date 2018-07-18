package cn.ananyz.cp.service.mapper;

import cn.ananyz.cp.service.model.CpDataResultViewsSscTj;
import cn.ananyz.cp.service.model.anlyz.MaxCountView;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

public interface CpDataResultViewsSscTjMapper extends Mapper<CpDataResultViewsSscTj> {
    MaxCountView selectMaxCishuViewByStartAndEndTime(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}