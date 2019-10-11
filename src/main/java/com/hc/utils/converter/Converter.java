package com.hc.utils.converter;

/**
 * Converter
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public interface Converter<FROM,TO> {
    TO convert(FROM from);
}
