package cn.ananyz.cp.service.data.collection.model;

/**
 * Created by 王晶 on 2018/6/3.
 */
public class CPDataModel {
    private int wan;
    private int qian;
    private int bai;
    private int shi;
    private int ge;
    private String longDateAndQiHao;
    private String shortQiHao;

    public int getWan() {
        return wan;
    }

    public void setWan(int wan) {
        this.wan = wan;
    }

    public int getQian() {
        return qian;
    }

    public void setQian(int qian) {
        this.qian = qian;
    }

    public int getBai() {
        return bai;
    }

    public void setBai(int bai) {
        this.bai = bai;
    }

    public int getShi() {
        return shi;
    }

    public void setShi(int shi) {
        this.shi = shi;
    }

    public int getGe() {
        return ge;
    }

    public void setGe(int ge) {
        this.ge = ge;
    }

    public String getLongDateAndQiHao() {
        return longDateAndQiHao;
    }

    public String getShortQiHao() {
        return shortQiHao;
    }

    public void setShortQiHao(String shortQiHao) {
        this.shortQiHao = shortQiHao;
    }

    public void setLongDateAndQiHao(String longDateAndQiHao) {
        this.longDateAndQiHao = longDateAndQiHao;
    }

    @Override
    public String toString() {
        return "CPDataModel{" +
                "wan=" + wan +
                ", qian=" + qian +
                ", bai=" + bai +
                ", shi=" + shi +
                ", ge=" + ge +
                ", longDateAndQiHao='" + longDateAndQiHao + '\'' +
                ", shortQiHao='" + shortQiHao + '\'' +
                '}';
    }
}
