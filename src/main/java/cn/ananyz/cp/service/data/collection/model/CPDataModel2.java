package cn.ananyz.cp.service.data.collection.model;

import java.util.Date;

public class CPDataModel2 {

    private int num;
    private int index;
    private String longDateAndQiHao;
    private String shortQiHao;
    private Date openTime;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLongDateAndQiHao() {
        return longDateAndQiHao;
    }

    public void setLongDateAndQiHao(String longDateAndQiHao) {
        this.longDateAndQiHao = longDateAndQiHao;
    }

    public String getShortQiHao() {
        return shortQiHao;
    }

    public void setShortQiHao(String shortQiHao) {
        this.shortQiHao = shortQiHao;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    @Override
    public String toString() {
        return "CPDataModel2{" +
                "num=" + num +
                ", index=" + index +
                ", longDateAndQiHao='" + longDateAndQiHao + '\'' +
                ", shortQiHao='" + shortQiHao + '\'' +
                ", openTime=" + openTime +
                '}';
    }
}
