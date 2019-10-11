package com.hc.utils.converter;

/**
 * Float2String
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Float2String implements Converter<Float,String> {
    @Override
    public String convert(Float aFloat) {
        if(aFloat == null){
            return null;
        }

        return aFloat.toString();
    }
}
