package definitions;

public interface Maps<K, V> {
    public void clear();

    public void put(K key, V val);

    public V get(K key);

    public boolean containsKey(K key);

    public boolean containsValue(V val);

    public V getOrDefault(K key, V fallback);

    public boolean isEmpty();

    public void putIfAbsent(K key, V val);

    public V remove(K key);

    public int size();
}
