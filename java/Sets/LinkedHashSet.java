package Sets;

import Maps.LinkedHashMap;
import definitions.Sets;

public class LinkedHashSet<T> implements Sets<T> {

    private LinkedHashMap<T, Object> map = new LinkedHashMap<T, Object>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void add(T val) {
        map.put(val, null);
    }

    @Override
    public boolean contains(T val) {
        return map.containsKey(val);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void remove(T val) {
        map.remove(val);
    }

    @Override
    public int size() {
        return map.size();
    }
}
