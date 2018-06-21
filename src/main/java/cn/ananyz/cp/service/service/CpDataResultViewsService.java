package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpDataResultViews;
import cn.ananyz.cp.service.view.CpDataResultView;

import java.util.List;

public interface CpDataResultViewsService extends BaseService<CpDataResultViews> {
    public int insert(CpDataResultView cpDataResultView);

    public int insert(List<CpDataResultView> cpDataResultView);
}
