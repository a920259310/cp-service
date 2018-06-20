package cn.ananyz.cp.service.config;

import java.util.List;

public class CpDataResultConfig {

    private List<String> listIndex;
    private int start;
    private int end;
    private int diffNum;


    public int getDiffNum() {
        return diffNum;
    }

    public void setDiffNum(int diffNum) {
        this.diffNum = diffNum;
    }

    public List<String> getListIndex() {
        return listIndex;
    }

    public void setListIndex(List<String> listIndex) {
        this.listIndex = listIndex;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }


    @Override
    public String toString() {
        return "CpDataResultConfig{" +
                "listIndex=" + listIndex +
                ", start=" + start +
                ", end=" + end +
                ", diffNum=" + diffNum +
                '}';
    }
}
