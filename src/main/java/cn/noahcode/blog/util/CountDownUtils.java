package cn.noahcode.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author NoahCode
 * @date 2020/10/7
 * @description
 */
public class CountDownUtils {

    private static long day = 0;
    private static long hour = 0;
    private static long minute = 0;
    private static long second = 0;

    private static boolean dayNotAlready = false;
    private static boolean hourNotAlready = false;
    private static boolean minuteNotAlready = false;
    private static boolean secondNotAlready = false;


    /**
     * @return java.lang.String
     * @description
     * @author NoahCode
     * @date 2020/10/7
     * @param: overTime 目标天数
     * @param: currentDateTime 当前时间
     */
    public static String getCountDown(Date overTime, Date currentDateTime) {
        Long totalSeconds = getTimeDiffSecond(overTime, currentDateTime.toString());
        if (totalSeconds > 0) {
            secondNotAlready = true;
            second = totalSeconds;
            if (second >= 60) {
                minuteNotAlready = true;
                minute = second / 60;
                second = second % 60;
                if (minute >= 60) {
                    hourNotAlready = true;
                    hour = minute / 60;
                    minute = minute % 60;
                    if (hour > 24) {
                        dayNotAlready = true;
                        day = hour / 24;
                        hour = hour % 24;
                    }
                }
            }
        }
        if (day != 0) {
            return day + " 天";
        } else if (hour != 0) {
            return hour + " 小时";
        } else if (minute != 0) {
            return minute + " 分钟";
        } else {
            return second + " 秒";
        }
    }

    private static long getTimeDiffSecond(Date date, String date2) {
        if (date == null || date.equals("")) {
            return 0;
        }
        if (date2 == null || date2.equals("")) {
            return 0;
        }
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        java.util.Date myDate = null;
        try {
            myDate = myFormatter.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long day = (myDate.getTime() - date.getTime()) / 1000;
        return day;
    }

}
