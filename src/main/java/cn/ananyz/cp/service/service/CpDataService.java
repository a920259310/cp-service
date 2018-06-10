package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpData;

public interface CpDataService extends BaseService<CpData> {
    public int insert(CpData t);
    public CpData queryLastData() throws Exception;
}
