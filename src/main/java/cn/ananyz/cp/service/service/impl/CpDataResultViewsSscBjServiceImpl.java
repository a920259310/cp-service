package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.model.CpDataResultViewsSscBj;
import cn.ananyz.cp.service.model.anlyz.MaxCountView;
import cn.ananyz.cp.service.service.CpDataResultViewsSscBjService;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CpDataResultViewsSscBjServiceImpl extends BaseServiceImpl<CpDataResultViewsSscBj> implements CpDataResultViewsSscBjService {

    @Override
    public int insert(CpDataResultView cpDataResultView) {

        CpDataResultViewsSscBj cpDataResultViewsSscBjCondition = new CpDataResultViewsSscBj();

        cpDataResultViewsSscBjCondition.setStartQiHao(Integer.parseInt(cpDataResultView.getStartQihao()));
        cpDataResultViewsSscBjCondition.setEndQiHao(Integer.parseInt(cpDataResultView.getEndQihao()));
        cpDataResultViewsSscBjCondition.setCpIndex(cpDataResultView.getCpIndex());
        cpDataResultViewsSscBjCondition.setCishu(cpDataResultView.getCishu());
        cpDataResultViewsSscBjCondition.setStartQiDate(cpDataResultView.getStartQiDate());
        cpDataResultViewsSscBjCondition.setEndQiDate(cpDataResultView.getEndQiDate());

        List<CpDataResultViewsSscBj> select = mapper.select(cpDataResultViewsSscBjCondition);

        if(select != null && select.size() > 0){
            return 0;
        }

        CpDataResultViewsSscBj cpDataResultViewsSscBj = new CpDataResultViewsSscBj();

        cpDataResultViewsSscBj.setCpIndex(cpDataResultView.getCpIndex());
        cpDataResultViewsSscBj.setEndQiHao(Integer.parseInt(cpDataResultView.getEndQihao()));
        cpDataResultViewsSscBj.setStartQiHao(Integer.parseInt(cpDataResultView.getStartQihao()));
        cpDataResultViewsSscBj.setCruHaoMa(cpDataResultView.getCruHaoMa());
        cpDataResultViewsSscBj.setCreateTime(cpDataResultView.getCreateTime());
        cpDataResultViewsSscBj.setCreateDate(cpDataResultView.getCreateDate());
        cpDataResultViewsSscBj.setCishu(cpDataResultView.getCishu());


        String[] stringsYichuList = new String[cpDataResultView.getYichu().size()];
        String[] stringsYichuA = cpDataResultView.getYichu().toArray(stringsYichuList);

        cpDataResultViewsSscBj.setYichu(Arrays.toString(stringsYichuA));

        String[] stringsWeichuList = new String[cpDataResultView.getWeichu().size()];
        String[] stringsWeichu = cpDataResultView.getWeichu().toArray(stringsWeichuList);
        cpDataResultViewsSscBj.setWeichu(Arrays.toString(stringsWeichu));

        cpDataResultViewsSscBj.setStartQiDate(cpDataResultView.getStartQiDate());
        cpDataResultViewsSscBj.setEndQiDate(cpDataResultView.getEndQiDate());

        return mapper.insert(cpDataResultViewsSscBj);
    }

    @Override
    public int insert(List<CpDataResultView> cpDataResultViews) {
        int i = 0 ;
        for(CpDataResultView v: cpDataResultViews){
            int insert = insert(v);
            i = i + insert;
        }
        return i;
    }

    @Override
    public MaxCountView selectMaxCountViewByStartAndEndTime(Date startTime, Date endTime) {
        return cpDataResultViewsSscBjMapper.selectMaxCishuViewByStartAndEndTime(startTime,endTime);
    }
}
