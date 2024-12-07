import Queue from "../queues/queue.ts";

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
  public height(node: BinaryTreeNode<T> | null): number {
    if (node == null) return 0;
    return Math.max(this.height(node.left), this.height(node.right)) + 1;
  }

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

  public levelOrder(node: BinaryTreeNode<T> | null) {
    const map = new Map<number, T[]>();
    this.levelOrderHelper(node, map, 0);
    return [...map.values()];
  }

  private levelOrderHelper(
    node: BinaryTreeNode<T> | null,
    levelMap: Map<number, T[]>,
    level: number,
  ) {
    if (node == null) return;
    if (!levelMap.has(level)) levelMap.set(level, []);
    levelMap.get(level)!.push(node.val);
    this.levelOrderHelper(node.left, levelMap, level + 1);
    this.levelOrderHelper(node.right, levelMap, level + 1);
  }

  public levelOrderArray(node: BinaryTreeNode<T> | null) {
    const ans: T[] = [];
    if (node == null) return ans;
    const q = new Queue<BinaryTreeNode<T>>();
    q.add(node);

    while (q.size != 0) {
      const removed = q.remove()!;
      ans.push(removed.val);
      if (removed.left != null) q.add(node.left!);
      if (removed.right != null) q.add(node.right!);
    }

    return ans;
  }

  public leftView(
    node: BinaryTreeNode<T> | null,
  ) {
    const map = new Map<number, T>();
    this.leftViewHelper(node, map, 0);
    return [...map.values()];
  }

  private leftViewHelper(
    node: BinaryTreeNode<T> | null,
    map: Map<number, T>,
    level: number,
  ) {
    if (node == null) return;
    if (!map.has(level)) map.set(level, node.val);
    this.leftViewHelper(node.left, map, level + 1);
    this.leftViewHelper(node.right, map, level + 1);
  }

  public rightView(node: BinaryTreeNode<T> | null) {
    const map = new Map<number, T>();
    this.rightViewHelper(node, map, 0);
    return [...map.values()];
  }

  private rightViewHelper(
    node: BinaryTreeNode<T> | null,
    map: Map<number, T>,
    level: number,
  ) {
    if (node == null) return;
    map.set(level, node.val);
    this.leftViewHelper(node.left, map, level + 1);
    this.leftViewHelper(node.right, map, level + 1);
  }

  public topView(node: BinaryTreeNode<T> | null) {
    const map = new Map<number, T>();
    this.topViewHelper(node, 0, map);
    return map.entries().toArray().sort((a, b) => a[0] - b[0]).map((it) =>
      it[1]
    );
  }

  private topViewHelper(
    node: BinaryTreeNode<T> | null,
    scale: number,
    map: Map<number, T>,
  ) {
    if (node == null) return;
    if (!map.has(scale)) map.set(scale, node.val);
    this.topViewHelper(node.left, scale - 1, map);
    this.topViewHelper(node.right, scale + 1, map);
  }

  public bottomView(node: BinaryTreeNode<T> | null) {
    const map = new Map<number, T>();
    this.bottomViewHelper(node, 0, map);
    return map.entries().toArray().sort((a, b) => a[0] - b[0]).map((it) =>
      it[1]
    );
  }

  private bottomViewHelper(
    node: BinaryTreeNode<T> | null,
    scale: number,
    map: Map<number, T>,
  ) {
    if (node == null) return;
    if (!map.has(scale)) map.set(scale, node.val);
    this.topViewHelper(node.left, scale - 1, map);
    this.topViewHelper(node.right, scale + 1, map);
  }

  public verticalOrder(node: BinaryTreeNode<T> | null) {
    const map = new Map<number, T[]>();
    this.verticalOrderHelper(node, 0, map);
    return map.entries().toArray().sort((a, b) => a[0] - b[0]).map((it) =>
      it[1]
    );
  }

  private verticalOrderHelper(
    node: BinaryTreeNode<T> | null,
    scale: number,
    map: Map<number, T[]>,
  ) {
    if (node == null) return;
    if (!map.has(scale)) map.set(scale, []);
    map.get(scale)!.push(node.val);
    this.verticalOrderHelper(node.left, scale - 1, map);
    this.verticalOrderHelper(node.right, scale + 1, map);
  }
}
