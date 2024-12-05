import { BinaryTree, BinaryTreeNode } from "./binary-tree.ts";

export default class CompleteBinaryTree<T> extends BinaryTree<T> {
  private arr: (BinaryTreeNode<T> | null)[];

  public constructor() {
    super();
    this.arr = [null];
  }

  public add(val: T) {
    const newNode = new BinaryTreeNode(val);
    this.arr.push(newNode);
    const parentIndex = Math.floor(this.arr.length / 2);
    const parentNode = this.arr[parentIndex]!;
    if (parentNode.left != null) parentNode.left = newNode;
    else parentNode.right = newNode;
  }

  public get size() {
    return this.arr.length - 1;
  }

  public remove(): T | null {
    if (this.size == 0) return null;
    const lastNodeIndex = this.arr.length - 1;
    const lastNode = this.arr[lastNodeIndex]!;
    const parentIndex = Math.floor(lastNodeIndex / 2);
    const parentNode = this.arr[parentIndex]!;
    if (parentNode.left == lastNode) parentNode.left = null;
    else parentNode.right = null;
    return lastNode.val;
  }

  public override inOrder(node: BinaryTreeNode<T> | null): T[] {
    return super.inOrder(node);
  }

  public override preOrder(node: BinaryTreeNode<T> | null): T[] {
    return super.preOrder(node);
  }

  public override postOrder(node: BinaryTreeNode<T> | null): T[] {
    return super.postOrder(node);
  }

  // can add more functions and use parent utility like postOrder,preOrder etc...
}
