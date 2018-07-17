package cn.ananyz.cp.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cp_data_sys_config")
public class CpDataSysConfig {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 1:重 2:北 3:天
     */
    @Column(name = "game_type")
    private String gameType;

    /**
     * 计划次数
     */
    @Column(name = "plan_count")
    private Integer planCount;

    /**
     * 统计最大连续次数时,使用最近几天的数据
     */
    @Column(name = "recent_day")
    private Integer recentDay;

    /**
     * 预计最大次数大于max_count多少次
     */
    @Column(name = "lg_max_count")
    private Integer lgMaxCount;

    /**
     * 最近N天的最大次数
     */
    @Column(name = "max_count")
    private Integer maxCount;

    /**
     * 最近N天最大次数出现的次数
     */
    @Column(name = "max_count_count")
    private Integer maxCountCount;

    /**
     * 最近N天最大次数出现的最大时间
     */
    @Column(name = "max_count_date_time_max")
    private Date maxCountDateTimeMax;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取1:重 2:北 3:天
     *
     * @return game_type - 1:重 2:北 3:天
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * 设置1:重 2:北 3:天
     *
     * @param gameType 1:重 2:北 3:天
     */
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    /**
     * 获取计划次数
     *
     * @return plan_count - 计划次数
     */
    public Integer getPlanCount() {
        return planCount;
    }

    /**
     * 设置计划次数
     *
     * @param planCount 计划次数
     */
    public void setPlanCount(Integer planCount) {
        this.planCount = planCount;
    }

    /**
     * 获取统计最大连续次数时,使用最近几天的数据
     *
     * @return recent_day - 统计最大连续次数时,使用最近几天的数据
     */
    public Integer getRecentDay() {
        return recentDay;
    }

    /**
     * 设置统计最大连续次数时,使用最近几天的数据
     *
     * @param recentDay 统计最大连续次数时,使用最近几天的数据
     */
    public void setRecentDay(Integer recentDay) {
        this.recentDay = recentDay;
    }

    /**
     * 获取预计最大次数大于max_count多少次
     *
     * @return lg_max_count - 预计最大次数大于max_count多少次
     */
    public Integer getLgMaxCount() {
        return lgMaxCount;
    }

    /**
     * 设置预计最大次数大于max_count多少次
     *
     * @param lgMaxCount 预计最大次数大于max_count多少次
     */
    public void setLgMaxCount(Integer lgMaxCount) {
        this.lgMaxCount = lgMaxCount;
    }

    /**
     * 获取最近N天的最大次数
     *
     * @return max_count - 最近N天的最大次数
     */
    public Integer getMaxCount() {
        return maxCount;
    }

    /**
     * 设置最近N天的最大次数
     *
     * @param maxCount 最近N天的最大次数
     */
    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * 获取最近N天最大次数出现的次数
     *
     * @return max_count_count - 最近N天最大次数出现的次数
     */
    public Integer getMaxCountCount() {
        return maxCountCount;
    }

    /**
     * 设置最近N天最大次数出现的次数
     *
     * @param maxCountCount 最近N天最大次数出现的次数
     */
    public void setMaxCountCount(Integer maxCountCount) {
        this.maxCountCount = maxCountCount;
    }

    /**
     * 获取最近N天最大次数出现的最大时间
     *
     * @return max_count_date_time_max - 最近N天最大次数出现的最大时间
     */
    public Date getMaxCountDateTimeMax() {
        return maxCountDateTimeMax;
    }

    /**
     * 设置最近N天最大次数出现的最大时间
     *
     * @param maxCountDateTimeMax 最近N天最大次数出现的最大时间
     */
    public void setMaxCountDateTimeMax(Date maxCountDateTimeMax) {
        this.maxCountDateTimeMax = maxCountDateTimeMax;
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

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}