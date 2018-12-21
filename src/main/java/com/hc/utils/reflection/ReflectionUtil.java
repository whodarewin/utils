package com.hc.utils.reflection;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public static String[] getParamName(Constructor constructor) {
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] params = discoverer.getParameterNames(constructor);
        return params;
    }

    public static String[] getParamName(Method method){
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] params = discoverer.getParameterNames(method);
        return params;
    }
}
