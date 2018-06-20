package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpDataResult;
import cn.ananyz.cp.service.view.CpDataResultView;

import java.text.ParseException;
import java.util.List;

public interface CpDataResultService extends BaseService<CpDataResult> {

    public List<CpDataResultView> analyzi(List<String> listIndex,int start,int end,int diffNum) throws ParseException;

    public Boolean sendMail(CpDataResultView cpDataResultView);

    public Boolean sendMails(List<CpDataResultView> cpDataResultViews);
}
