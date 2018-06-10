package cn.ananyz.cp.service.model;

/**
 * Created by 王晶 on 2018/6/10.
 */
public class CpParityAnalysisResult extends CpParityAnalysis{
    private int ciShu;

    public int getCiShu() {
        return ciShu;
    }

    public void setCiShu(int ciShu) {
        this.ciShu = ciShu;
    }

    @Override
    public String toString() {
        return "CpParityAnalysisResult{" +
                "ciShu=" + ciShu +
                '}';
    }
}
