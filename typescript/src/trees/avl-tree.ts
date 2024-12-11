import { CompareFn } from "../util.ts";
import { BinaryTree } from "./binary-tree.ts";

export class AvlTreeNode<T> {
  val: T;
  left: AvlTreeNode<T> | null;
  right: AvlTreeNode<T> | null;
  height: number;

  constructor(val: T) {
    this.val = val;
    this.left = null;
    this.right = null;
    this.height = 0;
  }
}

export default class AvlTree<T> extends BinaryTree<T> {
  private root: AvlTreeNode<T> | null;
  private count: number;
  private compareFn: CompareFn<T>;

  public constructor(compareFn: CompareFn<T>) {
    super();
    this.root = null;
    this.count = 0;
    this.compareFn = compareFn;
  }

  public add(val: T) {
    this.root = this.addHelper(val, this.root);
  }

  private addHelper(val: T, node: AvlTreeNode<T> | null): AvlTreeNode<T> {
    if (node == null) {
      this.count++;
      return new AvlTreeNode(val);
    }
    if (this.compareFn(node.val, val) > 0) {
      node.left = this.addHelper(val, node.left);
    } else if (this.compareFn(node.val, val) < 0) {
      node.right = this.addHelper(val, node.right);
    } else return node;
    this.updateHeight(node);
    return this.balance(node);
  }

  private hasDeleted = false;

  public remove(val: T) {
    this.hasDeleted = false;
    this.root = this.removeHelper(val, this.root);
    if (this.hasDeleted) this.count--;
  }

  private removeHelper(
    val: T,
    node: AvlTreeNode<T> | null,
  ): AvlTreeNode<T> | null {
    if (node == null) return null;
    if (this.compareFn(node.val, val) > 0) {
      node.left = this.removeHelper(val, node.left);
    } else if (this.compareFn(node.val, val) < 0) {
      node.right = this.removeHelper(val, node.right);
    } else {
      this.hasDeleted = true;
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;
      const minValueFromRight = this.getMin(node.right);
      node.val = minValueFromRight;
      this.removeHelper(minValueFromRight, node.right);
    }
    this.updateHeight(node);
    return this.balance(node);
  }

  private getMin(node: AvlTreeNode<T> | null): T {
    let min = node!.val;
    while (node != null) {
      if (this.compareFn(node.val, min) < 0) min = node.val;
      node = node.left;
    }
    return min;
  }

  public get size() {
    return this.count;
  }

  private getHeight(node: AvlTreeNode<T> | null) {
    return node == null ? 0 : node.height;
  }

  private updateHeight(node: AvlTreeNode<T> | null) {
    if (node == null) return;
    node.height =
      Math.max(this.getHeight(node.left), this.getHeight(node.right)) + 1;
  }

  private getBalanceFactor(node: AvlTreeNode<T> | null) {
    return node == null
      ? 0
      : this.getHeight(node.left) - this.getHeight(node.right);
  }

  private balance(node: AvlTreeNode<T>): AvlTreeNode<T> {
    const balanaceFactor = this.getBalanceFactor(node);
    if (balanaceFactor > 1) {
      if (this.getBalanceFactor(node.left) < 0) {
        node.left = this.leftRotate(node.left)!;
      }
      return this.rightRotate(node)!;
    }
    if (balanaceFactor < 1) {
      if (this.getBalanceFactor(node.right) > 0) {
        node.right = this.leftRotate(node.right)!;
      }
      return this.leftRotate(node)!;
    }
    return node;
  }

  private leftRotate(node: AvlTreeNode<T> | null) {
    if (node == null) return;
    const rightChild = node.right!;
    const rightChildLeft = rightChild.left;
    rightChild.left = node;
    node.right = rightChildLeft;
    this.updateHeight(node);
    this.updateHeight(rightChild);
    return rightChild;
  }

  private rightRotate(node: AvlTreeNode<T> | null) {
    if (node == null) return null;
    const leftChild = node.left!;
    const leftChildRight = leftChild.right;
    leftChild.right = node;
    node.left = leftChildRight;
    this.updateHeight(node);
    this.updateHeight(leftChild);
    return leftChild;
  }

  public override inOrder(): T[] {
    return super.inOrder(this.root);
  }

  public override preOrder(): T[] {
    return super.preOrder(this.root);
  }

  public override postOrder(): T[] {
    return super.postOrder(this.root);
  }

  // can add more functions and use parent utility like postOrder,preOrder etc...
}
