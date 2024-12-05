import DoubleLinkedList from "./double-linked-list.ts";

export default class Stack<E> {
  private list: DoubleLinkedList<E>;
  private maxCapacity: number;

  constructor(maxCapacity: number = -1) {
    this.list = new DoubleLinkedList();
    this.maxCapacity = maxCapacity;
  }

  public push(val: E) {
    if (this.maxCapacity != -1 && this.list.size == this.maxCapacity) {
      throw new Error("Stack overflow");
    }
    this.list.addLast(val);
  }

  public get size() {
    return this.list.size;
  }

  public peek() {
    return this.list.getLast();
  }

  public pop() {
    return this.list.removeLast();
  }

  public clear() {
    this.list.clear();
  }
}
