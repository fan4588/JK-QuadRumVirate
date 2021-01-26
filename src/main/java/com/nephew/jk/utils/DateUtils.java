package com.nephew.jk.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils {
    public static String DATE_PATTERN = "yyyy-MM-dd";
    public static String TIME_PATTERN = DATE_PATTERN + " HH:mm:ss";

    /**
     * 获取当前系统时间
     */
    public static Date getCurrentDateSys() {
        return Calendar.getInstance().getTime();
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        }
    }

    public static String formatDate(Date date) {
        return formatDate(date, DATE_PATTERN);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, TIME_PATTERN);
    }

    public static Date parseDate(String strDate, String format) {
        try {
            if (strDate != null && !strDate.equals("")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                return simpleDateFormat.parse(strDate);
            } else {
                return null;
            }
        } catch (Exception var3) {
            return new Date();
        }
    }

    public static Date parseDate(String strDate) {
        return parseDate(strDate, DATE_PATTERN);
    }

    public static Date parseDateTime(String strDate) {
        //return parseDate(strDate, TIME_PATTERN);
        Date result;
        String parse = strDate.replaceFirst("[0-9]{4}([^0-9]?)", "yyyy$1");
        parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        DateFormat format = new SimpleDateFormat(parse);
        try {
            result = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return result;

    }


    /**
     * 计算 second 秒后的时间
     *
     * @param date
     * @param second
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 计算 minute 分钟后的时间
     *
     * @param date
     * @param minute
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 计算 hour 小时后的时间
     *
     * @param date
     * @param hour
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）天数
     *
     * @param date 输入日期
     * @param iday 要增加或减少的天数
     */
    public static Date addDay(Date date, int iday) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.DAY_OF_MONTH, iday);
        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）月份
     *
     * @param date   输入日期
     * @param imonth 要增加或减少的月分数
     */
    public static Date addMonth(Date date, int imonth) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.MONTH, imonth);
        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）年份
     *
     * @param date  输入日期
     * @param iyear 要增加或减少的年数
     */
    public static Date addYear(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.add(Calendar.YEAR, iyear);
        return cd.getTime();
    }

    public static String getTimePattern() {
        return TIME_PATTERN;
    }


    /**
     * 获取一个月开始的时间
     *
     * @param date
     * @return
     */
    public static Date getStartOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int month = cd.get(Calendar.MONTH);
        int year = cd.get(Calendar.YEAR);
        Calendar resCd = Calendar.getInstance();
        resCd.set(Calendar.YEAR, year);
        resCd.set(Calendar.MONTH, month);
        resCd.set(Calendar.DAY_OF_MONTH, 1);
        resCd.set(Calendar.HOUR_OF_DAY, 0);
        resCd.set(Calendar.MINUTE, 0);
        resCd.set(Calendar.SECOND, 0);

        return resCd.getTime();
    }

    /***
     * 获取一个月结束的时间
     */
    public static Date getEndOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int month = cd.get(Calendar.MONTH);
        int year = cd.get(Calendar.YEAR);
        Calendar resCd = Calendar.getInstance();
        resCd.set(Calendar.YEAR, year);
        resCd.set(Calendar.MONTH, month);
        resCd.set(Calendar.DAY_OF_MONTH, 1);
        resCd.set(Calendar.HOUR_OF_DAY, 0);
        resCd.set(Calendar.MINUTE, 0);
        resCd.set(Calendar.SECOND, 0);
        resCd.add(Calendar.MONTH, 1);
        resCd.add(Calendar.SECOND, -1);
        return resCd.getTime();
    }

    /**
     * 获取下个月同一天
     */
    public static String getTodayNextMonthStr() {
        LocalDate today = LocalDate.now();
        // LocalDate today = LocalDate.of(2019,1,30);
        LocalDate targetDate = today.plusMonths(1);
        System.out.println(targetDate.toString());
        return targetDate.toString();
    }

    /** 获取前一天最后时刻 **/
    public static Date getEndOfLastDay(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int day = cd.get(Calendar.DAY_OF_MONTH);
        int month = cd.get(Calendar.MONTH);
        int year = cd.get(Calendar.YEAR);
        Calendar resCd = Calendar.getInstance();
        resCd.set(Calendar.YEAR, year);
        resCd.set(Calendar.MONTH, month);
        resCd.set(Calendar.DAY_OF_MONTH, day - 1);
        resCd.set(Calendar.HOUR_OF_DAY, 23);
        resCd.set(Calendar.MINUTE, 59);
        resCd.set(Calendar.SECOND, 59);
        return resCd.getTime();
    }
    /** 获取明天开始时刻 **/
    public static Date getStartOfNextDay(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int day = cd.get(Calendar.DAY_OF_MONTH);
        int month = cd.get(Calendar.MONTH);
        int year = cd.get(Calendar.YEAR);
        Calendar resCd = Calendar.getInstance();
        resCd.set(Calendar.YEAR, year);
        resCd.set(Calendar.MONTH, month);
        resCd.set(Calendar.DAY_OF_MONTH, day+1);
        resCd.set(Calendar.HOUR_OF_DAY, 0);
        resCd.set(Calendar.MINUTE, 0);
        resCd.set(Calendar.SECOND, 0);
        return resCd.getTime();
    }

    /**
     * 获取一个月有多少周
     */
    public static int getWeekOfMonth(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取一个月有多少天
     */
    public static int getDayOfMonth(Date date){
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) {
        /*Calendar resCd = Calendar.getInstance();
        resCd.setTime(new Date());
        resCd.set(Calendar.DAY_OF_MONTH, 31);
        resCd.getTime();*/

        /*String s = DateUtils.formatDateTime(new Date());
        Date date = DateUtils.parseDateTime(s);
        System.out.println(date);
        Date date1 = DateUtils.addMinute(new Date(), -30);
        System.out.println(DateUtils.formatDateTime(date1));*/

        int weekOfMonth = getWeekOfMonth(new Date());
        int dayOfMonth = getDayOfMonth(new Date());
        System.out.println(weekOfMonth);
        System.out.println(dayOfMonth);

    }

}
