package cn.ananyz.cp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BjQhUtilTest {

    static String DATE_CAN_KAO = "2018-07-11 23:57:00";
    int hM = 692338;

    public static void main(String[] args) throws ParseException {
        String targetDate = "2018-07-12 11:02:00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = format.parse(targetDate);
        Date date = format2.parse(DATE_CAN_KAO);
        int days = differentDaysByMillisecond(date, date2);
        System.out.println("两个日期的差距：" + days);

        String replaceAll = targetDate.replaceAll("[0-9][0-9]\\:[0-9][0-9]\\:[0-9][0-9]", "09:07:00");
        System.out.println(replaceAll);

        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date4 = format3.parse(targetDate);
        Date date3 = format4.parse(replaceAll);

        int differentWuMinuteByMillisecond = differentWuMinuteByMillisecond(date3, date4, 1000 * 60 * 5);

        System.out.println(differentWuMinuteByMillisecond);

        int i = differentWuMinuteByMillisecond + 692338;
        int i1 = (days - 1) * 179;

        System.out.println(i + i1);
    }



    /**
     * 通过时间秒毫秒数判断两个时间的日期间隔
     * @param biaoZhunDate
     * @param cruDate
     * @return
     */
    public static int differentDaysByMillisecond(Date biaoZhunDate, Date cruDate) {
        int days = (int) ((cruDate.getTime() - biaoZhunDate.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param oneDayShouCiTime
     * @param cruDate
     * @return
     */
    public static int differentWuMinuteByMillisecond(Date oneDayShouCiTime, Date cruDate,long diffMillisecond) {

        int count = (int) ((cruDate.getTime() - oneDayShouCiTime.getTime()) / diffMillisecond);
        return count;
    }


}
