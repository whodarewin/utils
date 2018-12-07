package com.hc.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    private DateUtil(){}

    /**
     *
     * @param pattern 模式
     * @param value 时间str
     * @param unit 需要返回的时间单位
     * @return
     * @throws ParseException
     */
    public long to(String pattern, String value, TimeUnit unit) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(value);
        return unit.convert(date.getTime(), TimeUnit.MILLISECONDS);
    }

    /**
     * 将时间转换为可读字符串
     * @param date 时间
     * @param pattern 模式
     * @return
     */
    public String toDateStr(Date date,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
