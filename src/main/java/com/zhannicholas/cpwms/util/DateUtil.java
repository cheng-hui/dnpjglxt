package com.zhannicholas.cpwms.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
    private DateUtil(){}

    /**
     * 将当前系统时间转为 java.sql.Date
     */
    public static java.sql.Date fromUtilDate(java.util.Date utilDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(utilDate);
        java.util.Date timeDate = null;
        try {
            timeDate = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.sql.Date(timeDate.getTime());
    }

    /**
     * 将时间字符串转为 java.sql.Date
     * @param dateStr   时间字符串
     * @param isEndDate 是否是结束时间
     * @return  java.sql.Date 形式的时间
     */
    public static java.sql.Date fromDateStr(String dateStr, boolean isEndDate){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        if(isEndDate){
            return fromUtilDate(new java.util.Date(date.getTime() + 24 * 60 * 60 * 1000 - 1));
        }
        return fromUtilDate(new java.util.Date(date.getTime()));
    }
}
