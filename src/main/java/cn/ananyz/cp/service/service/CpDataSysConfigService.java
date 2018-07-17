package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpDataSysConfig;

import java.text.ParseException;


public interface CpDataSysConfigService extends BaseService<CpDataSysConfig> {

    /**
     * 根据起始结束时间   和游戏类型  更新游戏配置
     * @param gameType
     * @return   是否更新成功
     */
    public int updateMaxCount(String gameType) throws ParseException;

    public CpDataSysConfig selectCpDataSysConfigByGameTypeOne(String gameType);
}
