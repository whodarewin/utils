package com.hc.utils.converter;

/**
 * Long2Float
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Long2Float implements Converter<Long,Float> {
    @Override
    public Float convert(Long aLong) {
        if(aLong == null){
            return null;
        }
        return aLong.floatValue();
    }
}
