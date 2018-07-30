package com.example.yueshaojun.ioc.container;

import java.util.HashMap;

/**
 *
 * @author yueshaojun
 * @date 2018/7/25
 */

public class Container {
    private static HashMap<Class<?>,Object> beanContainer = new HashMap<>();
    public static void add(Class<?> key,Object value){
        beanContainer.put(key,value);
    }
    public static Object get(Class<?> key){
       return beanContainer.get(key);
    }
}
