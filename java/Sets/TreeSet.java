package Sets;

import Maps.TreeMap;
import definitions.Sets;

import java.util.Comparator;
import java.util.Objects;

public class TreeSet<T extends Comparable<T>> implements Sets<T> {

    private TreeMap<T, Object> map;
    private Comparator<T> comparator;

    public TreeSet() {
        this.comparator = Comparable::compareTo;
        map = new TreeMap<T, Object>(comparator);
    }

    public TreeSet(Comparator<T> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
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
