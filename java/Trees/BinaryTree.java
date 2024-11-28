package Trees;

import definitions.BTree.BTreeNode;

import java.util.*;

public class BinaryTree<T> {

    protected List<T> inOrder(BTreeNode<T> node) {
        List<T> ans = new ArrayList<>();
        if (node == null) return ans;
        ans.addAll(inOrder(node.left));
        ans.add(node.val);
        ans.addAll(inOrder(node.right));
        return ans;
    }

    protected List<T> preOrder(BTreeNode<T> node) {
        List<T> ans = new ArrayList<>();
        if (node == null) return ans;
        ans.add(node.val);
        ans.addAll(preOrder(node.left));
        ans.addAll(preOrder(node.right));
        return ans;
    }

    protected List<T> postOrder(BTreeNode<T> node) {
        List<T> ans = new ArrayList<>();
        if (node == null) return ans;
        ans.addAll(postOrder(node.left));
        ans.addAll(postOrder(node.right));
        ans.add(node.val);
        return ans;
    }

    protected List<List<T>> levelOrder(BTreeNode<T> node) {
        Map<Integer, List<T>> map = new LinkedHashMap<>();
        levelOrderHelper(node, map, 0);
        return map.values().stream().toList();

    }

    private void levelOrderHelper(BTreeNode<T> node, Map<Integer, List<T>> map, int level) {
        if (node == null) return;
        if (!map.containsKey(level)) map.put(level, new ArrayList<>());
        map.get(level).add(node.val);
        levelOrderHelper(node.left, map, level + 1);
        levelOrderHelper(node.right, map, level + 1);
    }


    protected List<T> leftView(BTreeNode<T> node) {
        var map = new LinkedHashMap<Integer, T>();
        leftViewHelper(node, map, 0);
        return map.values().stream().toList();
    }

    private void leftViewHelper(BTreeNode<T> node, Map<Integer, T> map, int level) {
        if (node == null) return;
        map.putIfAbsent(level, node.val);
        leftViewHelper(node.left, map, level + 1);
        leftViewHelper(node.right, map, level + 1);
    }

    protected List<T> rightView(BTreeNode<T> node) {
        var map = new LinkedHashMap<Integer, T>();
        rightViewHelper(node, map, 0);
        return map.values().stream().toList();
    }

    private void rightViewHelper(BTreeNode<T> node, Map<Integer, T> map, int level) {
        if (node == null) return;
        map.put(level, node.val);
        rightViewHelper(node.left, map, level + 1);
        rightViewHelper(node.right, map, level + 1);
    }

    protected int height(BTreeNode<T> node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    protected List<T> topView(BTreeNode<T> node) {
        var map = new TreeMap<Integer, T>();
        topViewHelper(node, map, 0);
        return map.values().stream().toList();
    }

    private void topViewHelper(BTreeNode<T> node, TreeMap<Integer, T> map, int scale) {
        if (node == null) return;
        map.putIfAbsent(scale, node.val);
        topViewHelper(node.left, map, scale - 1);
        topViewHelper(node.right, map, scale + 1);
    }

    protected List<T> bottomView(BTreeNode<T> node) {
        var map = new TreeMap<Integer, T>();
        bottomViewHelper(node, map, 0);
        return map.values().stream().toList();
    }

    private void bottomViewHelper(BTreeNode<T> node, TreeMap<Integer, T> map, int scale) {
        if (node == null) return;
        map.put(scale, node.val);
        bottomViewHelper(node.left, map, scale - 1);
        bottomViewHelper(node.right, map, scale + 1);
    }

    protected List<T> levelOrderArray(BTreeNode<T> node) {
        List<T> ans = new ArrayList<>();
        if (node == null) return ans;
        Queue<BTreeNode<T>> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            var elementNode = q.remove();
            ans.add(elementNode.val);
            if (elementNode.left != null) q.add(elementNode.left);
            if (elementNode.right != null) q.add(elementNode.right);
        }
        return ans;
    }

    protected List<List<T>> verticalOrder(BTreeNode<T> node) {
        var map = new TreeMap<Integer, List<T>>();
        verticalOrderHelper(node, map, 0);
        return map.values().stream().toList();
    }


    private void verticalOrderHelper(BTreeNode<T> node, Map<Integer, List<T>> map, int scale) {
        if (node == null) return;
        if (!map.containsKey(scale)) map.put(scale, new ArrayList<>());
        map.get(scale).add(node.val);
        verticalOrderHelper(node.left, map, scale - 1);
        verticalOrderHelper(node.right, map, scale + 1);

    }


    public static void main(String[] args) {
        var tree = new BinaryTree<Integer>();
        var root = new BTreeNode<Integer>(1);
        var a = new BTreeNode<Integer>(2);
        var b = new BTreeNode<Integer>(3);
        var c = new BTreeNode<Integer>(4);
        var d = new BTreeNode<Integer>(5);
        var e = new BTreeNode<Integer>(6);

        root.left = a;
        root.right = b;
        a.left = c;
        a.right = d;
        b.right = e;

        System.out.println(tree.inOrder(root));
        System.out.println(tree.preOrder(root));
        System.out.println(tree.postOrder(root));
        System.out.println(tree.levelOrder(root));
        System.out.println("Left view ->" + tree.leftView(root));
        System.out.println("Right view ->" + tree.rightView(root));
        System.out.println("Top view ->" + tree.topView(root));
        System.out.println("Bottom view ->" + tree.bottomView(root));
        System.out.println("Level order array ->" + tree.levelOrderArray(root));
        System.out.println("Vertical Order ->" + tree.verticalOrder(root));
    }
}
