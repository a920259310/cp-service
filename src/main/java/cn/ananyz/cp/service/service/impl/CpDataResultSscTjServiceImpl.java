package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.constance.Constance;
import cn.ananyz.cp.service.mail.MailConfig;
import cn.ananyz.cp.service.mail.MailService;
import cn.ananyz.cp.service.mapper.CpDataResultSscTjMapper;
import cn.ananyz.cp.service.model.CpDataResultSscTj;
import cn.ananyz.cp.service.service.CpDataResultSscTjService;
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
public class CpDataResultSscTjServiceImpl extends BaseServiceImpl<CpDataResultSscTj> implements CpDataResultSscTjService {

    private static Logger logger = Logger.getLogger(CpDataResultSscTjServiceImpl.class);
    @Autowired
    private CpDataResultSscTjMapper cpDataResultSscTjMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailConfig mailConfig;

    @Override
    public int insert(CpDataResultSscTj cpDataResultSscTj) {

        CpDataResultSscTj cpDataResultSscTjCondition = new CpDataResultSscTj();

        cpDataResultSscTjCondition.setCpDate(cpDataResultSscTj.getCpDate());
        cpDataResultSscTjCondition.setCpQiHao(cpDataResultSscTj.getCpQiHao());
        cpDataResultSscTjCondition.setCpIndex(cpDataResultSscTj.getCpIndex());

        CpDataResultSscTj cpDataResultSscTj1 = cpDataResultSscTjMapper.selectOne(cpDataResultSscTjCondition);

        if(cpDataResultSscTj1 == null){
            return cpDataResultSscTjMapper.insert(cpDataResultSscTj);
        }
        return 0;
    }



    @Override
    public List<CpDataResultView> analyzi(List<String> listIndex,int start,int end,int diffNum,String oneDayMaxQihao) throws ParseException {
        List<CpDataResultView> list = new ArrayList<CpDataResultView>();

        for(String cpIndex : listIndex){
            List<CpDataResultSscTj> cpDataResultSscTjs = cpDataResultSscTjMapper.selectLimit(cpIndex, start, end);

            if(cpDataResultSscTjs != null && cpDataResultSscTjs.size() >= end){
                Set<String> setNum = new HashSet<String>();

                for(CpDataResultSscTj cpDataResultSscTj : cpDataResultSscTjs){
                    setNum.add(cpDataResultSscTj.getCpNum());
                }

                if(setNum.size() <= diffNum){  //七期之内 出了小于五种号

                    ivalidataHaoMa(cpDataResultSscTjs, setNum,diffNum,oneDayMaxQihao);

                    CpDataResultSscTj remove = cpDataResultSscTjs.remove(cpDataResultSscTjs.size() - 1);
                    CpDataResultView cpDataResultView = new CpDataResultView();

                    cpDataResultView.setCpIndex(cpIndex);
                    cpDataResultView.setStartQihao(cpDataResultSscTjs.get((cpDataResultSscTjs.size() - 1)).getCpQiHao());
                    cpDataResultView.setEndQihao(cpDataResultSscTjs.get(0).getCpQiHao());
                    cpDataResultView.setCruHaoMa(cpDataResultSscTjs.get(0).getCpNum());
                    cpDataResultView.setCreateTime(new Date());
                    cpDataResultView.setCreateDate(new Date());
                    cpDataResultView.setCishu(cpDataResultSscTjs.size());

                    Set<String> collect = cpDataResultSscTjs.stream().map(x -> {
                        return x.getCpNum();
                    }).collect(Collectors.toSet());

                    cpDataResultView.setYichu(new ArrayList<>(collect));
                    Set<String> allNum = Constance.getAllNum();
                    allNum.removeAll(collect);
                    cpDataResultView.setWeichu(new ArrayList<>(allNum));

                    cpDataResultView.setStartQiDate(DateUtil.parseDate(cpDataResultSscTjs.get(cpDataResultSscTjs.size() - 1).getCpDate(),DateUtil.PATTERN_DATE));
                    cpDataResultView.setEndQiDate(DateUtil.parseDate(cpDataResultSscTjs.get(0).getCpDate(),DateUtil.PATTERN_DATE));

                    list.add(cpDataResultView);
                }
            }
        }

        return list;
    }

    /**
     * 递归按条件查询
     * @param cpDataResultSscTjs
     * @param setNum
     * @throws ParseException
     */
    private void ivalidataHaoMa(List<CpDataResultSscTj> cpDataResultSscTjs, Set<String> setNum,int diffNum,String oneDayMaxQihao) throws ParseException {
        CpDataResultSscTj cpDataResultSscTj = cpDataResultSscTjs.get(cpDataResultSscTjs.size() - 1);
        int i = Integer.parseInt(cpDataResultSscTj.getCpQiHao()) - 1;
        CpDataResultSscTj cpDataResultSscTjCondition = new CpDataResultSscTj();

        cpDataResultSscTjCondition.setCpIndex(cpDataResultSscTj.getCpIndex());
        cpDataResultSscTjCondition.setCpDate(cpDataResultSscTj.getCpDate());
        cpDataResultSscTjCondition.setCpQiHao(NumUtil.converIntToStringNum(i));

        if(i < 1){
            cpDataResultSscTjCondition.setCpQiHao(oneDayMaxQihao);
            cpDataResultSscTjCondition.setCpDate(
                    DateUtil.formatDate(
                            new Date(
                                    DateUtil.parseDate(cpDataResultSscTj.getCpDate(),DateUtil.PATTERN_DATE).getTime() - (24 * 60 * 60 * 1000)),
                            DateUtil.PATTERN_DATE));
        }

        logger.info("天津上一期的查询参数.......cpDataResultSscTjCondition:" + cpDataResultSscTjCondition.toString());
        CpDataResultSscTj cpDataResultSscTjSelect = cpDataResultSscTjMapper.selectOne(cpDataResultSscTjCondition);

        if(cpDataResultSscTjSelect != null){
            cpDataResultSscTjs.add(cpDataResultSscTjSelect);
            setNum.add(cpDataResultSscTjSelect.getCpNum());

            if(setNum.size() <= diffNum){
                ivalidataHaoMa(cpDataResultSscTjs,setNum,diffNum,oneDayMaxQihao);
            }
        }
    }

    private String getMailSubjectByCpDataResultView(CpDataResultView cpDataResultView) {
        return "天津" + mailConfig.getSubject() + ",日期:" + DateUtil.formatDate(cpDataResultView.getCreateTime(),DateUtil.PATTERN_DATE) +
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

    @Override
    public CpDataResultSscTj selectLastNumByDate(Date date) {
        CpDataResultSscTj cpDataResultSscTj = new CpDataResultSscTj();
        cpDataResultSscTj.setCpDate(DateUtil.formatDate(date,DateUtil.PATTERN_DATE));

        List<CpDataResultSscTj> select = mapper.select(cpDataResultSscTj);
        if(select == null || select.size() == 0){
            return null;
        }

        Collections.sort(select, new Comparator<CpDataResultSscTj>() {
            @Override
            public int compare(CpDataResultSscTj o1, CpDataResultSscTj o2) {
                return o1.getCpQiHao().compareTo(o2.getCpQiHao());
            }
        });

        return select.get(select.size()-1);
    }

    @Override
    public Boolean insertBatch(List<CpDataResultSscTj> cpDataResultSscTj) {

        CpDataResultSscTj cpDataResultSscTjSelect = selectLastNumByDate(new Date());

        Collections.sort(cpDataResultSscTj, new Comparator<CpDataResultSscTj>() {
            @Override
            public int compare(CpDataResultSscTj o1, CpDataResultSscTj o2) {
                return o2.getCpQiHao().compareTo(o1.getCpQiHao());
            }
        });

        if(cpDataResultSscTjSelect == null){
            for(CpDataResultSscTj cpDataResultSscTj2 : cpDataResultSscTj){
                insert(cpDataResultSscTj2);
            }
        }else {
            List<CpDataResultSscTj> collect = cpDataResultSscTj.stream().filter(s -> {
                return s.getCpQiHao().compareTo(cpDataResultSscTjSelect.getCpQiHao()) > 0 && s.getCpDate().compareTo(cpDataResultSscTjSelect.getCpDate()) >= 0;
            }).collect(Collectors.toList());

            if(collect != null){
                for(CpDataResultSscTj c : collect){
                    insert(c);
                }
            }

        }

        return Boolean.TRUE;
    }


}
