package cn.ananyz.cp.service.mapper;

import cn.ananyz.cp.service.model.CpDataResultSscBj;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CpDataResultSscBjMapper extends Mapper<CpDataResultSscBj> {
    public List<CpDataResultSscBj> selectLimit(@Param("cpIndex")String cpIndex, @Param("start")int start, @Param("end")int end);
}