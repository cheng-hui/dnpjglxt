package com.zhannicholas.cpwms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
    private DateUtil(){}

    /**
     * 将当前系统时间转为 java.sql.Date
     */
    public static java.sql.Date fromUtilDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new java.util.Date());
        java.util.Date timeDate = null;
        try {
            timeDate = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.sql.Date(timeDate.getTime());
    }
}
