package cn.ananyz.cp.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cp_data")
public class CpData {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 长期号 如:(20180101-120)
     */
    @Column(name = "cp_date")
    private String cpDate;

    /**
     * 短期号 如:(120)
     */
    @Column(name = "cp_qi_hao")
    private Integer cpQiHao;

    /**
     * 万
     */
    private Integer wan;

    /**
     * 千
     */
    private Integer qian;

    /**
     * 百
     */
    private Integer bai;

    /**
     * 十
     */
    private Integer shi;

    /**
     * 个
     */
    private Integer ge;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpDate() {
        return cpDate;
    }

    public void setCpDate(String cpDate) {
        this.cpDate = cpDate;
    }

    /**
     * 获取短期号 如:(120)
     *
     * @return cp_qi_hao - 短期号 如:(120)
     */
    public Integer getCpQiHao() {
        return cpQiHao;
    }

    /**
     * 设置短期号 如:(120)
     *
     * @param cpQiHao 短期号 如:(120)
     */
    public void setCpQiHao(Integer cpQiHao) {
        this.cpQiHao = cpQiHao;
    }

    /**
     * 获取万
     *
     * @return wan - 万
     */
    public Integer getWan() {
        return wan;
    }

    /**
     * 设置万
     *
     * @param wan 万
     */
    public void setWan(Integer wan) {
        this.wan = wan;
    }

    /**
     * 获取千
     *
     * @return qian - 千
     */
    public Integer getQian() {
        return qian;
    }

    /**
     * 设置千
     *
     * @param qian 千
     */
    public void setQian(Integer qian) {
        this.qian = qian;
    }

    /**
     * 获取百
     *
     * @return bai - 百
     */
    public Integer getBai() {
        return bai;
    }

    /**
     * 设置百
     *
     * @param bai 百
     */
    public void setBai(Integer bai) {
        this.bai = bai;
    }

    /**
     * 获取十
     *
     * @return shi - 十
     */
    public Integer getShi() {
        return shi;
    }

    /**
     * 设置十
     *
     * @param shi 十
     */
    public void setShi(Integer shi) {
        this.shi = shi;
    }

    /**
     * 获取个
     *
     * @return ge - 个
     */
    public Integer getGe() {
        return ge;
    }

    /**
     * 设置个
     *
     * @param ge 个
     */
    public void setGe(Integer ge) {
        this.ge = ge;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "CpData{" +
                "id=" + id +
                ", cpDate=" + cpDate +
                ", cpQiHao=" + cpQiHao +
                ", wan=" + wan +
                ", qian=" + qian +
                ", bai=" + bai +
                ", shi=" + shi +
                ", ge=" + ge +
                ", createTime=" + createTime +
                '}';
    }
}