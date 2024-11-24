package definitions;

public interface BTree {
    public class BTreeNode<T> {
        public T val;
        public BTreeNode<T> left, right;

        public BTreeNode(T val) {
            this.val = val;
        }
    }
}
