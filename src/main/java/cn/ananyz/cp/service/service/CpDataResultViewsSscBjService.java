package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpDataResultViewsSscBj;
import cn.ananyz.cp.service.view.CpDataResultView;

import java.util.List;

public interface CpDataResultViewsSscBjService extends BaseService<CpDataResultViewsSscBj> {

    public int insert(CpDataResultView cpDataResultView);

    public int insert(List<CpDataResultView> cpDataResultView);

}
