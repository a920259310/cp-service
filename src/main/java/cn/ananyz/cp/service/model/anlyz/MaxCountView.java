package cn.ananyz.cp.service.model.anlyz;

import java.util.Date;

/**
 * 分析结果视图
 */
public class MaxCountView {

    private int maxCount;//最大连续次数
    private int count;//近期出现的次数
    private Date createTime;//出现时间

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MaxCountView{" +
                "maxCount=" + maxCount +
                ", count=" + count +
                ", createTime=" + createTime +
                '}';
    }
}
