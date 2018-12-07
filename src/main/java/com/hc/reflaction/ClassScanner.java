package com.hc.reflaction;

import com.google.common.base.Preconditions;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * class的扫描器
 */
public class ClassScanner {

    /**
     * 扫描类
     * @param packageScope 包范围
     * @param annotation 类所带的注解
     * @param clazz 类属于那个class
     * @param <T>
     * @return
     */
    public static <T> Set<Class<T>> scanClasses(String packageScope, Class<? extends Annotation> annotation, Class<T> clazz){
        Preconditions.checkNotNull(clazz);
        Preconditions.checkNotNull(annotation);
        Reflections reflections = new Reflections(packageScope);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation);
        Set<Class<T>> ret = new HashSet<Class<T>>();
        for(Class<?> cla : classes){
           if(clazz.isAssignableFrom(cla)){
               ret.add((Class<T>) cla);
           }
        }
        return ret;
    }
}
