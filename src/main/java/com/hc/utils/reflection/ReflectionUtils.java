package com.hc.utils.reflection;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 */
public class ReflectionUtils {

    /**
     * 获得某个类的注解
     * @param clazz 类
     * @param annotation 类的注解
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Class clazz,Class<T> annotation){
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation classAnno : annotations){
            if(annotation.isAssignableFrom(classAnno.getClass())){
                return (T)classAnno;
            }
        }
        return null;
    }


    /**
     * 获得构造器的参数名称
     * @param constructor
     * @return
     */
    public static String[] getConstructorParamName(Constructor constructor){
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] params = discoverer.getParameterNames(constructor);
        return params;
    }

    /**
     * 获得class所包含的field
     * @param clazz 类
     * @param abortClass 向上追溯到的父类（not include）
     * @return
     */
    public static List<Field> getFileds(Class clazz, Class abortClass){
        List<Field> ret = new ArrayList<>();
        Class fatcherClass = clazz;
        while(!fatcherClass.equals(Object.class) && !fatcherClass.equals(abortClass)){
            Field[] fields = fatcherClass.getDeclaredFields();
            for(Field field : fields){
                ret.add(field);
            }
            fatcherClass = fatcherClass.getSuperclass();
        }

        return ret;
    }
}
