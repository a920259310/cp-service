package cn.ananyz.cp.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cp_data_result_ssc_bj")
public class CpDataResultSscBj {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 日期
     */
    @Column(name = "cp_date")
    private String cpDate;

    /**
     * 期号
     */
    @Column(name = "cp_qi_hao")
    private Integer cpQiHao;

    /**
     * 位置
     */
    @Column(name = "cp_index")
    private String cpIndex;

    /**
     * 号码
     */
    @Column(name = "cp_num")
    private String cpNum;

    /**
     * 时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取日期
     *
     * @return cp_date - 日期
     */
    public String getCpDate() {
        return cpDate;
    }

    /**
     * 设置日期
     *
     * @param cpDate 日期
     */
    public void setCpDate(String cpDate) {
        this.cpDate = cpDate;
    }

    /**
     * 获取期号
     *
     * @return cp_qi_hao - 期号
     */
    public Integer getCpQiHao() {
        return cpQiHao;
    }

    /**
     * 设置期号
     *
     * @param cpQiHao 期号
     */
    public void setCpQiHao(Integer cpQiHao) {
        this.cpQiHao = cpQiHao;
    }

    /**
     * 获取位置
     *
     * @return cp_index - 位置
     */
    public String getCpIndex() {
        return cpIndex;
    }

    /**
     * 设置位置
     *
     * @param cpIndex 位置
     */
    public void setCpIndex(String cpIndex) {
        this.cpIndex = cpIndex;
    }

    /**
     * 获取号码
     *
     * @return cp_num - 号码
     */
    public String getCpNum() {
        return cpNum;
    }

    /**
     * 设置号码
     *
     * @param cpNum 号码
     */
    public void setCpNum(String cpNum) {
        this.cpNum = cpNum;
    }

    /**
     * 获取时间
     *
     * @return create_time - 时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置时间
     *
     * @param createTime 时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CpDataResultSscBj{" +
                "id=" + id +
                ", cpDate='" + cpDate + '\'' +
                ", cpQiHao=" + cpQiHao +
                ", cpIndex='" + cpIndex + '\'' +
                ", cpNum='" + cpNum + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}