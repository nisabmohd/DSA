package Trees;

import definitions.BTree;

import java.util.*;

public class CompleteBinaryTree<T> extends BinaryTree<T> {
    private ArrayList<BTree.BTreeNode<T>> list;
    private int size;

    public CompleteBinaryTree() {
        list = new ArrayList<>();
        list.add(null);
        size = 1;
    }

    public boolean isEmpty() {
        return size == 1;
    }

    public int size() {
        return size;
    }

    public void add(T val) {
        var node = new BTree.BTreeNode<>(val);
        if (isEmpty()) {
            list.add(size++, node);
            return;
        }
        list.add(size, node);
        var parentNode = list.get(size / 2);
        if (parentNode.left == null) parentNode.left = node;
        else parentNode.right = node;
        size++;
    }

    public T remove() {
        if (isEmpty()) return null;
        var lastNode = list.get(size - 1);
        var parentNode = list.get((size - 1) / 2);
        if (parentNode.left == lastNode) parentNode.left = null;
        else parentNode.right = null;
        size--;
        return lastNode.val;
    }

    public List<T> inOrder() {
        return super.inOrder(list.get(1));
    }

    public List<List<T>> levelOrder() {
        return super.levelOrder(list.get(1));
    }

    // can add more functions and use parent utility like postOrder,preOrder etc...

}
