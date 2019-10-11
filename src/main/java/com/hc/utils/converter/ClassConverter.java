package com.hc.utils.converter;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.NoSuchElementException;

/**
 * ClassConverter
 *
 * @author han.congcong
 * @date 2019/9/26
 */

public class ClassConverter {

    private static Table<Class,Class,Converter> table =  HashBasedTable.create();

    static{
        ClassConverter.register(String.class,Integer.class,new String2Integer());
        ClassConverter.register(String.class,int.class,new String2Integer());
        ClassConverter.register(String.class,Float.class,new String2Float());
        ClassConverter.register(String.class,float.class,new String2Float());
        ClassConverter.register(String.class,Long.class,new String2Long());
        ClassConverter.register(String.class,long.class,new String2Long());
        ClassConverter.register(String.class,Double.class,new String2Double());
        ClassConverter.register(String.class,double.class,new String2Double());

        ClassConverter.register(Integer.class,Double.class,new Integer2Double());
        ClassConverter.register(Integer.class,Float.class,new Integer2Float());
        ClassConverter.register(Integer.class,Long.class,new Integer2Long());
        ClassConverter.register(Integer.class,String.class,new Integer2String());

        ClassConverter.register(Long.class,Double.class,new Long2Double());
        ClassConverter.register(Long.class,Float.class,new Long2Float());
        ClassConverter.register(Long.class,Integer.class,new Long2Integer());
        ClassConverter.register(Long.class,String.class,new Long2String());

        ClassConverter.register(Double.class,String.class,new Double2String());
        ClassConverter.register(Float.class,String.class,new Float2String());
    }

    public static void register(Class from ,Class to, Converter converter){
        table.put(from,to,converter);
    }

    public static boolean canHandle(Object from,Class to){
        return table.contains(from.getClass(),to);
    }

    public static <T> T convert(Object from,Class<T> to){
        if(from == null){
            return null;
        }
        if(to.isAssignableFrom(from.getClass())){
            return (T)from;
        }

        Converter converter = table.get(from.getClass(),to);
        if(converter == null){
            throw new NoSuchElementException("no converter from "+ from.getClass().getName()
            + " to "+ to.getName());
        }
        return (T)converter.convert(from);
    }
}
