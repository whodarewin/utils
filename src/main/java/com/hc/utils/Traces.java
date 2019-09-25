package com.hc.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Traces
 *
 * @author han.congcong
 * @date 2019/9/24
 */

public class Traces {

    private static ThreadLocal<Boolean> TURN = ThreadLocal.withInitial(() -> Boolean.FALSE);
    private static ThreadLocal<Map<String,Object>> TRACE = ThreadLocal.withInitial(LinkedHashMap::new);

    public static void turnOn(){
        TURN.set(Boolean.TRUE);
    }

    public static void turnOff(){
        TURN.set(Boolean.FALSE);
    }

    public static void add(String key, Object value, Function function){
        if(TURN.get()) {
            TRACE.get().put(key, function.apply(value));
        }
    }

    public static void add(String key,Object value){
        if(TURN.get()) {
            TRACE.get().put(key, value);
        }
    }

    public static Map<String,Object> get(){
        return new HashMap<>(TRACE.get());
    }

    public static Object get(String key){
        return TRACE.get().get(key);
    }

    public static void remove(){
        TRACE.get().clear();
    }
}
