package com.hc.utils.converter;

import org.apache.commons.lang3.StringUtils;

/**
 * String2Float
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class String2Float implements Converter<String,Float> {
    @Override
    public Float convert(String s) {
        if(StringUtils.isBlank(s)){
            return null;
        }
        return Float.parseFloat(s);
    }
}
