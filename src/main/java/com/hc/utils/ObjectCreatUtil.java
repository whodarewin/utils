package com.hc.utils;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.*;
import java.util.*;

public class ObjectCreatUtil {


    public static <T> T getObject(Class<T> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException {


        Method[] methods = clazz.getDeclaredMethods();
        Object t = clazz.newInstance();

        for(Method method : methods){
            String methodName = method.getName();

            Type[] parameterTypes = method.getGenericParameterTypes();

            System.out.println(method.getName());
            if(methodName.startsWith("set")){
                Parameter[] parameters = method.getParameters();
                if(parameters.length != 1){
                    continue;
                }else{
                    Parameter parameter = parameters[0];
                    Class subClazz = parameter.getType();
                    Type type = parameterTypes[0];

                    Object obj = genValue(subClazz,type);
                    method.invoke(t,obj);
                }
            }
        }
        return (T)t;
    }


    private static Object genValue(Class clazz, Type type) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        if(isPri(clazz)){
            return getBasicType(clazz);
        }else if(List.class.isAssignableFrom(clazz)){
            return getList(type);
        }else if(Map.class.isAssignableFrom(clazz)){
            return getMap(type);
        }else if(clazz.isEnum()){
            return clazz.getEnumConstants()[0];
        }else if(String.class.isAssignableFrom(clazz)){
            return "testtesttesttesttest";
        }
        else{
            return getObject(clazz);
        }
    }




    public static Object getBasicType(Class clazz){
        Random random = new Random();
        if (clazz.equals(String.class)){
            return random.nextInt();
        }else if(clazz.equals(int.class) || Integer.class.isAssignableFrom(clazz)){
            return random.nextInt();
        }else if(clazz.equals(long.class) || Long.class.isAssignableFrom(clazz)){
            return random.nextLong();
        }else if(clazz.equals(short.class) || Short.class.isAssignableFrom(clazz)){
            return random.nextInt();
        }else if(clazz.equals(float.class) || Float.class.isAssignableFrom(clazz)){
            return random.nextFloat();
        }else if(clazz.equals(double.class) || Double.class.isAssignableFrom(clazz)) {
            return random.nextDouble();
        }else if(clazz.equals(boolean.class) || Boolean.class.isAssignableFrom(clazz)){
            return true;
        }
        throw new RuntimeException("no basic type defined");
    }

    public static boolean isPri(Class clazz){
        return clazz.isPrimitive() ||
        clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Short.class)
                ||clazz.equals(float.class) || clazz.equals(double.class);
    }

    public static List getList(Type type) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        List list = new ArrayList();
        if(type instanceof ParameterizedType){
            Object object = ((ParameterizedType)type).getActualTypeArguments()[0];
            if(object instanceof ParameterizedTypeImpl){
                ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl)object;
                Class clazz = parameterizedType.getRawType();
                list.add(genValue(clazz, parameterizedType));
            }else{
                Class clazz = (Class)object;
                list.add(genValue(clazz,null));
            }
        }else{
            list.add(new Object());
        }

        return list;
    }


    public static Map getMap(Type type) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Map map = new HashMap();
        if(type instanceof ParameterizedType){
            Object keyObject = ((ParameterizedType)type).getActualTypeArguments()[0];
            Object valueObject = ((ParameterizedType)type).getActualTypeArguments()[1];
            Object key = null;
            Object value = null;
            if(keyObject instanceof ParameterizedTypeImpl){
                ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl)keyObject;
                Class clazz = parameterizedType.getRawType();
                key = genValue(clazz, parameterizedType);
            }else{
                Class clazz = (Class)keyObject;
                key = getObject(clazz);
            }

            if(valueObject instanceof ParameterizedTypeImpl){
                ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl)valueObject;
                Class clazz = parameterizedType.getRawType();
                value = genValue(clazz, parameterizedType);
            }else{
                Class clazz = (Class)valueObject;
                value = genValue(clazz,null);
            }

            map.put(key,value);

        }else{
            map.put(new Object(),new Object());
        }

        return map;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Test test = ObjectCreatUtil.getObject(Test.class);
        System.out.println((1L << 13)- 1L);
    }

    public static class Test{
        List<List<String>> some;

        public List<List<String>> getSome() {
            return some;
        }

        public void setSome(List<List<String>> some) {
            this.some = some;
        }
    }
}
