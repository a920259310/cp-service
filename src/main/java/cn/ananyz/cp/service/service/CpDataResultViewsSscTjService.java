package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpDataResultViewsSscTj;
import cn.ananyz.cp.service.view.CpDataResultView;

import java.util.List;

public interface CpDataResultViewsSscTjService extends BaseService<CpDataResultViewsSscTj> {
    public int insert(CpDataResultView cpDataResultView);

    public int insert(List<CpDataResultView> cpDataResultView);
}
