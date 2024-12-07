export default class CircularDeqeue<T> {
  private arr: T[];
  private arrSize: number;
  private count: number;

  private front: number;
  private rear: number;

  constructor(size: number) {
    this.arrSize = size + 1;
    this.arr = new Array(size + 1);
    this.count = 0;
    this.front = 0;
    this.rear = 0;
  }

  public clear() {
    this.arr = new Array(this.arrSize);
    this.count = 0;
  }

  private isEmpty() {
    return this.count == 0;
  }

  private isFull() {
    return this.count == this.arrSize - 1;
  }

  public addFirst(val: T) {
    if (this.isFull()) return;
    this.front = (this.front - 1 + this.arrSize) % this.arrSize;
    this.arr[this.front] = val;
    this.count++;
  }

  public addLast(val: T) {
    if (this.isFull()) return;
    this.arr[this.rear] = val;
    this.rear = (this.rear + 1) % this.arrSize;
    this.count++;
  }

  public removeFirst() {
    if (this.isFull()) return;
    const val = this.arr[this.front];
    this.front = (this.front - 1 + this.arrSize) % this.arrSize;
    this.count--;
    return val;
  }

  public removeLast() {
    if (this.isFull()) return;
    const val = this.arr[this.rear];
    this.rear = (this.rear - 1 + this.arrSize) % this.arrSize;
    this.count--;
    return val;
  }

  public peekFirst() {
    if (this.isEmpty()) return null;
    return this.arr[this.front];
  }

  public peekLast() {
    if (this.isEmpty()) return null;
    return this.arr[(this.rear - 1 + this.arrSize) % this.arrSize];
  }

  public get size() {
    return this.arrSize;
  }
}
