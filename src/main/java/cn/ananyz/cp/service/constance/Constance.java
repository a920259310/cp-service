package cn.ananyz.cp.service.constance;

import java.util.HashSet;
import java.util.Set;

public class Constance {
     public static final String[] cpIndexs = new String[]{"1","2","3","4","5"};

     public static Set<String> getAllNum(){
          Set<String> cpNum = new HashSet<String>();
          cpNum.add("1");
          cpNum.add("2");
          cpNum.add("3");
          cpNum.add("4");
          cpNum.add("5");
          cpNum.add("6");
          cpNum.add("7");
          cpNum.add("8");
          cpNum.add("9");
          cpNum.add("0");
          return cpNum;
     }

     public static Set<String> getAllNumBj(){
          Set<String> cpNum = new HashSet<String>();
          cpNum.add("01");
          cpNum.add("02");
          cpNum.add("03");
          cpNum.add("04");
          cpNum.add("05");
          cpNum.add("06");
          cpNum.add("07");
          cpNum.add("08");
          cpNum.add("09");
          cpNum.add("10");
          return cpNum;
     }
}
