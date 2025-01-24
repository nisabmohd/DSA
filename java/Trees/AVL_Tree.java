package Trees;

import definitions.BTree;

import java.util.Comparator;
import java.util.List;

public class AVL_Tree<T> extends BinaryTree<T> {

    private static class AVLTreeNode<E> {
        int height;
        E val;
        AVLTreeNode<E> left, right;

        public AVLTreeNode(E val) {
            this.val = val;
            height = 0;
        }
    }

    private AVLTreeNode<T> root;
    private int size;
    private final Comparator<T> comparator;


    public void clear() {
        size = 0;
        root = null;
    }

    public AVL_Tree() {
        clear();
        comparator = this.getObjectComparator();
    }

    public AVL_Tree(Comparator<T> c) {
        clear();
        this.comparator = c;
    }

    @SuppressWarnings("unchecked")
    private Comparator<T> getObjectComparator() {
        return (a, b) -> ((Comparable<T>) a).compareTo(b);
    }

    public void add(T val) {
        root = add(val, root);
    }

    private AVLTreeNode<T> add(T val, AVLTreeNode<T> node) {
        if (node == null) {
            size++;
            return new AVLTreeNode<>(val);
        }
        if (comparator.compare(node.val, val) > 0) {
            node.left = add(val, node.left);
        } else if (comparator.compare(node.val, val) < 0) {
            node.right = add(val, node.right);
        } else return node;
        updateHeight(node);
        return balance(node);
    }

    private boolean hasDeleted = false;


    public void remove(T val) {
        hasDeleted = false;
        root = remove(val, root);
        if (hasDeleted) size--;
    }

    private AVLTreeNode<T> remove(T val, AVLTreeNode<T> node) {
        if (node == null) return null;
        if (comparator.compare(node.val, val) > 0) {
            node.left = remove(val, node.left);
        } else if (comparator.compare(node.val, val) < 0) {
            node.right = remove(val, node.right);
        } else {
            hasDeleted = true;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            var maxLeft = getMaxValue(node.left);
            node.val = maxLeft;
            node.left = remove(maxLeft, node.left);
        }
        updateHeight(node);
        return balance(node);
    }

    private T getMaxValue(AVLTreeNode<T> node) {
        var max = node.val;
        while (node != null) {
            if (comparator.compare(node.val, max) > 0) max = node.val;
            node = node.right;
        }
        return max;
    }

    private AVLTreeNode<T> balance(AVLTreeNode<T> node) {
        var balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        return node;
    }

    private AVLTreeNode<T> rightRotate(AVLTreeNode<T> node) {
        var leftChild = node.left;
        var leftChildRightChild = leftChild.right;
        leftChild.right = node;
        node.left = leftChildRightChild;
        updateHeight(node);
        updateHeight(leftChild);
        return leftChild;
    }

    private AVLTreeNode<T> leftRotate(AVLTreeNode<T> node) {
        var rightChild = node.right;
        var rightChildLeftChild = rightChild.left;
        rightChild.left = node;
        node.right = rightChildLeftChild;
        updateHeight(node);
        updateHeight(rightChild);
        return rightChild;
    }

    private int height(AVLTreeNode<?> node) {
        return node == null ? 0 : node.height;
    }

    private int getBalanceFactor(AVLTreeNode<T> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private void updateHeight(AVLTreeNode<?> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }


    public boolean contains(T val) {
        return contains(val, root);
    }

    private boolean contains(T val, AVLTreeNode<T> node) {
        if (node == null) return false;
        if (comparator.compare(node.val, val) == 0) return true;
        if (comparator.compare(node.val, val) > 0) return contains(val, node.left);
        else return contains(val, node.right);
    }

    public int size() {
        return size;
    }

    private BTree.BTreeNode<T> convert(AVLTreeNode<T> node) {
        return node == null ? null : new BTree.BTreeNode<>(node.val);
    }

    public List<T> inOrder() {
        return super.inOrder(convert(root));
    }

}
