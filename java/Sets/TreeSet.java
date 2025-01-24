package Sets;

import Maps.TreeMap;
import definitions.Sets;

import java.util.Comparator;
import java.util.Objects;

public class TreeSet<T> implements Sets<T> {

    private final TreeMap<T, Object> map;

    public TreeSet() {
        map = new TreeMap<T, Object>();
    }

    public TreeSet(Comparator<T> comparator) {
        Objects.requireNonNull(comparator);
        map = new TreeMap<T, Object>(comparator);
    }

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
