package com.hc.utils.converter;

/**
 * Integer2Double
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Integer2Double implements Converter<Integer, Double> {
    @Override
    public Double convert(Integer integer) {
        if(integer == null){
            return null;
        }
        return integer.doubleValue();
    }
}
