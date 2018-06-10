package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.mapper.CpParityAnalysisMapper;
import cn.ananyz.cp.service.model.CpParityAnalysis;
import cn.ananyz.cp.service.model.CpParityAnalysisResult;
import cn.ananyz.cp.service.service.CpParityAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王晶 on 2018/6/10.
 */
@Service
public class CpParityAnalysisServiceImpl implements CpParityAnalysisService {
    @Autowired
    private CpParityAnalysisMapper cpParityAnalysisMapper;

    @Override
    public int insert(CpParityAnalysis cpParityAnalysis) {
        return cpParityAnalysisMapper.insert(cpParityAnalysis);
    }

    @Override
    public CpParityAnalysis queryById(Object t) {
        return cpParityAnalysisMapper.selectByPrimaryKey(t);
    }

    @Override
    public List<CpParityAnalysis> query(CpParityAnalysis cpParityAnalysis) {
        return cpParityAnalysisMapper.select(cpParityAnalysis);
    }

    @Override
    public CpParityAnalysis queryByBean(CpParityAnalysis cpParityAnalysis) throws Exception {
        return cpParityAnalysisMapper.selectOne(cpParityAnalysis);
    }

    @Override
    public CpParityAnalysis queryLastTimeData(String indexNum) throws Exception {
//        CpParityAnalysis cpParityAnalysis = new CpParityAnalysis();
//        cpParityAnalysis.setIndexNum(indexNum);
        return cpParityAnalysisMapper.queryLastTimeData("" + indexNum);
    }

    @Override
    public CpParityAnalysisResult queryCountByIndexNum(String indexNum) throws Exception {
        return cpParityAnalysisMapper.queryCountByIndexNum(indexNum);
    }


}
