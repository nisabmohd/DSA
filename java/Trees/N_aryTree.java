package Trees;

import java.util.*;

public class N_aryTree {
    public static class N_arryNode<T> {
        T val;
        ArrayList<N_arryNode<T>> childs;

        public N_arryNode(T val) {
            this.val = val;
        }
    }

    public <E> List<E> preOrder(N_arryNode<E> node) {
        return null;
    }

    public static void main(String[] args) {
        var root = new N_arryNode<>(1);
        var a = new N_arryNode<>(2);
        var b = new N_arryNode<>(3);
        var c = new N_arryNode<>(4);
        var d = new N_arryNode<>(5);
        root.childs.add(a);
        root.childs.add(b);
        root.childs.add(c);

    }
}
