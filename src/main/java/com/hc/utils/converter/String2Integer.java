package com.hc.utils.converter;


import org.apache.commons.lang3.StringUtils;

/**
 * String2Integer
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class String2Integer implements Converter<String,Integer> {
    @Override
    public Integer convert(String s) {
        if(StringUtils.isBlank(s)){
            return null;
        }
        return Integer.parseInt(s);
    }
}
