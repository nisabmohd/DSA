package Trees;

import definitions.BTree;

public class BinarySearchTree<T extends Comparable<T>> {
    private BTree.BTreeNode<T> root;
    private int size;

    public BinarySearchTree() {
        size = 0;
        root = null;
    }

    public void add(T val) {
        root = add(val, root);
        size++;
    }

    private BTree.BTreeNode<T> add(T val, BTree.BTreeNode<T> node) {
        if (node == null) return new BTree.BTreeNode<>(val);
        if (node.val.compareTo(val) > 0) node.left = add(val, node.left);
        else if (node.val.compareTo(val) < 0) node.right = add(val, node.right);
        return node;
    }

    private boolean hasDeleted = false;

    public void remove(T val) {
        hasDeleted = false;
        root = remove(val, root);
        if (hasDeleted) size--;
    }

    private T getMaxValue(BTree.BTreeNode<T> node) {
        var max = node.val;
        while (node != null) {
            if (node.val.compareTo(max) > 0) max = node.val;
            node = node.right;
        }
        return max;
    }

    private BTree.BTreeNode<T> remove(T val, BTree.BTreeNode<T> node) {
        if (node == null) return null;
        if (node.val.compareTo(val) > 0) {
            node.left = remove(val, node.left);
            return node;
        } else if (node.val.compareTo(val) < 0) {
            node.right = remove(val, node.right);
            return node;
        }
        if (node.left == null && node.right == null) {
            hasDeleted = true;
            return null;
        } else if (node.left != null && node.right == null) {
            hasDeleted = true;
            return node.left;
        } else if (node.left == null && node.right != null) {
            hasDeleted = true;
            return node.right;
        } else {
            var maxLeft = getMaxValue(node.left);
            node.val = maxLeft;
            node.left = remove(maxLeft, node.left);
            hasDeleted = true;
            return node;
        }
    }

    public boolean contains(T val) {
        return contains(val, root);
    }

    private boolean contains(T val, BTree.BTreeNode<T> node) {
        if (node == null) return false;
        if (node.val.equals(val)) return true;
        if (node.val.compareTo(val) > 0) return contains(val, node.left);
        else return contains(val, node.right);
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        root = null;
    }
}

