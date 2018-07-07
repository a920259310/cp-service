package cn.ananyz.cp.service.service;

import cn.ananyz.cp.service.model.CpDataResultSscTj;
import cn.ananyz.cp.service.view.CpDataResultView;

import java.text.ParseException;
import java.util.List;

public interface CpDataResultSscTjService  extends BaseService<CpDataResultSscTj>{

    public List<CpDataResultView> analyzi(List<String> listIndex, int start, int end, int diffNum,String oneDayMaxQihao) throws ParseException;

    public Boolean sendMail(CpDataResultView cpDataResultView);

    public Boolean sendMails(List<CpDataResultView> cpDataResultViews);

}
