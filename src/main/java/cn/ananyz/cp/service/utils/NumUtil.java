package cn.ananyz.cp.service.utils;

public class NumUtil {
    public static String converIntToStringNum(Integer qihao){

        String result = "";

        String s = "" + qihao;
        switch (s.length()){
            case 0:
                result = "000";
                break;
            case 1:
                result = "00" + s;
                break;
            case 2:
                result = "0" + s;
                break;
            case 3:
                result = "" + s;
                break;
            default:
                result = s;
                break;
        }

        return result;
    }
}
