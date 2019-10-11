package com.hc.utils.converter;

/**
 * Integer2Long
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Integer2Long implements Converter<Integer,Long> {
    @Override
    public Long convert(Integer integer) {
        if(integer == null){
            return null;
        }
        return integer.longValue();
    }
}
