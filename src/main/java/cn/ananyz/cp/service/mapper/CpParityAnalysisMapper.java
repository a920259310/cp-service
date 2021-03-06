package cn.ananyz.cp.service.mapper;

import cn.ananyz.cp.service.model.CpParityAnalysis;
import cn.ananyz.cp.service.model.CpParityAnalysisResult;
import tk.mybatis.mapper.common.Mapper;

public interface CpParityAnalysisMapper extends Mapper<CpParityAnalysis> {
    public CpParityAnalysis queryLastTimeData(String indexNum) throws Exception;

    public CpParityAnalysisResult queryCountByIndexNum(String indexNum) throws Exception;
}