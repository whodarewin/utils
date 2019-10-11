package com.hc.utils.converter;

/**
 * Integer2Float
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Integer2Float implements Converter<Integer,Float> {
    @Override
    public Float convert(Integer integer) {
        if(integer == null){
            return null;
        }
        return integer.floatValue();
    }
}
