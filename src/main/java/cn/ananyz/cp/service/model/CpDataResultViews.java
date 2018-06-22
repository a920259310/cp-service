package cn.ananyz.cp.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cp_data_result_views")
public class CpDataResultViews {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角标
     */
    @Column(name = "cp_index")
    private String cpIndex;

    /**
     * 结束期号
     */
    @Column(name = "end_qi_hao")
    private String endQiHao;

    /**
     * 开始期号
     */
    @Column(name = "start_qi_hao")
    private String startQiHao;

    /**
     * 当前号码
     */
    @Column(name = "cru_hao_ma")
    private String cruHaoMa;

    /**
     * 次数
     */
    private Integer cishu;

    /**
     * 已出号码
     */
    private String yichu;

    /**
     * 未出号码
     */
    private String weichu;

    /**
     * 采集时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 采集日期
     */
    @Column(name = "create_date")
    private Date createDate;

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
     * 获取角标
     *
     * @return cp_index - 角标
     */
    public String getCpIndex() {
        return cpIndex;
    }

    /**
     * 设置角标
     *
     * @param cpIndex 角标
     */
    public void setCpIndex(String cpIndex) {
        this.cpIndex = cpIndex;
    }

    /**
     * 获取结束期号
     *
     * @return end_qi_hao - 结束期号
     */
    public String getEndQiHao() {
        return endQiHao;
    }

    /**
     * 设置结束期号
     *
     * @param endQiHao 结束期号
     */
    public void setEndQiHao(String endQiHao) {
        this.endQiHao = endQiHao;
    }

    /**
     * 获取开始期号
     *
     * @return start_qi_hao - 开始期号
     */
    public String getStartQiHao() {
        return startQiHao;
    }

    /**
     * 设置开始期号
     *
     * @param startQiHao 开始期号
     */
    public void setStartQiHao(String startQiHao) {
        this.startQiHao = startQiHao;
    }

    /**
     * 获取当前号码
     *
     * @return cru_hao_ma - 当前号码
     */
    public String getCruHaoMa() {
        return cruHaoMa;
    }

    /**
     * 设置当前号码
     *
     * @param cruHaoMa 当前号码
     */
    public void setCruHaoMa(String cruHaoMa) {
        this.cruHaoMa = cruHaoMa;
    }

    /**
     * 获取次数
     *
     * @return cishu - 次数
     */
    public Integer getCishu() {
        return cishu;
    }

    /**
     * 设置次数
     *
     * @param cishu 次数
     */
    public void setCishu(Integer cishu) {
        this.cishu = cishu;
    }

    /**
     * 获取已出号码
     *
     * @return yichu - 已出号码
     */
    public String getYichu() {
        return yichu;
    }

    /**
     * 设置已出号码
     *
     * @param yichu 已出号码
     */
    public void setYichu(String yichu) {
        this.yichu = yichu;
    }

    /**
     * 获取未出号码
     *
     * @return weichu - 未出号码
     */
    public String getWeichu() {
        return weichu;
    }

    /**
     * 设置未出号码
     *
     * @param weichu 未出号码
     */
    public void setWeichu(String weichu) {
        this.weichu = weichu;
    }

    /**
     * 获取采集时间
     *
     * @return create_time - 采集时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置采集时间
     *
     * @param createTime 采集时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取采集日期
     *
     * @return create_date - 采集日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置采集日期
     *
     * @param createDate 采集日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}