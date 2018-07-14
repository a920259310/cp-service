package cn.ananyz.cp.service.config;

public class CpDataResultSscBjConfig extends BaseConfig{
    private Long sleepTime;

    public Long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public String toString() {
        return "CpDataResultSscBjConfig{" +
                "sleepTime=" + sleepTime +
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
