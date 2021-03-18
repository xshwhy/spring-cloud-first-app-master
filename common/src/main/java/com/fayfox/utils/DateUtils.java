package com.fayfox.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取今天的日期
     * @return String
     */
    public static String getCurrentDateString() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_PATTERN);
        return sdf.format(d);
    }

    /**
     * 获取当前秒级时间戳
     * @return int
     */
    public static int getCurrentTimestamp() {
        return (int)(System.currentTimeMillis() / 1000);
    }
}
