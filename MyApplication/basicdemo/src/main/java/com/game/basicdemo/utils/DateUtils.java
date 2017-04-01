package com.game.basicdemo.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 2016/12/1.
 */

public class DateUtils {
    private static int NUMBER = 500;
    //根据日期生成一个随机数用来显示更新到多少集
    public static int getRandomNumber() {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date d1= null;
        try {
            d1 = format.parse("2016-10-08");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String currentDateString = format.format(new Date());
        try {
            Date d2 = format.parse(currentDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int between = 0;
        try {
            between = daysBetween(d1,new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return NUMBER+ (int)(between*1.2)+(int)(Math.random() * ((100 - 1) + 1));
    }
    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     *字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
