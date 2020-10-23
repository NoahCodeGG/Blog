package cn.noahcode.blog.util;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author NoahCode
 * @date 2020/9/28
 * @description
 */
public class DateUtils {

    public static Date now() {
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }

    public static void main(String[] args) {
        System.out.println(now());
    }

}
