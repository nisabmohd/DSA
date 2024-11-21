package Trees;

import java.util.*;

public interface BinaryTree<T> {
    public static class Node<T> {
        T val;
        Node<T> left, right;
    }

    public List<T> inOrder(Node<T> node);

    public List<T> preOrder(Node<T> node);

    public List<T> postOrder(Node<T> node);

}
