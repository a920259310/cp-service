package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.mapper.CpDataMapper;
import cn.ananyz.cp.service.model.CpData;
import cn.ananyz.cp.service.service.CpDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王晶 on 2018/6/10.
 */
@Service
public class CpDataServiceImpl implements CpDataService {


    @Autowired
    private CpDataMapper cpDataMapper;

    public int insert(CpData cpData) {
        CpData cpData1 = new CpData();
        cpData1.setCpDate(cpData.getCpDate());
        cpData1.setCpQiHao(cpData.getCpQiHao());
        List<CpData> select = cpDataMapper.select(cpData1);
        if(select == null || select.size() == 0){
            return cpDataMapper.insert(cpData);
        }
        return 0;
    }

    @Override
    public CpData queryLastData() throws Exception {
        return cpDataMapper.queryLastData();
    }

    public CpData queryById(Object t) {
        return cpDataMapper.selectByPrimaryKey(t);
    }

    public List<CpData> query(CpData cpData) {
        return cpDataMapper.select(cpData);
    }

    @Override
    public CpData queryByBean(CpData cpData) throws Exception {
        return cpDataMapper.selectOne(cpData);
    }
}
