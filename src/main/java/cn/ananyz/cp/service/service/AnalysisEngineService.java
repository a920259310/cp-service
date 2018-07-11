package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpData;

/**
 * Created by 王晶 on 2018/6/10.
 */
public interface AnalysisEngineService {
    public void insert(CpData cpData) throws Exception;

    public void analys(String indexNumP,int warnCount) throws Exception;
}
