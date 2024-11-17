package Trees;

import java.util.ArrayList;

public class N_aryTree {
    public static class N_arryNode<T> {
        T val;
        ArrayList<N_arryNode<T>> childs;

        public N_arryNode(T val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        var root = new N_arryNode<>(1);
        var a = new N_arryNode<>(2);
    }
}
