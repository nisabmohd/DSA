package Maps;

import Trees.BinaryTree;
import definitions.BTree.BTreeNode;
import definitions.Maps;

import java.util.Comparator;
import java.util.function.BiConsumer;

class Entry<K, V> {
    K key;
    V val;

    public Entry(K key, V val) {
        this.key = key;
        this.val = val;
    }
}

// todo : optimise by using AVL_TREE
public class TreeMap<K extends Comparable<K>, V> extends BinaryTree<Entry<K, V>> implements Maps<K, V> {

    private BTreeNode<Entry<K, V>> root;
    private int size;
    private Comparator<K> comparator;

    public TreeMap() {
        root = null;
        size = 0;
        this.comparator = Comparable::compareTo;
    }

    public TreeMap(Comparator<K> comparator) {
        this();
        this.comparator = comparator;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void put(K key, V val) {
        root = put(new Entry<K, V>(key, val), root);
    }


    private BTreeNode<Entry<K, V>> put(Entry<K, V> entry, BTreeNode<Entry<K, V>> node) {
        if (node == null) {
            size++;
            return new BTreeNode<>(entry);
        }
        if (comparator.compare(node.val.key, entry.key) > 0) {
            size++;
            node.left = put(entry, node.left);
        } else if (comparator.compare(node.val.key, entry.key) < 0) {
            size++;
            node.right = put(entry, node.right);
        }
        return node;
    }

    private Entry<K, V> deletedEntry = null;

    @Override
    public V remove(K key) {
        deletedEntry = null;
        root = remove(key, root);
        if (deletedEntry == null) return null;
        size--;
        return deletedEntry.val;
    }

    private BTreeNode<Entry<K, V>> remove(K key, BTreeNode<Entry<K, V>> node) {
        if (node == null) return null;
        if (comparator.compare(node.val.key, key) > 0) {
            node.left = remove(key, node.left);
            return node;
        } else if (comparator.compare(node.val.key, key) < 0) {
            node.right = remove(key, node.right);
            return node;
        } else {
            if (node.left == null && node.right == null) {
                if (deletedEntry == null) deletedEntry = node.val;
                return null;
            } else if (node.left != null && node.right == null) {
                if (deletedEntry == null) deletedEntry = node.val;
                return node.left;
            } else if (node.left == null && node.right != null) {
                if (deletedEntry == null) deletedEntry = node.val;
                return node.right;
            } else {
                if (deletedEntry == null) deletedEntry = node.val;
                var entry = getMin(node.right);
                node.val = entry;
                node.right = remove(entry.key, node.right);
                return node;
            }
        }
    }

    private Entry<K, V> getMin(BTreeNode<Entry<K, V>> node) {
        var ans = node.val;
        while (node != null) {
            if (comparator.compare(node.val.key, ans.key) < 0) ans = node.val;
            node = node.left;
        }
        return ans;
    }

    @Override
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, BTreeNode<Entry<K, V>> node) {
        if (node == null) return null;
        if (node.val.key.equals(key)) return node.val.val;
        if (comparator.compare(node.val.key, key) > 0)
            return get(key, node.left);
        else return get(key, node.right);
    }

    @Override
    public boolean containsValue(V val) {
        return containsValue(val, root);
    }

    private boolean containsValue(V val, BTreeNode<Entry<K, V>> node) {
        if (node == null) return false;
        if (node.val.val.equals(val)) return true;
        var fromLeft = containsValue(val, node.left);
        if (fromLeft) return true;
        return containsValue(val, node.right);
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
        super.inOrder(root).forEach(entry -> {
            consumer.accept(entry.key, entry.val);
        });
    }

}
