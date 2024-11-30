package Maps;

import definitions.Maps;

import java.util.function.BiConsumer;

public class HashMap<K, V> implements Maps<K, V> {

    public static class Entry<K, V> {
        K key;
        V val;

        Entry<K, V> next;

        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

    private final int bucket_capacity = 97;
    private Entry<K, V>[] bucket;
    private int size;

    public HashMap() {
        this.bucket = new Entry[bucket_capacity];
        this.size = 0;
    }

    @Override
    public void clear() {
        this.bucket = new Entry[bucket_capacity];
        this.size = 0;
    }

    private int getHash(K key) {
        return key.hashCode() % bucket_capacity;
    }

    @Override
    public void put(K key, V val) {
        int hash = getHash(key);
        Entry<K, V> head = bucket[hash];
        var newEntry = new Entry<K, V>(key, val);
        if (head == null) {
            bucket[hash] = newEntry;
            size++;
            return;
        }
        while (head.next != null) {
            if (head.key.equals(key)) {
                head.val = val;
                return;
            }
            head = head.next;
        }
        if (head.key.equals(key)) {
            head.val = val;
            return;
        }
        head.next = newEntry;
        size++;
    }

    @Override
    public V get(K key) {
        int hash = getHash(key);
        Entry<K, V> head = bucket[hash];
        while (head != null) {
            if (head.key.equals(key)) return head.val;
            head = head.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V val) {
        for (var entryHead : bucket) {
            while (entryHead != null) {
                if (entryHead.val.equals(val)) return true;
                entryHead = entryHead.next;
            }
        }
        return false;
    }

    @Override
    public V getOrDefault(K key, V fallback) {
        var val = get(key);
        return val != null ? val : fallback;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void putIfAbsent(K key, V val) {
        if (containsKey(key)) return;
        put(key, val);
    }

    @Override
    public V remove(K key) {
        int hash = getHash(key);
        Entry<K, V> head = bucket[hash];
        if (head.key.equals(key)) {
            var val = head.val;
            bucket[hash] = head.next;
            return val;
        }
        Entry<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) break;
            prev = head;
            head = head.next;
        }
        if (head == null) return null;
        var val = head.val;
        prev.next = head.next;
        return val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (var entryHead : bucket) {
            while (entryHead != null) {
                consumer.accept(entryHead.key, entryHead.val);
                entryHead = entryHead.next;
            }
        }
    }
}
