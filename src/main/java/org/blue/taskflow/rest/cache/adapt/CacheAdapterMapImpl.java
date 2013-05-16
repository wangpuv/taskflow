package org.blue.taskflow.rest.cache.adapt;

import org.blue.taskflow.rest.utils.BeansHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 11:03:52
 */
public class CacheAdapterMapImpl implements CacheAdapter {

    private static Map<String, Object> cachePool = new HashMap<String, Object>();

    public Object get(String key, boolean isClone) {
        if (isClone) {
            return BeansHelper.deepClone(cachePool.get(key));
        }
        return cachePool.get(key);
    }

    public void put(String key, Object value) {
        if (cachePool.containsKey(key))
            throw new RuntimeException("该cache中已包含[key=" + key + "]的键值");
        cachePool.put(key, value);
    }

    public void update(String key, Object value) {
        cachePool.put(key, value);
    }

    public Object remove(String key) {
        return cachePool.remove(key);
    }

    public boolean contains(String key) {
        return cachePool.containsKey(key);
    }
}
