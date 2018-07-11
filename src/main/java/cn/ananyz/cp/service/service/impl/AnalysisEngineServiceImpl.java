package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.mail.MailConfig;
import cn.ananyz.cp.service.mail.MailService;
import cn.ananyz.cp.service.model.CpData;
import cn.ananyz.cp.service.model.CpParityAnalysis;
import cn.ananyz.cp.service.model.CpParityAnalysisResult;
import cn.ananyz.cp.service.service.AnalysisEngineService;
import cn.ananyz.cp.service.service.CpDataService;
import cn.ananyz.cp.service.service.CpParityAnalysisService;
import cn.ananyz.cp.service.utils.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by 王晶 on 2018/6/10.
 */
@Service
public class AnalysisEngineServiceImpl implements AnalysisEngineService {
    @Autowired
    private CpDataService cpDataService;
    @Autowired
    private CpParityAnalysisService cpParityAnalysisService;
    @Autowired
    private MailService mailService;

    @Autowired
    private MailConfig mailConfig;

    @Override
    public void insert(CpData cpData) throws Exception {

        int insert = cpDataService.insert(cpData);

        if(insert > 0){
            Integer wan = cpData.getWan();
            Integer qian = cpData.getQian();
            Integer bai = cpData.getBai();
            Integer shi = cpData.getShi();
            Integer ge = cpData.getGe();

            jiOuAnalys(cpData,1+"",wan);
            jiOuAnalys(cpData,2+"",qian);
            jiOuAnalys(cpData,3+"",bai);
            jiOuAnalys(cpData,4+"",shi);
            jiOuAnalys(cpData,5+"",ge);
        }



    }

    @Override
    public void analys(String indexNumP,int warnCount) throws Exception {

        CpParityAnalysisResult cpParityAnalysisResult = cpParityAnalysisService.queryCountByIndexNum(indexNumP);
        CpData cpData = cpDataService.queryLastData();

        int ciShu = cpParityAnalysisResult.getCiShu();
        Long batchNum = cpParityAnalysisResult.getBatchNum();

        if(ciShu > warnCount){
            mailService.sendMorePerson(mailConfig.getFrom(),mailConfig.getTo(),"改版前:" + mailConfig.getSubject() + "," +
                            "日期:"+cpData.getCpDate()+",期号:" + cpData.getCpQiHao(),
                    mailConfig.getText() + "第" + indexNumP+ "位,次数:"+ciShu+",批次号:" + batchNum  + ",时间:" + DateUtil.formatDate(cpData.getCreateTime(),DateUtil.PATTERN_DATE_TIME) + ",号码:"
                            + cpData.getWan() + cpData.getQian() + cpData.getBai() + cpData.getShi() + cpData.getGe());
        }

    }

    /**
     * 奇偶数分析
     * @param cpData
     */
    public int jiOuAnalys(CpData cpData,String indexNum,Integer num) throws Exception {
        int i = num % 2;
        CpParityAnalysis cpParityAnalysis = new CpParityAnalysis();
        cpParityAnalysis.setCpDataId(cpData.getId());
        cpParityAnalysis.setIndexNum(indexNum);
        cpParityAnalysis.setCreateTime(new Date());
        cpParityAnalysis.setParity(i);
        cpParityAnalysis.setCpDataId(cpData.getId());

        CpParityAnalysis cpParityAnalysis1 = cpParityAnalysisService.queryLastTimeData(indexNum);
        if(cpParityAnalysis1 != null && i == cpParityAnalysis1.getParity()){
            cpParityAnalysis.setBatchNum(cpParityAnalysis1.getBatchNum());
        }else{
            if(cpParityAnalysis1 == null){
                cpParityAnalysis.setBatchNum(1l);
            }else{
                cpParityAnalysis.setBatchNum(cpParityAnalysis1.getBatchNum() + 1);
            }
        }
        return cpParityAnalysisService.insert(cpParityAnalysis);
    }

}
