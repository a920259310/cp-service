package cn.ananyz.cp.service.config;

import java.util.List;

public class BaseConfig {

    protected String subJect; // 类型

    protected List<String> listIndex;//索引位置
    protected int start;    //最近起始
    protected int end;     //最近结束
    protected int diffNum;  //分号

    protected String oneDayLastQihao;  //最大期数
    protected int warnCount;    //连续多少次告警

    protected Boolean isInitTodayData;   //是否初始化今日数据
    protected Boolean isSchedule;   //是否调度

    public Boolean getInitTodayData() {
        return isInitTodayData;
    }

    public void setInitTodayData(Boolean initTodayData) {
        isInitTodayData = initTodayData;
    }

    public Boolean getSchedule() {
        return isSchedule;
    }

    public void setSchedule(Boolean schedule) {
        isSchedule = schedule;
    }

    public int getDiffNum() {
        return diffNum;
    }

    public void setDiffNum(int diffNum) {
        this.diffNum = diffNum;
    }

    public List<String> getListIndex() {
        return listIndex;
    }

    public void setListIndex(List<String> listIndex) {
        this.listIndex = listIndex;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getOneDayLastQihao() {
        return oneDayLastQihao;
    }

    public void setOneDayLastQihao(String oneDayLastQihao) {
        this.oneDayLastQihao = oneDayLastQihao;
    }

    public int getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(int warnCount) {
        this.warnCount = warnCount;
    }

    public String getSubJect() {
        return subJect;
    }

    public void setSubJect(String subJect) {
        this.subJect = subJect;
    }

    @Override
    public String toString() {
        return "BaseConfig{" +
                "subJect='" + subJect + '\'' +
                ", listIndex=" + listIndex +
                ", start=" + start +
                ", end=" + end +
                ", diffNum=" + diffNum +
                ", oneDayLastQihao='" + oneDayLastQihao + '\'' +
                ", warnCount=" + warnCount +
                ", isInitTodayData=" + isInitTodayData +
                ", isSchedule=" + isSchedule +
                '}';
    }
}
