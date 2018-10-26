package cn.ananyz.cp.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

/**
 * Created by 王晶 on 2018/6/10.
 */
public class DateUtil {
    public static String PATTERN_DATE="yyyy-MM-dd";
    public static String PATTERN_DATE_TIME="yyyy-MM-dd HH:mm:ss";
    public static String PATTERN_DATE_TIME_="yyyy/MM/dd HH:mm:ss";
    public static String PATTERN_DATE_NOT="yyyyMMdd";


    public static String formatDate(Date date,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(String date,String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(date);
    }

    public static Date add(Date date,int ymd,int amout) throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(ymd,amout);
        Date rightNowTime = rightNow.getTime();
        return rightNowTime;
    }

    public static void main(String[] args) throws ParseException {
        Date add = add(new Date(), Calendar.DAY_OF_MONTH, -10);
        System.out.println(formatDate(add,PATTERN_DATE_TIME));
    }

}
