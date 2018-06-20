package cn.ananyz.cp.service.view;

import java.util.Date;
import java.util.List;

/**
 * 分析结果视图
 */
public class CpDataResultView {

    private String cpIndex;//索引位置
    private String endQihao;//结束期号
    private String startQihao;//开始期号
    private String cruHaoMa;//号码
    private Date createTime;//采集时间
    private int cishu;//连续次数
    private List<String> yichu;//已出
    private List<String> weichu;//未出


    public String getCpIndex() {
        return cpIndex;
    }

    public void setCpIndex(String cpIndex) {
        this.cpIndex = cpIndex;
    }

    public String getEndQihao() {
        return endQihao;
    }

    public void setEndQihao(String endQihao) {
        this.endQihao = endQihao;
    }

    public String getStartQihao() {
        return startQihao;
    }

    public void setStartQihao(String startQihao) {
        this.startQihao = startQihao;
    }

    public String getCruHaoMa() {
        return cruHaoMa;
    }

    public void setCruHaoMa(String cruHaoMa) {
        this.cruHaoMa = cruHaoMa;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCishu() {
        return cishu;
    }

    public void setCishu(int cishu) {
        this.cishu = cishu;
    }

    public List<String> getYichu() {
        return yichu;
    }

    public void setYichu(List<String> yichu) {
        this.yichu = yichu;
    }

    public List<String> getWeichu() {
        return weichu;
    }

    public void setWeichu(List<String> weichu) {
        this.weichu = weichu;
    }


    @Override
    public String toString() {
        return "CpDataResultView{" +
                "cpIndex='" + cpIndex + '\'' +
                ", endQihao='" + endQihao + '\'' +
                ", startQihao='" + startQihao + '\'' +
                ", cruHaoMa='" + cruHaoMa + '\'' +
                ", createTime=" + createTime +
                ", cishu=" + cishu +
                ", yichu=" + yichu +
                ", weichu=" + weichu +
                '}';
    }
}
