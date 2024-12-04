export class Node<T> {
    val: T;
    children: Node<T>[];
    constructor(val: T) {
        this.val = val;
        this.children = [];
    }
}

export class N_aryTree {
    public static preOrder<T>(node: Node<T>): T[] {
        const ans: T[] = [];
        ans.push(node.val);
        node.children.forEach((child) => {
            ans.push(...this.preOrder(child));
        });
        return ans;
    }

    public static postOrder<T>(node: Node<T>): T[] {
        const ans: T[] = [];
        node.children.forEach((child) => {
            ans.push(...this.postOrder(child));
        });
        ans.push(node.val);
        return ans;
    }

    public static levelOrder<T>(node: Node<T>): T[][] {
        const ans: T[][] = [];
        const map = new Map<number, T[]>();
        this.levelOrderHelper(node, 0, map);
        ans.push(...map.values());
        return ans;
    }

    private static levelOrderHelper<T>(
        node: Node<T>,
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

const a = new Node("a");
const b = new Node("b");
const c = new Node("c");
const d = new Node("d");
const e = new Node("e");
const f = new Node("f");
const g = new Node("g");

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
