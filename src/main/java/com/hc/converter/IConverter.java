package com.hc.converter;

public interface IConverter<T> {
    byte[] convert(T t);
    T convert(byte[] bytes);
}
