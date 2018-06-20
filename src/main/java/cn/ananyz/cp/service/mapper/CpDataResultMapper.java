package cn.ananyz.cp.service.mapper;

import cn.ananyz.cp.service.model.CpDataResult;
import com.alibaba.druid.sql.visitor.functions.Char;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CpDataResultMapper extends Mapper<CpDataResult> {

    public List<CpDataResult> selectLimit(@Param("cpIndex")String cpIndex, @Param("start")int start, @Param("end")int end);
}