package com.hc.utils.converter;

/**
 * Long2Double
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Long2Double implements Converter<Long, Double> {
    @Override
    public Double convert(Long aLong) {
        if(aLong == null){
            return null;
        }
        return aLong.doubleValue();
    }
}
