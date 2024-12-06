class DoubleLinkNode<T> {
  val: T;
  prev: DoubleLinkNode<T> | null;
  next: DoubleLinkNode<T> | null;

  constructor(val: T) {
    this.val = val;
    this.next = null;
    this.prev = null;
  }
}

export default class DoubleLinkedList<T> {
  private head: DoubleLinkNode<T> | null;
  private tail: DoubleLinkNode<T> | null;
  private count: number;

  public constructor() {
    this.head = null;
    this.tail = null;
    this.count = 0;
  }

  private isValidAddIndex(index: number) {
    return index >= 0 && index <= this.count;
  }

  private isValidGetIndex(index: number) {
    return index >= 0 && index < this.count;
  }

  public get size() {
    return this.count;
  }

  private init(val: T) {
    this.head = new DoubleLinkNode(val);
    this.tail = this.head;
    this.count++;
  }

  public addFirst(val: T) {
    if (this.size == 0) return this.init(val);
    const node = new DoubleLinkNode(val);
    node.next = this.head;
    this.head!.prev = node;
    this.head = node;
    this.count++;
  }

  public addLast(val: T) {
    if (this.size == 0) return this.init(val);
    const node = new DoubleLinkNode(val);
    this.tail!.next = node;
    node.prev = this.tail;
    this.tail = node;
    this.count++;
  }

  public add(val: T, index?: number) {
    index ??= this.size;
    if (!this.isValidAddIndex(index)) return;
    if (index == this.size) return this.addLast(val);
    if (index == 0) return this.addFirst(val);
    let temp = this.head;
    while (index - 1 > 0) {
      temp = temp!.next;
      index--;
    }
    const node = new DoubleLinkNode(val);
    const next = temp!.next;
    temp!.next = node;
    node.prev = temp;
    node.next = next;
    next!.prev = node;
    this.count++;
  }

  public getFirst(): T | null {
    if (this.size == 0) return null;
    return this.head!.val;
  }

  public getLast(): T | null {
    if (this.size == 0) return null;
    return this.tail!.val;
  }

  public get(index: number): T | null {
    if (!this.isValidGetIndex(index)) return null;
    if (index == 0) return this.getFirst();
    if (index == this.size - 1) return this.getLast();
    let temp = this.head;
    while (index > 0) {
      index--;
      temp = temp!.next;
    }
    return temp!.val;
  }

  public removeFirst(): T | null {
    if (this.size == 0) return null;
    const val = this.head!.val;
    this.head = this.head!.next;
    if (this.head) {
      this.head.prev = null;
    }
    this.count--;
    return val;
  }

  public removeLast(): T | null {
    if (this.size == 0) return null;
    const val = this.tail!.val;
    this.tail = this.tail!.prev;
    if (this.tail) {
      this.tail.next = null;
    }
    this.count--;
    return val;
  }

  public remove(index?: number): T | null {
    index ??= this.size - 1;
    if (!this.isValidGetIndex(index)) return null;
    if (index == this.size - 1) return this.removeLast();
    if (index == 0) return this.removeFirst();
    let temp = this.head;
    while (index > 0) {
      temp = temp!.next;
      index--;
    }
    const val = temp!.val;
    const prev = temp!.prev;
    const next = temp!.next;
    if (prev) {
      prev.next = next;
    }
    if (next) {
      next.prev = prev;
    }
    this.count--;
    return val;
  }

  public set(index: number, val: T) {
    if (!this.isValidGetIndex(index)) return;
    let temp = this.head;
    while (index > 0) {
      index--;
      temp = temp!.next;
    }
    temp!.val = val;
  }

  public clear() {
    this.head = null;
    this.tail = null;
    this.count = 0;
  }

  public toArray() {
    const arr: T[] = [];
    let temp = this.head;
    while (temp != null) {
      arr.push(temp.val);
      temp = temp.next;
    }
    return arr;
  }

  public toArrayReverse() {
    const arr: T[] = [];
    let temp = this.tail;
    while (temp != null) {
      arr.push(temp.val);
      temp = temp.prev;
    }
    return arr;
  }
}
