package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpParityAnalysis;
import cn.ananyz.cp.service.model.CpParityAnalysisResult;

/**
 * Created by 王晶 on 2018/6/10.
 */
public interface CpParityAnalysisService extends BaseService<CpParityAnalysis> {
    public CpParityAnalysis queryLastTimeData(String indexNum) throws Exception;

    public CpParityAnalysisResult queryCountByIndexNum(String indexNum) throws Exception;


}
