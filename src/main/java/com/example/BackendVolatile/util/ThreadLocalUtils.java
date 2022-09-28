package com.example.BackendVolatile.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程缓存工具类
 *
 * @author Boss
 */
public class ThreadLocalUtils {
    private static ThreadLocal<Map<String, Long>> cache = new ThreadLocal<>();

    /**
     * 向ThreadLocal缓存值
     *
     * @param key   要缓存的KEY
     * @param value 要缓存的VALUE
     */
    public static void set(String key, Long value) {
        if (!isCaheIsNull()) {
            cache.get().put(key, value);
        } else {
            Map<String, Long> vmap = new HashMap<>();
            vmap.put(key, value);
            cache.set(vmap);
        }
    }

    /**
     * 从ThreadLocal里获取缓存的值
     *
     * @param key 要获取的数据的KEY
     * @return 要获取的值
     */
    public static Long getCache(String key) {
        Map<String, Long> map = cache.get();
        if (isCaheIsNull()) {
            return null;
        }
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return null;
        }
    }



    /**
     * 根据KEY移除缓存里的数据
     *
     * @param key
     */
    public static void removeByKey(String key) {
        if (isCaheIsNull()) {
            return;
        } else {
            cache.get().remove(key);
        }
    }

    /**
     * 移除当前线程缓存
     * 用于释放当前线程threadlocal资源
     */
    public static void remove() {
        cache.remove();
    }

    private static boolean isCaheIsNull() {
        return cache.get() == null;
    }
}

