import type {CompareFn} from "../util.ts";
import {BinaryTree, BinaryTreeNode} from "./binary-tree.ts";

export default class BinarySearchTree<T> extends BinaryTree<T> {
  private root: BinaryTreeNode<T> | null;
  private elements: number;
  private readonly compareFn: CompareFn<T>;

  public constructor(compare: CompareFn<T>) {
    super();
    this.root = null;
    this.elements = 0;
    this.compareFn = compare;
  }

  public add(val: T) {
    this.root = this.addHelper(val, this.root);
  }

  private addHelper(
    val: T,
    node: BinaryTreeNode<T> | null,
  ): BinaryTreeNode<T> | null {
    if (!node) return new BinaryTreeNode(val);
    if (this.compareFn(node.val, val) > 0) {
      node.left = this.addHelper(val, node.left);
      this.elements++;
    } else if (this.compareFn(node.val, val) < 0) {
      node.left = this.addHelper(val, node.right);
      this.elements++;
    }
    return node;
  }

  private hasDeleted: boolean = false;

  public remove(val: T) {
    this.hasDeleted = false;
    this.root = this.removeHelper(val, this.root);
    if (this.hasDeleted) this.elements--;
  }

  private removeHelper(
    val: T,
    node: BinaryTreeNode<T> | null,
  ): BinaryTreeNode<T> | null {
    if (!node) return null;
    if (this.compareFn(node.val, val) > 0) {
      node.left = this.removeHelper(val, node.left);
      return node;
    } else if (this.compareFn(node.val, val) < 0) {
      node.right = this.removeHelper(val, node.right);
      return node;
    } else {
      if (!node.left && !node.right) {
        this.hasDeleted = true;
        return null;
      } else if (!!node.left && !node.right) {
        this.hasDeleted = true;
        return node.left;
      } else if (!node.left && !!node.right) {
        this.hasDeleted = true;
        return node.right;
      } else {
        node.val = this.getMin(node.right);
        this.hasDeleted = true;
        node.right = this.removeHelper(val, node.right);
        return node;
      }
    }
  }

  private getMin(node: BinaryTreeNode<T> | null): T {
    let min = node!.val;
    while (node != null) {
      if (this.compareFn(node.val, min) < 0) min = node.val;
      node = node.left;
    }
    return min;
  }

  public get size() {
    return this.elements;
  }

  public clear() {
    this.root = null;
    this.elements = 0;
  }

  public has(val: T) {
    return this.hasHelper(val, this.root);
  }

  private hasHelper(val: T, node: BinaryTreeNode<T> | null): boolean {
    if (!node) return false;
    if (this.compareFn(node.val, val) == 0) return true;
    const fromLeft = this.hasHelper(val, node.left);
    if (fromLeft) return fromLeft;
    return this.hasHelper(val, node.right);
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

  public override levelOrder() {
    return super.levelOrder(this.root);
  }

  public override height() {
    return super.height(this.root);
  }

  public override rightView() {
    return super.rightView(this.root);
  }

  public override leftView() {
    return super.leftView(this.root);
  }

  public override topView() {
    return super.topView(this.root);
  }

  public override bottomView() {
    return super.bottomView(this.root);
  }

  public override levelOrderArray() {
    return super.levelOrderArray(this.root);
  }

  public override verticalOrder() {
    return super.verticalOrder(this.root);
  }

  public includes(val: T) {
    return this.includesHelper(val, this.root);
  }

  private includesHelper(val: T, node: BinaryTreeNode<T> | null): boolean {
    if (node == null) return false;
    if (this.compareFn(val, node.val) == 0) return true;
    if (this.includesHelper(val, node.left)) return true;
    return this.includesHelper(val, node.right);
  }
}
