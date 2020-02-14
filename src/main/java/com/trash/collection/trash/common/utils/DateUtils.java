package com.trash.collection.trash.common.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     *
     * @param week 周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return 返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 根据身份证号得到年龄
     *
     * @param idCard 身份证号码
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard) {
        int length = idCard.length();
        String dates = "";
        if (length == 18) {
            dates = idCard.substring(6, 10);
        } else {
            dates = idCard.substring(6, 8);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(new Date());
        int age = Integer.parseInt(year) - Integer.parseInt(dates);
        return age;
    }

    /**
     * 根据身份证号得到生日
     *
     * @param idCard 身份证号码
     * @return 年龄
     */
    public static Date getBirthByIdCard(String idCard) {
        int length = idCard.length();
        String dates = "";
        if (length == 18) {
            dates = idCard.substring(6, 14);
        } else {
            dates = "19" + idCard.substring(6, 12);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date birth = null;
        try {
            birth = df.parse(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birth;
    }

    /**
     * 获取日期天数差值
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 天数差
     */
    public static int getDayDifference(Date startDate, Date endDate) {
        long dayTime = endDate.getTime() - startDate.getTime();
        return (int) (dayTime / (24 * 60 * 60 * 1000));
    }

    /**
     * 判断是否同一个月
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date1).equals(sdf.format(date2));
    }

    /**
     * 计算两个时间相差几小时几分
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static String getDatePoor(Date startDate, Date endDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少小时
        long hour = diff / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        return hour + "小时" + min + "分钟";
    }

    /**
     * 判断日期为星期几返回字符串
     *
     * @param date 日期
     * @return
     */
    public static String getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer weekday = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekday.equals(2)) {
            return "星期一";
        } else if (weekday.equals(3)) {
            return "星期二";
        } else if (weekday.equals(4)) {
            return "星期三";
        } else if (weekday.equals(5)) {
            return "星期四";
        } else if (weekday.equals(6)) {
            return "星期五";
        } else if (weekday.equals(7)) {
            return "星期六";
        } else if (weekday.equals(1)) {
            return "星期日";
        } else {
            return "";
        }
    }

}
