package Trees;

import java.util.*;

public class N_aryTree {
    public static class N_arryNode<T> {
        T val;
        ArrayList<N_arryNode<T>> childs;

        public N_arryNode(T val) {
            this.val = val;
            this.childs = new ArrayList<>();
        }
    }

    public static <E> List<E> preOrder(N_arryNode<E> node) {
        List<E> ans = new ArrayList<>();
        ans.add(node.val);
        node.childs.forEach(child -> {
            ans.addAll(preOrder(child));
        });
        return ans;
    }


    public static <E> List<E> postOrder(N_arryNode<E> node) {
        List<E> ans = new ArrayList<>();
        node.childs.forEach(child -> {
            ans.addAll(postOrder(child));
        });
        ans.add(node.val);
        return ans;
    }

    public static <E> List<List<E>> levelOrder(N_arryNode<E> node) {
        Map<Integer, List<E>> map = new LinkedHashMap<>();
        levelOrderHelper(node, map, 0);
        return map.values().stream().toList();
    }

    private static <E> void levelOrderHelper(N_arryNode<E> node, Map<Integer, List<E>> map, int level) {
        if (!map.containsKey(level)) map.put(level, new ArrayList<>());
        map.get(level).add(node.val);
        node.childs.forEach(child -> {
            levelOrderHelper(child, map, level + 1);
        });
    }

    public static void main(String[] args) {
        var root = new N_arryNode<>(1);
        var a = new N_arryNode<>(2);
        var b = new N_arryNode<>(3);
        var c = new N_arryNode<>(4);
        var d = new N_arryNode<>(5);
        var e = new N_arryNode<>(6);
        root.childs.add(b);
        root.childs.add(a);
        root.childs.add(c);
        b.childs.add(d);
        b.childs.add(e);

        System.out.println(preOrder(root));
        System.out.println(postOrder(root));
        System.out.println(levelOrder(root));

    }
}

