import Queue from "../queues/queue.ts";

export default class BiDirectionalGraph<T> {
  private adjMap: Map<T, Set<T>>;

  public constructor() {
    this.adjMap = new Map();
  }

  public addEdge(src: T, dest?: T) {
    if (!this.adjMap.has(src)) this.adjMap.set(src, new Set());
    if (!dest) return;
    if (!this.adjMap.has(dest)) this.adjMap.set(dest, new Set());
    this.adjMap.get(src)!.add(dest);
    this.adjMap.get(dest)!.add(src);
  }

  public removeEdge(src: T, dest?: T) {
    if (!dest) {
      this.adjMap.get(src)?.forEach((adjNode) => {
        this.adjMap.get(adjNode)?.delete(src);
      });
      this.adjMap.delete(src);
      return;
    }
    this.adjMap.get(src)?.delete(dest);
  }

  public bfsOfNode(src: T) {
    return this.bfsHelper(src, new Set());
  }

  private bfsHelper(src: T, vis: Set<T>) {
    const ans: T[] = [];
    const q = new Queue<T>();
    q.add(src);
    while (q.size > 0) {
      const node = q.remove()!;
      vis.add(node);
      ans.push(node);
      this.adjMap.get(node)!.forEach((adjNode) => {
        if (!vis.has(adjNode)) q.add(adjNode);
      });
    }
    return ans;
  }

  public bfs(): T[][] {
    const ans: T[][] = [];
    const vis = new Set<T>();
    this.adjMap.forEach((_, k) => {
      if (!vis.has(k)) ans.push(this.bfsHelper(k, vis));
    });
    return ans;
  }

  public dfs() {
    const ans: T[][] = [];
    const vis = new Set<T>();
    this.adjMap.forEach((_, k) => {
      if (!vis.has(k)) ans.push(this.dfsHelper(k, vis));
    });
    return ans;
  }

  public dfsOfNode(src: T) {
    if (!this.adjMap.has(src)) return [];
    return this.dfsHelper(src, new Set());
  }

  private dfsHelper(src: T, vis: Set<T>): T[] {
    const ans: T[] = [];
    ans.push(src);
    vis.add(src);
    this.adjMap.get(src)?.forEach((adjNode) => {
      if (!vis.has(adjNode)) {
        ans.push(...this.dfsHelper(adjNode, vis));
      }
    });
    return ans;
  }

  public toString() {
    return this.adjMap.entries().toArray().map(([key, val]) => {
      return [key, "->[" + [...val].join(",") + "]"];
    }).join(", ");
  }

  public get vertices() {
    return this.adjMap.size;
  }
}
