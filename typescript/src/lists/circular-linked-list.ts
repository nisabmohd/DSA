import { SingleLinkNode } from "./single-linked-list.ts";

export default class SingleLinkedList<T> {
  private head: SingleLinkNode<T> | null;
  private tail: SingleLinkNode<T> | null;
  private count: number;

  public constructor() {
    this.head = null;
    this.tail = null;
    this.count = 0;
  }

  public get size() {
    return this.count;
  }

  private isValidGetIndex(index: number) {
    return index >= 0 && index < this.size;
  }

  private isValidAddIndex(index: number) {
    return index >= 0 && index <= this.size;
  }

  private init(val: T) {
    this.head = new SingleLinkNode(val);
    this.tail = this.head;
    this.tail!.next = this.head;
    this.count = 1;
  }

  public addFirst(val: T) {
    if (this.size == 0) return this.init(val);
    const node = new SingleLinkNode(val);
    node.next = this.head;
    this.tail!.next = node;
    this.head = node;
    this.count++;
  }

  public addLast(val: T) {
    if (this.size == 0) return this.init(val);
    const node = new SingleLinkNode(val);
    this.tail!.next = node;
    node.next = this.head;
    this.tail = node;
    this.count++;
  }

  public add(val: T, index?: number) {
    index ??= this.size;
    if (!this.isValidAddIndex(index)) return;
    if (index == 0) return this.addFirst(val);
    if (index == this.size) return this.addLast(val);
    let temp = this.head;
    while (index - 1 > 0) {
      temp = temp!.next;
      index--;
    }
    const node = new SingleLinkNode(val);
    node.next = temp!.next;
    temp!.next = node;
    this.count++;
  }

  public getFirst() {
    if (this.size == 0) return null;
    return this.head!.val;
  }

  public getLast() {
    if (this.size == 0) return null;
    return this.tail!.val;
  }

  public get(index: number) {
    if (!this.isValidGetIndex(index)) return null;
    if (index == 0) return this.getFirst();
    if (index == this.size - 1) return this.getLast();
    let temp = this.head;
    while (index > 0) {
      temp = temp!.next;
      index--;
    }
    return temp!.val;
  }

  public removeFirst() {
    if (this.size == 0) return null;
    const val = this.head!.val;
    this.head != this.head!.next;
    if (this.head == null) {
      this.clear();
      return val;
    }
    this.tail!.next = this.head;
    this.count--;
    return val;
  }

  public removeLast() {
    if (this.size == 0) return null;
    const val = this.tail!.val;
    if (this.size == 1) {
      this.clear();
      return val;
    }
    let temp = this.head;
    while (temp!.next != this.tail) {
      temp = temp!.next;
    }
    temp!.next = this.head;
    this.count--;
    return val;
  }

  public remove(index?: number) {
    index ??= this.size - 1;
    if (!this.isValidGetIndex(index)) return null;
    if (index == 0) return this.removeFirst();
    if (index == this.size - 1) return this.removeLast();
    let temp = this.head;
    while (index > 0) {
      temp = temp!.next;
      index--;
    }
    temp!.next = temp!.next!.next;
    this.count--;
  }

  public set(index: number, val: T) {
    if (!this.isValidGetIndex(index)) return;
    let temp = this.head;
    while (index > 0) {
      temp = temp!.next;
      index--;
    }
    temp!.val = val;
  }

  public includes(val: T) {
    let temp = this.head;
    while (temp != null) {
      if (val == temp!.val) return true;
      temp = temp!.next;
    }
    return false;
  }

  public clear() {
    this.head = null;
    this.tail = null;
    this.count = 0;
  }

  public toArray() {
    const arr: T[] = [];
    if (this.size == 0) return arr;
    arr.push(this.head!.val);
    if (this.size == 1) {
      return arr;
    }
    let temp = this.head!.next;
    while (temp != this.head) {
      arr.push(temp!.val);
      temp = temp!.next;
    }
    return arr;
  }
}
