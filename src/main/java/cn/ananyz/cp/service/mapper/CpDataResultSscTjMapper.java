package cn.ananyz.cp.service.mapper;

import cn.ananyz.cp.service.model.CpDataResult;
import cn.ananyz.cp.service.model.CpDataResultSscTj;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CpDataResultSscTjMapper extends Mapper<CpDataResultSscTj> {
    public List<CpDataResultSscTj> selectLimit(@Param("cpIndex")String cpIndex, @Param("start")int start, @Param("end")int end);
}