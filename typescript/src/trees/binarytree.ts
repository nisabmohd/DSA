export class BinaryTreeNode<T> {
    val: T;
    left: BinaryTreeNode<T> | null;
    right: BinaryTreeNode<T> | null;

    constructor(val: T) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

export class BinaryTree<T> {
    public inOrder(node: BinaryTreeNode<T> | null) {
        const ans: T[] = [];
        if (!node) return ans;
        ans.push(...this.inOrder(node!.left));
        ans.push(node.val);
        ans.push(...this.inOrder(node!.right));
        return ans;
    }

    public preOrder(node: BinaryTreeNode<T> | null) {
        const ans: T[] = [];
        if (!node) return ans;
        ans.push(node.val);
        ans.push(...this.preOrder(node!.left));
        ans.push(...this.preOrder(node!.right));
        return ans;
    }

    public postOrder(node: BinaryTreeNode<T> | null) {
        const ans: T[] = [];
        if (!node) return ans;
        ans.push(...this.postOrder(node!.left));
        ans.push(...this.postOrder(node!.right));
        ans.push(node.val);
        return ans;
    }
}
