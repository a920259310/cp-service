package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.mapper.CpDataResultViewsMapper;
import cn.ananyz.cp.service.model.CpDataResultViews;
import cn.ananyz.cp.service.service.CpDataResultViewsService;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class CpDataResultViewsServiceImpl implements CpDataResultViewsService {
    @Autowired
    private CpDataResultViewsMapper cpDataResultViewsMapper;

    @Override
    public int insert(CpDataResultView cpDataResultView) {
        CpDataResultViews cpDataResultViewsCondition = new CpDataResultViews();

        cpDataResultViewsCondition.setStartQiHao(cpDataResultView.getStartQihao());
        cpDataResultViewsCondition.setEndQiHao(cpDataResultView.getEndQihao());
        cpDataResultViewsCondition.setCpIndex(cpDataResultView.getCpIndex());
        cpDataResultViewsCondition.setCishu(cpDataResultView.getCishu());
        cpDataResultViewsCondition.setStartQiDate(cpDataResultView.getStartQiDate());
        cpDataResultViewsCondition.setEndQiDate(cpDataResultView.getEndQiDate());

        List<CpDataResultViews> select = cpDataResultViewsMapper.select(cpDataResultViewsCondition);

        if(select != null && select.size() > 0){
            return 0;
        }

        CpDataResultViews cpDataResultViews = new CpDataResultViews();

        cpDataResultViews.setCpIndex(cpDataResultView.getCpIndex());
        cpDataResultViews.setEndQiHao(cpDataResultView.getEndQihao());
        cpDataResultViews.setStartQiHao(cpDataResultView.getStartQihao());
        cpDataResultViews.setCruHaoMa(cpDataResultView.getCruHaoMa());
        cpDataResultViews.setCreateTime(cpDataResultView.getCreateTime());
        cpDataResultViews.setCreateDate(cpDataResultView.getCreateDate());
        cpDataResultViews.setCishu(cpDataResultView.getCishu());


        String[] stringsYichuList = new String[cpDataResultView.getYichu().size()];
        String[] stringsYichuA = cpDataResultView.getYichu().toArray(stringsYichuList);

        cpDataResultViews.setYichu(Arrays.toString(stringsYichuA));

        String[] stringsWeichuList = new String[cpDataResultView.getWeichu().size()];
        String[] stringsWeichu = cpDataResultView.getWeichu().toArray(stringsWeichuList);
        cpDataResultViews.setWeichu(Arrays.toString(stringsWeichu));

        cpDataResultViews.setStartQiDate(cpDataResultView.getStartQiDate());
        cpDataResultViews.setEndQiDate(cpDataResultView.getEndQiDate());

        return cpDataResultViewsMapper.insert(cpDataResultViews);


    }

    @Override
    public int insert(List<CpDataResultView> cpDataResultView) {
        int i = 0 ;
        for(CpDataResultView v: cpDataResultView){
            int insert = insert(v);
            i = i + insert;
        }
        return i;
    }

    @Override
    public int insert(CpDataResultViews cpDataResultViews) {
        return 0;
    }

    @Override
    public CpDataResultViews queryById(Object t) {
        return null;
    }

    @Override
    public List<CpDataResultViews> query(CpDataResultViews cpDataResultViews) {
        return null;
    }

    @Override
    public CpDataResultViews queryByBean(CpDataResultViews cpDataResultViews) throws Exception {
        return null;
    }
}
