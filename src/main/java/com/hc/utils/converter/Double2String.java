package com.hc.utils.converter;

/**
 * Double2String
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Double2String implements Converter<Double,String> {
    @Override
    public String convert(Double aDouble) {
        if(aDouble == null){
            return null;
        }
        return aDouble.toString();
    }
}
