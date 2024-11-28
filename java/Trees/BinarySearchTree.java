package Trees;

import java.util.*;

import definitions.BTree.BTreeNode;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    private BTreeNode<T> root;
    private int size;

    public BinarySearchTree() {
        size = 0;
        root = null;
    }

    public void add(T val) {
        root = add(val, root);

    }

    private BTreeNode<T> add(T val, BTreeNode<T> node) {
        if (node == null) {
            size++;
            return new BTreeNode<T>(val);
        }
        if (node.val.compareTo(val) > 0) {
            size++;
            node.left = add(val, node.left);
        } else if (node.val.compareTo(val) < 0) {
            size++;
            node.right = add(val, node.right);
        }
        return node;
    }

    private boolean hasDeleted = false;

    public void remove(T val) {
        hasDeleted = false;
        root = remove(val, root);
        if (hasDeleted) size--;
    }

    private T getMaxValue(BTreeNode<T> node) {
        var max = node.val;
        while (node != null) {
            if (node.val.compareTo(max) > 0) max = node.val;
            node = node.right;
        }
        return max;
    }

    private BTreeNode<T> remove(T val, BTreeNode<T> node) {
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

    private boolean contains(T val, BTreeNode<T> node) {
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

    public List<T> inOrder() {
        return super.inOrder(root);
    }

    public List<T> preOrder() {
        return super.preOrder(root);
    }

    public List<T> postOrder() {
        return super.postOrder(root);
    }

    public List<List<T>> levelOrder() {
        return super.levelOrder(root);
    }

    public int height() {
        return super.height(root);
    }

    public List<T> rightView() {
        return super.rightView(root);
    }

    public List<T> leftView() {
        return super.leftView(root);
    }

    public List<T> topView() {
        return super.topView(root);
    }

    public List<T> bottomView() {
        return super.bottomView(root);
    }

    public List<T> levelOrderArray() {
        return super.levelOrderArray(root);
    }

    public List<List<T>> verticalOrder() {
        return super.verticalOrder(root);
    }

}
