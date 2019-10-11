package com.hc.utils.converter;

import com.immomo.mcf.util.StringUtils;

/**
 * String2Double
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class String2Double implements Converter<String,Double> {
    @Override
    public Double convert(String s) {
        if(StringUtils.isBlank(s)){
            return null;
        }
        return Double.parseDouble(s);
    }
}
