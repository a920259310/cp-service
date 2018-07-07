package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.mapper.CpDataResultViewsSscTjMapper;
import cn.ananyz.cp.service.model.CpDataResultViewsSscTj;
import cn.ananyz.cp.service.service.CpDataResultViewsSscTjService;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CpDataResultViewsSscTjServiceImpl extends BaseServiceImpl<CpDataResultViewsSscTj> implements CpDataResultViewsSscTjService {
    @Autowired
    private CpDataResultViewsSscTjMapper cpDataResultViewsSscTjMapper;

    @Override
    public int insert(CpDataResultView cpDataResultView) {
        CpDataResultViewsSscTj cpDataResultViewsSscTjCondition = new CpDataResultViewsSscTj();

        cpDataResultViewsSscTjCondition.setStartQiHao(cpDataResultView.getStartQihao());
        cpDataResultViewsSscTjCondition.setEndQiHao(cpDataResultView.getEndQihao());
        cpDataResultViewsSscTjCondition.setCpIndex(cpDataResultView.getCpIndex());
        cpDataResultViewsSscTjCondition.setCishu(cpDataResultView.getCishu());
        cpDataResultViewsSscTjCondition.setStartQiDate(cpDataResultView.getStartQiDate());
        cpDataResultViewsSscTjCondition.setEndQiDate(cpDataResultView.getEndQiDate());

        List<CpDataResultViewsSscTj> select = cpDataResultViewsSscTjMapper.select(cpDataResultViewsSscTjCondition);

        if(select != null && select.size() > 0){
            return 0;
        }

        CpDataResultViewsSscTj cpDataResultViewsSscTj = new CpDataResultViewsSscTj();

        cpDataResultViewsSscTj.setCpIndex(cpDataResultView.getCpIndex());
        cpDataResultViewsSscTj.setEndQiHao(cpDataResultView.getEndQihao());
        cpDataResultViewsSscTj.setStartQiHao(cpDataResultView.getStartQihao());
        cpDataResultViewsSscTj.setCruHaoMa(cpDataResultView.getCruHaoMa());
        cpDataResultViewsSscTj.setCreateTime(cpDataResultView.getCreateTime());
        cpDataResultViewsSscTj.setCreateDate(cpDataResultView.getCreateDate());
        cpDataResultViewsSscTj.setCishu(cpDataResultView.getCishu());


        String[] stringsYichuList = new String[cpDataResultView.getYichu().size()];
        String[] stringsYichuA = cpDataResultView.getYichu().toArray(stringsYichuList);

        cpDataResultViewsSscTj.setYichu(Arrays.toString(stringsYichuA));

        String[] stringsWeichuList = new String[cpDataResultView.getWeichu().size()];
        String[] stringsWeichu = cpDataResultView.getWeichu().toArray(stringsWeichuList);
        cpDataResultViewsSscTj.setWeichu(Arrays.toString(stringsWeichu));

        cpDataResultViewsSscTj.setStartQiDate(cpDataResultView.getStartQiDate());
        cpDataResultViewsSscTj.setEndQiDate(cpDataResultView.getEndQiDate());

        return cpDataResultViewsSscTjMapper.insert(cpDataResultViewsSscTj);


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
}
