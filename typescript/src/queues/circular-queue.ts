export default class CircularQueue<T> {
  private arr: T[];
  private arrSize: number;
  private count: number;

  private rear: number;
  private front: number;

  constructor(size: number) {
    this.arrSize = size;
    this.arr = new Array(size);
    this.count = 0;
    this.rear = -1;
    this.front = -1;
  }

  private isEmpty() {
    return this.rear == -1 && this.front == -1;
  }

  private isFull() {
    return (this.rear + 1) % this.arrSize == this.front;
  }

  public clear() {
    this.arr = new Array(this.arrSize);
    this.count = 0;
    this.front = -1;
    this.rear = -1;
  }

  public add(val: T) {
    if (this.isFull()) return;
    if (this.isEmpty()) {
      this.arr[this.rear++] = val;
      this.front++;
      this.count++;
      return;
    }
    this.rear = (this.rear + 1) % this.arrSize;
    this.arr[this.rear] = val;
    this.count++;
  }

  public remove() {
    if (this.isEmpty()) return null;
    const val = this.arr[this.front];
    this.front = (this.front + 1) % this.arrSize;
    if (this.isEmpty()) {
      this.clear();
    } else this.count--;
    return val;
  }

  public peek() {
    if (this.isEmpty()) return null;
    return this.arr[this.front];
  }

  public get size() {
    return this.arrSize;
  }
}
