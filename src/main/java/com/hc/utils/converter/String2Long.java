package com.hc.utils.converter;

import org.apache.commons.lang3.StringUtils;

/**
 * String2Long
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class String2Long implements Converter<String,Long> {
    @Override
    public Long convert(String s) {
        if(StringUtils.isBlank(s)){
            return null;
        }
        return Long.parseLong(s);
    }
}
