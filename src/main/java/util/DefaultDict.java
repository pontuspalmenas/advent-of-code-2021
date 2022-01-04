package util;

import java.util.HashMap;

public class DefaultDict<K, V> extends HashMap<K, V> {

    Class<V> clazz;
    public DefaultDict(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public V get(Object key) {
        V returnValue = super.get(key);
        if (returnValue == null) {
            try {
                returnValue = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.put((K) key, returnValue);
        }
        return returnValue;
    }
}
