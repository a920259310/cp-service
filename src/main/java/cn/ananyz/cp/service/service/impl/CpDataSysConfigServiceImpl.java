package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.enums.GameTypeEnum;
import cn.ananyz.cp.service.model.CpDataSysConfig;
import cn.ananyz.cp.service.model.anlyz.MaxCountView;
import cn.ananyz.cp.service.service.CpDataSysConfigService;
import cn.ananyz.cp.service.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


@Service
public class CpDataSysConfigServiceImpl extends BaseServiceImpl<CpDataSysConfig> implements CpDataSysConfigService {
    @Override
    public int updateMaxCount(String gameType) throws ParseException {

        //查询配置信息
        CpDataSysConfig selectCpDataSysConfigByGameTypeOne = selectCpDataSysConfigByGameTypeOne(gameType);
        Date endDate = new Date();
        Date startDate = DateUtil.add(endDate, Calendar.DAY_OF_MONTH, -selectCpDataSysConfigByGameTypeOne.getRecentDay());

        MaxCountView maxCountView = null;

        if(GameTypeEnum.GAME_TYPE_ENUM_BJ.getGameType().equals(gameType)){
            maxCountView = cpDataResultViewsSscBjMapper.selectMaxCishuViewByStartAndEndTime(startDate, endDate);
            logger.info(GameTypeEnum.GAME_TYPE_ENUM_BJ.getGameName() + "最大次数统计查询   起始时间:" + DateUtil.formatDate(startDate,DateUtil.PATTERN_DATE_TIME) + "   结束时间:"+ DateUtil.formatDate(endDate,DateUtil.PATTERN_DATE_TIME));
            logger.info(GameTypeEnum.GAME_TYPE_ENUM_BJ.getGameName() + "的连需最大次数统计:" + maxCountView);
        }
        if(GameTypeEnum.GAME_TYPE_ENUM_CQ.getGameType().equals(gameType)){
            maxCountView = cpDataResultViewsMapper.selectMaxCishuViewByStartAndEndTime(startDate, endDate);
            logger.info(GameTypeEnum.GAME_TYPE_ENUM_CQ.getGameName() + "最大次数统计查询   起始时间:" + DateUtil.formatDate(startDate,DateUtil.PATTERN_DATE_TIME) + "   结束时间:"+ DateUtil.formatDate(endDate,DateUtil.PATTERN_DATE_TIME));
            logger.info(GameTypeEnum.GAME_TYPE_ENUM_CQ.getGameName() + "的连需最大次数统计:" + maxCountView);
        }
        if(GameTypeEnum.GAME_TYPE_ENUM_TJ.getGameType().equals(gameType)){
            maxCountView = cpDataResultViewsSscTjMapper.selectMaxCishuViewByStartAndEndTime(startDate, endDate);
            logger.info(GameTypeEnum.GAME_TYPE_ENUM_TJ.getGameName() + "最大次数统计查询   起始时间:" + DateUtil.formatDate(startDate,DateUtil.PATTERN_DATE_TIME) + "   结束时间:"+ DateUtil.formatDate(endDate,DateUtil.PATTERN_DATE_TIME));
            logger.info(GameTypeEnum.GAME_TYPE_ENUM_TJ.getGameName() + "的连需最大次数统计:" + maxCountView);
        }

        //配置的最大连续次数或者配置的更新时间小于
        if(selectCpDataSysConfigByGameTypeOne.getMaxCount() < maxCountView.getMaxCount()
                || selectCpDataSysConfigByGameTypeOne.getMaxCountDateTimeMax().getTime() < startDate.getTime()
                || (selectCpDataSysConfigByGameTypeOne.getMaxCount() == maxCountView.getMaxCount()
                    && selectCpDataSysConfigByGameTypeOne.getMaxCountCount() < maxCountView.getCount())
                ){

            selectCpDataSysConfigByGameTypeOne.setMaxCount(maxCountView.getMaxCount());
            selectCpDataSysConfigByGameTypeOne.setUpdateTime(endDate);
            selectCpDataSysConfigByGameTypeOne.setMaxCountCount(maxCountView.getCount());
            selectCpDataSysConfigByGameTypeOne.setMaxCountDateTimeMax(maxCountView.getCreateTime());

        }

        return mapper.updateByPrimaryKeySelective(selectCpDataSysConfigByGameTypeOne);
    }

    @Override
    public CpDataSysConfig selectCpDataSysConfigByGameTypeOne(String gameType) {
        CpDataSysConfig one = new CpDataSysConfig();
        one.setGameType(gameType);
        return mapper.selectOne(one);
    }
}
