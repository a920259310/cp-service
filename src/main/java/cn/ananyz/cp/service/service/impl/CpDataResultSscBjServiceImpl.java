package cn.ananyz.cp.service.service.impl;

import cn.ananyz.cp.service.constance.Constance;
import cn.ananyz.cp.service.data.collection.model.CPDataModel2;
import cn.ananyz.cp.service.data.collection.parse.CpApiBj;
import cn.ananyz.cp.service.mapper.CpDataResultSscBjMapper;
import cn.ananyz.cp.service.model.CpDataResultSscBj;
import cn.ananyz.cp.service.service.CpDataResultSscBjService;
import cn.ananyz.cp.service.utils.DateUtil;
import cn.ananyz.cp.service.view.CpDataResultView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CpDataResultSscBjServiceImpl extends BaseServiceImpl<CpDataResultSscBj> implements CpDataResultSscBjService {

    private static Logger logger = Logger.getLogger(CpDataResultSscBjServiceImpl.class);

    @Autowired
    private CpApiBj cpApiBj;

    @Autowired
    private CpDataResultSscBjMapper cpDataResultSscBjMapper;

    @Override
    public int insert(CpDataResultSscBj cpDataResultSscBj) {

        if(cpDataResultSscBj == null){
            return 0;
        }

        CpDataResultSscBj cpDataResultSscBjCondition = new CpDataResultSscBj();
        cpDataResultSscBjCondition.setCpQiHao(cpDataResultSscBj.getCpQiHao());
        cpDataResultSscBjCondition.setCpIndex(cpDataResultSscBj.getCpIndex());
        CpDataResultSscBj cpDataResultSscBj1 = mapper.selectOne(cpDataResultSscBjCondition);

        if(cpDataResultSscBj1 == null){
            return mapper.insert(cpDataResultSscBj);
        }

        return 0;
    }

    @Override
    public List<CpDataResultView> analyzi(List<String> listIndex, int start, int end, int diffNum) throws ParseException {
        List<CpDataResultView> list = new ArrayList<CpDataResultView>();

        for(String cpIndex : listIndex){
            List<CpDataResultSscBj> cpDataResultSscBjs = cpDataResultSscBjMapper.selectLimit(cpIndex, start, end);

            if(cpDataResultSscBjs != null && cpDataResultSscBjs.size() >= end){
                Set<String> setNum = new HashSet<String>();

                for(CpDataResultSscBj cpDataResultSscBj : cpDataResultSscBjs){
                    setNum.add(cpDataResultSscBj.getCpNum());
                }

                if(setNum.size() <= diffNum){  //七期之内 出了小于五种号

                    ivalidataHaoMa(cpDataResultSscBjs, setNum,diffNum);

                    CpDataResultSscBj remove = cpDataResultSscBjs.remove(cpDataResultSscBjs.size() - 1);
                    CpDataResultView cpDataResultView = new CpDataResultView();

                    cpDataResultView.setCpIndex(cpIndex);
                    cpDataResultView.setStartQihao(cpDataResultSscBjs.get((cpDataResultSscBjs.size() - 1)).getCpQiHao() + "");
                    cpDataResultView.setEndQihao(cpDataResultSscBjs.get(0).getCpQiHao() + "");
                    cpDataResultView.setCruHaoMa(cpDataResultSscBjs.get(0).getCpNum());
                    cpDataResultView.setCreateTime(new Date());
                    cpDataResultView.setCreateDate(new Date());
                    cpDataResultView.setCishu(cpDataResultSscBjs.size());

                    Set<String> collect = cpDataResultSscBjs.stream().map(x -> {
                        return x.getCpNum();
                    }).collect(Collectors.toSet());

                    cpDataResultView.setYichu(new ArrayList<>(collect));
                    Set<String> allNum = Constance.getAllNumBj();
                    allNum.removeAll(collect);
                    cpDataResultView.setWeichu(new ArrayList<>(allNum));

                    cpDataResultView.setStartQiDate(DateUtil.parseDate(cpDataResultSscBjs.get(cpDataResultSscBjs.size() - 1).getCpDate(),DateUtil.PATTERN_DATE));
                    cpDataResultView.setEndQiDate(DateUtil.parseDate(cpDataResultSscBjs.get(0).getCpDate(),DateUtil.PATTERN_DATE));

                    list.add(cpDataResultView);
                }
            }
        }

        return list;

    }


    /**
     * 递归按条件查询
     * @param cpDataResultSscBjs
     * @param setNum
     * @throws ParseException
     */
    private void ivalidataHaoMa(List<CpDataResultSscBj> cpDataResultSscBjs, Set<String> setNum,int diffNum) throws ParseException {
        CpDataResultSscBj cpDataResultSscBj = cpDataResultSscBjs.get(cpDataResultSscBjs.size() - 1);
        int i = cpDataResultSscBj.getCpQiHao() - 1;
        CpDataResultSscBj cpDataResultSscBjCondition = new CpDataResultSscBj();

        cpDataResultSscBjCondition.setCpIndex(cpDataResultSscBj.getCpIndex());
        cpDataResultSscBjCondition.setCpQiHao(i);


        logger.info("北京上一期的查询参数.......cpDataResultSscBjCondition:" + cpDataResultSscBjCondition.toString());
        CpDataResultSscBj cpDataResultSscBjSelect = cpDataResultSscBjMapper.selectOne(cpDataResultSscBjCondition);

        if(cpDataResultSscBjSelect != null){
            cpDataResultSscBjs.add(cpDataResultSscBjSelect);
            setNum.add(cpDataResultSscBjSelect.getCpNum());

            if(setNum.size() <= diffNum){
                ivalidataHaoMa(cpDataResultSscBjs,setNum,diffNum);
            }
        }else{
            logger.info("有未采集到的号  cpDataResultSscBjCondition:" + cpDataResultSscBjCondition);
        }

    }



    @Override
    public CpDataResultSscBj selectLastNum(Date date) {
        CpDataResultSscBj cpDataResultSscBjCondition = new CpDataResultSscBj();
        cpDataResultSscBjCondition.setCpDate(DateUtil.formatDate(date,DateUtil.PATTERN_DATE));

        List<CpDataResultSscBj> select = mapper.select(cpDataResultSscBjCondition);

        if(select == null || select.size() ==0){
            return null;
        }

        return select.stream().max(new Comparator<CpDataResultSscBj>() {
            @Override
            public int compare(CpDataResultSscBj o1, CpDataResultSscBj o2) {
                return o1.getCpQiHao().compareTo(o2.getCpQiHao());
            }
        }).get();
    }

    @Override
    public Boolean insertBatch(List<CpDataResultSscBj> cpDataResultSscBj) {
        Collections.sort(cpDataResultSscBj, new Comparator<CpDataResultSscBj>() {
            @Override
            public int compare(CpDataResultSscBj o1, CpDataResultSscBj o2) {
                return o1.getCpQiHao().compareTo(o2.getCpQiHao());
            }
        });

        for (CpDataResultSscBj cp : cpDataResultSscBj
             ) {
            this.insert(cp);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean insertLastNumInsertMysqlByDate(Date date, int delayMinute) throws IOException, ParseException {

        List<CPDataModel2> todayAllDatas = cpApiBj.getTodayAllDatas(date);

        CpDataResultSscBj cpDataResultSscBj = this.selectLastNum(DateUtil.add(date, Calendar.MINUTE, delayMinute));
        if(cpDataResultSscBj == null){
            List<CpDataResultSscBj> list = this.convertCPDataModel2ToCpDataResultSscBjs(todayAllDatas);
            this.insertBatch(list);
        }else {
            List<CPDataModel2> collect = todayAllDatas.stream().filter(x -> {
                return x.getShortQiHao().compareTo(cpDataResultSscBj.getCpQiHao() + "") > 0;
            }).collect(Collectors.toList());
            this.insertBatch(this.convertCPDataModel2ToCpDataResultSscBjs(collect));
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean insertByQihaoInsertMysql(Long sleepTime) throws IOException, ParseException {

        CpDataResultSscBj cpDataResultSscBj = this.selectLastNum(new Date());

        if(cpDataResultSscBj == null){
            this.insertLastNumInsertMysqlByDate(new Date(),-3);
        }

        CpDataResultSscBj cpDataResultSscBj2 = this.selectLastNum(new Date());
        logger.info("北京最大值: cpDataResultSscBj2:" + cpDataResultSscBj2);
        Boolean flag = cpDataResultSscBj2 == null ? Boolean.FALSE : Boolean.TRUE;

        while (flag){
            logger.info("循环查询隐藏值   cpDataResultSscBj2:" + cpDataResultSscBj2);
            List<CpDataResultSscBj> list = insertPreDataByLastData(cpDataResultSscBj2);
            insertBatch(list);
            if(list == null || list.size() == 0){
                flag = false;
            }else {
                cpDataResultSscBj2.setCpQiHao(list.get(0).getCpQiHao());
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public List<CpDataResultSscBj> insertPreDataByLastData(CpDataResultSscBj cpDataResultSscBj) throws IOException, ParseException {

        if(cpDataResultSscBj == null){
            return null;
        }

        List<CPDataModel2> dataByQiHao = cpApiBj.getDataByQiHao(cpDataResultSscBj.getCpQiHao() + 1);

        return convertCPDataModel2ToCpDataResultSscBjs(dataByQiHao);
    }


    private List<CpDataResultSscBj> convertCPDataModel2ToCpDataResultSscBjs(List<CPDataModel2> cpDataModel2s){
        List<CpDataResultSscBj> list = new ArrayList<>();

        if(cpDataModel2s == null || cpDataModel2s.size() == 0){
            return list;
        }


        for (CPDataModel2 cp : cpDataModel2s) {
            CpDataResultSscBj cpDataResultSscBj = this.convertCPDataModel2ToCpDataResultSscBj(cp);
            list.add(cpDataResultSscBj);
        }
        return list;
    }


    private CpDataResultSscBj convertCPDataModel2ToCpDataResultSscBj(CPDataModel2 cpDataModel2){
        if(cpDataModel2 == null) return null;

        CpDataResultSscBj cpDataResultSscBj = new CpDataResultSscBj();

        cpDataResultSscBj.setCpDate(DateUtil.formatDate(cpDataModel2.getOpenTime(),DateUtil.PATTERN_DATE));
        cpDataResultSscBj.setCpQiHao(Integer.parseInt(cpDataModel2.getShortQiHao()));
        cpDataResultSscBj.setCpIndex(cpDataModel2.getIndex() + "");
        cpDataResultSscBj.setCreateTime(cpDataModel2.getOpenTime());
        cpDataResultSscBj.setCpNum(cpDataModel2.getNum() + "");

        return cpDataResultSscBj;
    }

}
