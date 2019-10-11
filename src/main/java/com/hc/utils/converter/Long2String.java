package com.hc.utils.converter;

/**
 * Long2String
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Long2String implements Converter<Long,String> {
    @Override
    public String convert(Long aLong) {
        if(aLong == null){
            return null;

        }
        return aLong.toString();
    }
}
