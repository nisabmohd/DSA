package Sets;

import Maps.HashMap;
import definitions.Sets;

public class HashSet<T> implements Sets<T> {

    private HashMap<T, Object> map = new HashMap<T, Object>();

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
