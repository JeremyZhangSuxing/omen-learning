package com.omen.learning.sample.pattern;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : Knight
 * @date : 2021/5/21 3:50 下午
 */
public class Singleton {
    private Singleton() {

    }

    private static final ConcurrentHashMap<String, String> MAP = new ConcurrentHashMap<>();
    /**
     * 避免指令重排序
     */
    private volatile static Singleton singleton = null;

    public static synchronized Singleton getInstance() {
        if (singleton != null) {
            return singleton;
        }
        singleton = new Singleton();
        if (MAP.contains(String.valueOf(singleton.hashCode()))) {
            System.err.println("------ ");
        } else {
            MAP.put(String.valueOf(singleton.hashCode()), "TEST");
        }
        return singleton;
    }

    public static Singleton getInstance1() {
        if (singleton != null) {
            return singleton;
        }
        synchronized (Singleton.class) {
            if (singleton == null) {
                singleton = new Singleton();
            }
            return singleton;
        }
    }

    public static int getSize() {
        return MAP.size();
    }
}
