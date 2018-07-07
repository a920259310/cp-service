package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.constance.Constance;
import cn.ananyz.cp.service.mail.MailConfig;
import cn.ananyz.cp.service.mail.MailService;
import cn.ananyz.cp.service.mapper.CpDataResultMapper;
import cn.ananyz.cp.service.model.CpDataResult;
import cn.ananyz.cp.service.service.CpDataResultService;
import cn.ananyz.cp.service.utils.DateUtil;
import cn.ananyz.cp.service.utils.NumUtil;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CpDataResultServiceImpl extends BaseServiceImpl<CpDataResult> implements CpDataResultService {

    private static Logger logger = Logger.getLogger(CpDataResultServiceImpl.class);

    @Autowired
    private CpDataResultMapper cpDataResultMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailConfig mailConfig;

    @Override
    public int insert(CpDataResult cpDataResult) {

        CpDataResult cpDataResultCondition = new CpDataResult();

        cpDataResultCondition.setCpDate(cpDataResult.getCpDate());
        cpDataResultCondition.setCpQiHao(cpDataResult.getCpQiHao());
        cpDataResultCondition.setCpIndex(cpDataResult.getCpIndex());

        CpDataResult cpDataResult1 = cpDataResultMapper.selectOne(cpDataResultCondition);

        if(cpDataResult1 == null){
            return cpDataResultMapper.insert(cpDataResult);
        }
        return 0;
    }


    @Override
    public List<CpDataResultView> analyzi(List<String> listIndex,int start,int end,int diffNum,String oneDayMaxQihao) throws ParseException {
        List<CpDataResultView> list = new ArrayList<CpDataResultView>();

        for(String cpIndex : listIndex){
            List<CpDataResult> cpDataResults = cpDataResultMapper.selectLimit(cpIndex, start, end);

            if(cpDataResults != null && cpDataResults.size() >= end){
                Set<String> setNum = new HashSet<String>();

                for(CpDataResult cpDataResult : cpDataResults){
                    setNum.add(cpDataResult.getCpNum());
                }

                if(setNum.size() <= diffNum){  //七期之内 出了小于五种号

                    ivalidataHaoMa(cpDataResults, setNum,diffNum,oneDayMaxQihao);

                    CpDataResult remove = cpDataResults.remove(cpDataResults.size() - 1);
                    CpDataResultView cpDataResultView = new CpDataResultView();

                    cpDataResultView.setCpIndex(cpIndex);
                    cpDataResultView.setStartQihao(cpDataResults.get((cpDataResults.size() - 1)).getCpQiHao());
                    cpDataResultView.setEndQihao(cpDataResults.get(0).getCpQiHao());
                    cpDataResultView.setCruHaoMa(cpDataResults.get(0).getCpNum());
                    cpDataResultView.setCreateTime(new Date());
                    cpDataResultView.setCreateDate(new Date());
                    cpDataResultView.setCishu(cpDataResults.size());

                    Set<String> collect = cpDataResults.stream().map(x -> {
                        return x.getCpNum();
                    }).collect(Collectors.toSet());

                    cpDataResultView.setYichu(new ArrayList<>(collect));
                    Set<String> allNum = Constance.getAllNum();
                    allNum.removeAll(collect);
                    cpDataResultView.setWeichu(new ArrayList<>(allNum));

                    cpDataResultView.setStartQiDate(DateUtil.parseDate(cpDataResults.get(cpDataResults.size() - 1).getCpDate(),DateUtil.PATTERN_DATE));
                    cpDataResultView.setEndQiDate(DateUtil.parseDate(cpDataResults.get(0).getCpDate(),DateUtil.PATTERN_DATE));

                    list.add(cpDataResultView);
                }
            }
        }

        return list;
    }

    @Override
    public Boolean sendMail(CpDataResultView cpDataResultView) {

        String text = getMailTextByCpDataResultView(cpDataResultView);

        String mailSubjectByCpDataResultView = getMailSubjectByCpDataResultView(cpDataResultView);

        mailService.sendMorePerson(mailConfig.getFrom(),mailConfig.getTo(),mailSubjectByCpDataResultView,text);

        return Boolean.TRUE;
    }

    private String getMailSubjectByCpDataResultView(CpDataResultView cpDataResultView) {
        return mailConfig.getSubject() + ",日期:" + DateUtil.formatDate(cpDataResultView.getCreateTime(),DateUtil.PATTERN_DATE) +
                ",期号:" + cpDataResultView.getEndQihao();
    }

    private String getMailTextByCpDataResultView(CpDataResultView cpDataResultView) {
        return "位置:" + cpDataResultView.getCpIndex() + ",开始期号:" + cpDataResultView.getStartQihao() + ",结束期号:" +
                    cpDataResultView.getEndQihao() + ",号码:" + cpDataResultView.getCruHaoMa() + ",采集时间:" +
                    DateUtil.formatDate(cpDataResultView.getCreateTime(),DateUtil.PATTERN_DATE_TIME) + "连续次数:" + cpDataResultView.getCishu()
                    + "已经出现的号:" + cpDataResultView.getYichu() + ",没有出现的号:" + cpDataResultView.getWeichu() + "\r\n";
    }

    @Override
    public Boolean sendMails(List<CpDataResultView> cpDataResultViews) {
        if(cpDataResultViews == null || cpDataResultViews.size() == 0){
            return Boolean.TRUE;
        }
        String mailSubjectByCpDataResultView = getMailSubjectByCpDataResultView(cpDataResultViews.get(0));
        String text = "";

        for(CpDataResultView cpDataResultView : cpDataResultViews){
            text = text + getMailTextByCpDataResultView(cpDataResultView);
        }

        mailService.sendMorePerson(mailConfig.getFrom(),mailConfig.getTo(),"改版后:" + mailSubjectByCpDataResultView,text);

        return Boolean.TRUE;
    }


    /**
     * 递归按条件查询
     * @param cpDataResults
     * @param setNum
     * @throws ParseException
     */
    private void ivalidataHaoMa(List<CpDataResult> cpDataResults, Set<String> setNum,int diffNum,String oneDayMaxQihao) throws ParseException {
        CpDataResult cpDataResult = cpDataResults.get(cpDataResults.size() - 1);
        int i = Integer.parseInt(cpDataResult.getCpQiHao()) - 1;
        CpDataResult cpDataResultCondition = new CpDataResult();

        cpDataResultCondition.setCpIndex(cpDataResult.getCpIndex());
        cpDataResultCondition.setCpDate(cpDataResult.getCpDate());
        cpDataResultCondition.setCpQiHao(NumUtil.converIntToStringNum(i));

        if(i < 1){
            cpDataResultCondition.setCpQiHao(oneDayMaxQihao);
            cpDataResultCondition.setCpDate(
                    DateUtil.formatDate(
                            new Date(
                                    DateUtil.parseDate(cpDataResult.getCpDate(),DateUtil.PATTERN_DATE).getTime() - (24 * 60 * 60 * 1000)),
                            DateUtil.PATTERN_DATE));
        }

        logger.info("上一期的查询参数.......cpDataResultCondition:" + cpDataResultCondition.toString());
        CpDataResult cpDataResultSelect = cpDataResultMapper.selectOne(cpDataResultCondition);

        if(cpDataResultSelect != null){
            cpDataResults.add(cpDataResultSelect);
            setNum.add(cpDataResultSelect.getCpNum());

            if(setNum.size() <= diffNum){
                ivalidataHaoMa(cpDataResults,setNum,diffNum,oneDayMaxQihao);
            }
        }
    }
}
