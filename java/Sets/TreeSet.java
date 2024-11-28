package Sets;

import Maps.TreeMap;
import definitions.Sets;

public class TreeSet<T extends Comparable<T>> implements Sets<T> {

    private TreeMap<T, Object> map = new TreeMap<T, Object>();
    private Object garbage = null;


    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void add(T val) {
        map.put(val, garbage);
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
