export class Nary_Node<T> {
  val: T;
  children: Nary_Node<T>[];
  constructor(val: T) {
    this.val = val;
    this.children = [];
  }
}

export class N_aryTree {
  public static preOrder<T>(node: Nary_Node<T>): T[] {
    const ans: T[] = [];
    ans.push(node.val);
    node.children.forEach((child) => {
      ans.push(...this.preOrder(child));
    });
    return ans;
  }

  public static postOrder<T>(node: Nary_Node<T>): T[] {
    const ans: T[] = [];
    node.children.forEach((child) => {
      ans.push(...this.postOrder(child));
    });
    ans.push(node.val);
    return ans;
  }

  public static levelOrder<T>(node: Nary_Node<T>): T[][] {
    const ans: T[][] = [];
    const map = new Map<number, T[]>();
    this.levelOrderHelper(node, 0, map);
    ans.push(...map.values());
    return ans;
  }

  private static levelOrderHelper<T>(
    node: Nary_Node<T>,
    level: number,
    map: Map<number, T[]>,
  ) {
    if (!map.has(level)) map.set(level, []);
    map.get(level)!.push(node.val);
    node.children.forEach((child) =>
      this.levelOrderHelper(child, level + 1, map)
    );
  }
}

const a = new Nary_Node("a");
const b = new Nary_Node("b");
const c = new Nary_Node("c");
const d = new Nary_Node("d");
const e = new Nary_Node("e");
const f = new Nary_Node("f");
const g = new Nary_Node("g");

a.children.push(b, c, d);
b.children.push(e);
c.children.push(f);
e.children.push(g);

console.log(
  N_aryTree.levelOrder(a),
);
console.log(
  N_aryTree.postOrder(a),
);
console.log(
  N_aryTree.preOrder(a),
);
