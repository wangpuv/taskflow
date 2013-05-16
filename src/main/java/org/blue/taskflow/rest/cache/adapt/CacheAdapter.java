package org.blue.taskflow.rest.cache.adapt;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 10:59:44
 */
public interface CacheAdapter {

    Object get(String key, boolean isClone);

    void put(String key, Object value);

    void update(String key, Object value);

    Object remove(String key);

    boolean contains(String key);
}
