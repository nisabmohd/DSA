import DoubleLinkedList from "../lists/double-linked-list.ts";

export default class Queue<T> {
  // could have extended directly to DLL but want to limit the features like standard queue
  private list: DoubleLinkedList<T>;

  constructor() {
    this.list = new DoubleLinkedList();
  }

  public add(val: T) {
    this.list.addLast(val);
  }

  public remove() {
    return this.list.removeFirst();
  }

  public peek() {
    return this.list.getFirst();
  }

  public get size() {
    return this.list.size;
  }

  public clear() {
    this.list.clear();
  }
}
