package Trees;

import definitions.BTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BinaryTree<T> {

    public List<T> inOrder(BTree.BTreeNode<T> node) {
        List<T> ans = new ArrayList<>();
        if (node == null) return ans;
        ans.addAll(inOrder(node.left));
        ans.add(node.val);
        ans.addAll(inOrder(node.right));
        return ans;
    }

    public List<T> preOrder(BTree.BTreeNode<T> node) {
        List<T> ans = new ArrayList<>();
        if (node == null) return ans;
        ans.add(node.val);
        ans.addAll(preOrder(node.left));
        ans.addAll(preOrder(node.right));
        return ans;
    }

    public List<T> postOrder(BTree.BTreeNode<T> node) {
        List<T> ans = new ArrayList<>();
        if (node == null) return ans;
        ans.addAll(postOrder(node.left));
        ans.addAll(postOrder(node.right));
        ans.add(node.val);
        return ans;
    }

    public List<List<T>> levelOrder(BTree.BTreeNode<T> node) {
        Map<Integer, List<T>> map = new LinkedHashMap<>();
        levelOrderHelper(node, map, 0);
        return map.values().stream().toList();

    }

    private void levelOrderHelper(BTree.BTreeNode<T> node, Map<Integer, List<T>> map, int level) {
        if (node == null) return;
        if (!map.containsKey(level)) map.put(level, new ArrayList<>());
        map.get(level).add(node.val);
        levelOrderHelper(node.left, map, level + 1);
        levelOrderHelper(node.right, map, level + 1);
    }

    //   todo
    //    public  List<T> levelOrderArray(BTreeNode<T> node);


    public static void main(String[] args) {
        var tree = new BinaryTree<Integer>();
        var root = new BTree.BTreeNode<Integer>(1);
        var a = new BTree.BTreeNode<Integer>(2);
        var b = new BTree.BTreeNode<Integer>(3);
        var c = new BTree.BTreeNode<Integer>(4);
        var d = new BTree.BTreeNode<Integer>(5);
        var e = new BTree.BTreeNode<Integer>(6);

        root.left = a;
        root.right = b;
        a.left = c;
        a.right = d;
        b.right = e;

        System.out.println(tree.inOrder(root));
        System.out.println(tree.preOrder(root));
        System.out.println(tree.postOrder(root));
        System.out.println(tree.levelOrder(root));
    }
}
