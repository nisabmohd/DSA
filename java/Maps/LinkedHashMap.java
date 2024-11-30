package Maps;

import definitions.Maps;

import java.util.function.BiConsumer;

public class LinkedHashMap<K, V> implements Maps<K, V> {

    private class Entry<K, V> {
        K key;
        V val;
        Entry<K, V> before, after, next;

        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private final int bucket_capacity = 97;
    private Entry<K, V> head, tail;
    private int size;
    private Entry<K, V>[] bucket;

    private void init() {
        head = null;
        tail = null;
        size = 0;
        bucket = new Entry[bucket_capacity];
    }

    public LinkedHashMap() {
        init();
    }

    @Override
    public void clear() {
        init();
    }

    private int getHash(K key) {
        return key.hashCode() % bucket_capacity;
    }

    @Override
    public void put(K key, V val) {
        int hash = getHash(key);
        var newEntry = new Entry<K, V>(key, val);
        if (head == null) head = newEntry;
        if (tail != null) {
            newEntry.before = tail;
            tail.after = newEntry;
        }
        tail = newEntry;
        var entryHead = bucket[hash];
        if (entryHead == null) {
            bucket[hash] = newEntry;
            size++;
            return;
        }
        while (entryHead.next != null) {
            if (entryHead.key.equals(key)) {
                entryHead.val = val;
                return;
            }
            entryHead = entryHead.next;
        }
        if (entryHead.key.equals(key)) {
            entryHead.val = val;
            return;
        }
        entryHead.next = newEntry;
        size++;
    }

    @Override
    public V get(K key) {
        int hash = getHash(key);
        var entryHead = bucket[hash];
        while (entryHead != null) {
            if (entryHead.key.equals(key)) return entryHead.val;
            entryHead = entryHead.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int hash = getHash(key);
        Entry<K, V> temp = bucket[hash], justPrev = null;
        while (temp != null && !temp.key.equals(key)) {
            justPrev = temp;
            temp = temp.next;
        }
        // key not found
        if (temp == null) return null;
        var returnVal = temp.val;
        if (justPrev == null) {
            // means only one in bucket index
            bucket[hash] = null;
            size--;
            if (temp.before != null) temp.before.after = temp.after;
            if (temp.after != null) temp.after.before = temp.before;
            return returnVal;
        }
        justPrev.next = temp.next;
        if (temp.before != null) temp.before.after = temp.after;
        if (temp.after != null) temp.after.before = temp.before;
        size--;
        return null;
    }

    @Override
    public boolean containsValue(V val) {
        var temp = head;
        while (temp != null) {
            if (temp.val.equals(val)) return true;
            temp = temp.after;
        }
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }


    @Override
    public V getOrDefault(K key, V fallback) {
        var val = get(key);
        return val == null ? fallback : val;
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
    public int size() {
        return size;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        var temp = head;
        while (temp != null) {
            consumer.accept(temp.key, temp.val);
            temp = temp.after;
        }
    }

}
