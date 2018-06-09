package cn.ananyz.cp.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cp_parity_analysis")
public class CpParityAnalysis {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 主表主键(cp_data)
     */
    @Column(name = "cp_data_id")
    private Long cpDataId;

    /**
     * 奇偶(1:奇数 2:偶数)
     */
    private Integer parity;

    /**
     * 什么位置(1:万 2:千 3:百 4:十 5:个)
     */
    @Column(name = "index_num")
    private String indexNum;

    @Column(name = "batch_num")
    private Long batchNum;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取主表主键(cp_data)
     *
     * @return cp_data_id - 主表主键(cp_data)
     */
    public Long getCpDataId() {
        return cpDataId;
    }

    /**
     * 设置主表主键(cp_data)
     *
     * @param cpDataId 主表主键(cp_data)
     */
    public void setCpDataId(Long cpDataId) {
        this.cpDataId = cpDataId;
    }

    /**
     * 获取奇偶(1:奇数 2:偶数)
     *
     * @return parity - 奇偶(1:奇数 2:偶数)
     */
    public Integer getParity() {
        return parity;
    }

    /**
     * 设置奇偶(1:奇数 2:偶数)
     *
     * @param parity 奇偶(1:奇数 2:偶数)
     */
    public void setParity(Integer parity) {
        this.parity = parity;
    }

    /**
     * 获取什么位置(1:万 2:千 3:百 4:十 5:个)
     *
     * @return index_num - 什么位置(1:万 2:千 3:百 4:十 5:个)
     */
    public String getIndexNum() {
        return indexNum;
    }

    /**
     * 设置什么位置(1:万 2:千 3:百 4:十 5:个)
     *
     * @param indexNum 什么位置(1:万 2:千 3:百 4:十 5:个)
     */
    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    /**
     * @return batch_num
     */
    public Long getBatchNum() {
        return batchNum;
    }

    /**
     * @param batchNum
     */
    public void setBatchNum(Long batchNum) {
        this.batchNum = batchNum;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "CpParityAnalysis{" +
                "id=" + id +
                ", cpDataId=" + cpDataId +
                ", parity=" + parity +
                ", indexNum='" + indexNum + '\'' +
                ", batchNum=" + batchNum +
                ", createTime=" + createTime +
                '}';
    }
}