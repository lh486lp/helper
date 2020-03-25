package com.earn.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luhui
 * @since 2018/12/23 21:09
 */
@Slf4j
public class StringUtil extends StringUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN_SORT = "yyyyMMdd";
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 判断指定的字符串是否是空指针或空串
     *
     * @param src
     * @return
     */
    public static boolean isNullAndBlank(String src) {
        return "".equals(trimToEmpty(src).intern()) || "null".equals(src);
    }

    public static String isNullDefault(String src, String defaultValue) {
        return "".equals(trimToEmpty(src).intern()) || "null".equals(src) ? defaultValue : src;
    }

    // 把对象转换成String
    public static String toString(Object obj) {
        return ((obj == null) || "".equals(obj) || "null".equals(obj)) ? "" : obj.toString().trim();
    }

    // 转换为数字,只能输入为数字的字符串
    public static int numFormat(Object obj) {
        return ((obj == null) || "".equals(obj) || " ".equals(obj) || "null".equals(obj)) ? 0
            : Integer.parseInt(obj.toString());
    }

    // 转换为long，只能输入为数字的字符串
    public static long longFormat(Object obj) {
        return ((obj == null) || "".equals(obj) || " ".equals(obj) || "null".equals(obj)) ? 0
            : Long.parseLong(obj.toString());
    }

    // 转换为数字,只能输入为数字的字符串
    public static double doubleFormat(Object obj) {
        try {
            return ((obj == null) || "".equals(obj) || " ".equals(obj) || "null".equals(obj)) ? 0
                : Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 字符串日期转为date类型日期
     *
     * @param dateStr
     * @return
     */
    public static Date strToDate(String dateStr) {
        return strToDate(dateStr, null);
    }

    /**
     * 字符串日期转为date类型日期
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date strToDate(String dateStr, String pattern) {
        pattern = isNullDefault(pattern, DATE_TIME_PATTERN);
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 获取时间字符串
     *
     * @return
     */
    public static String dateFormat() {
        return dateFormat(new Date(), DATE_PATTERN_SORT);
    }

    public static String dateFormat(Date date) {
        return dateFormat(date, DATE_PATTERN_SORT);
    }

    public static String dateFormat(String pattern) {
        return dateFormat(new Date(), pattern);
    }

    public static String dateFormat(Date date, String pattern) {
        pattern = isNullDefault(pattern, DATE_PATTERN_SORT);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 得到2日期的间隔天数
     *
     * @param t1
     * @param t2
     * @return
     * @throws ParseException
     */
    public static int getBetweenDays(String t1, String t2) {
        DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        int betweenDays = 0;
        try {
            Date d1 = format.parse(t1);
            Date d2 = format.parse(t2);
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(d1);
            c2.setTime(d2);
            // 保证第二个时间一定大于第一个时间
            if (c1.after(c2)) {
                c1 = c2;
                c2 = Calendar.getInstance();
                c2.setTime(d1);
            }
            Calendar date = (Calendar)c1.clone();
            while (date.before(c2)) {
                date.add(Calendar.DAY_OF_MONTH, 1);
                betweenDays++;
            }
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return betweenDays;
    }
}
