package com.hc.utils.converter;

/**
 * Integer2String
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class Integer2String implements Converter<Integer,String>{

    @Override
    public String convert(Integer integer) {
        if(integer == null){
            return null;
        }
        return integer.toString();
    }
}
