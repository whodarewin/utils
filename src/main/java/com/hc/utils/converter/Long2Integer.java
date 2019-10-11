package com.hc.utils.converter;

/**
 * Long2Integer
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Long2Integer implements Converter<Long,Integer> {
    @Override
    public Integer convert(Long aLong) {
        if(aLong == null){
            return null;
        }
        return aLong.intValue();
    }
}
