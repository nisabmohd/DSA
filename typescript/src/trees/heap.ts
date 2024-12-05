import type { CompareFn } from "../util.ts";

type Data<T> = T | null;

// default min heap assumption
export default class Heap<T> {
  private compare: CompareFn<T>;
  private arr: Data<T>[];

  public constructor(compare: CompareFn<T>, initialData: T[] = []) {
    this.arr = [null];
    this.compare = compare;
    this.arr.push(...initialData);
    this.buildHeap();
  }

  private buildHeap() {
    const notLeftIndex = Math.floor(this.arr.length / 2);
    for (let i = notLeftIndex; i > 0; i--) {
      this.heapify(i);
    }
  }

  private heapify(currentIndex: number) {
    const leftIndex = currentIndex * 2;
    const rightIndex = (currentIndex * 2) + 1;
    let minIndex = currentIndex;
    if (
      leftIndex <= this.size &&
      this.compare(this.arr[minIndex] as T, this.arr[leftIndex] as T) > 0
    ) {
      minIndex = leftIndex;
    }
    if (
      rightIndex <= this.size &&
      this.compare(this.arr[minIndex] as T, this.arr[rightIndex] as T) > 0
    ) {
      minIndex = rightIndex;
    }
    if (minIndex != currentIndex) {
      const minValue = this.arr[minIndex];
      const currentValue = this.arr[currentIndex];
      this.arr[minIndex] = currentValue;
      this.arr[currentIndex] = minValue;
      currentIndex = minIndex;
      this.heapify(currentIndex);
    }
  }

  public get top(): T | null {
    return this.arr[1] ?? null;
  }

  public get size(): number {
    return this.arr.length - 1;
  }

  public add(val: T) {
    this.arr.push(val);
    let currentIndex = this.arr.length - 1;
    while (currentIndex > 1) {
      const parentIndex = Math.floor(currentIndex / 2);
      const parentValue = this.arr[parentIndex];
      const currentValue = this.arr[currentIndex];
      if (this.compare(parentValue as T, currentValue as T) > 0) {
        this.arr[parentIndex] = currentValue;
        this.arr[currentIndex] = parentValue;
        currentIndex = parentIndex;
      } else break;
    }
  }

  public toString(): string {
    return this.arr.filter((it) => !!it).toString();
  }

  public remove(): T | null {
    if (this.size == 0) return null;
    const removedValue = this.arr[1] ?? null;
    if (this.size == 1) {
      this.arr.pop();
      return removedValue;
    }
    const lastValue = this.arr.pop()!;
    this.arr[1] = lastValue;
    let currentIndex = 1;
    while (currentIndex <= this.size) {
      const leftIndex = currentIndex * 2;
      const rightIndex = (currentIndex * 2) + 1;
      let minIndex = currentIndex;
      if (
        leftIndex <= this.size &&
        this.compare(
            this.arr[minIndex] as T,
            this.arr[leftIndex] as T,
          ) > 0
      ) {
        minIndex = leftIndex;
      }
      if (
        rightIndex <= this.size &&
        this.compare(
            this.arr[minIndex] as T,
            this.arr[rightIndex] as T,
          ) > 0
      ) {
        minIndex = rightIndex;
      }
      if (minIndex != currentIndex) {
        const minValue = this.arr[minIndex];
        const currentValue = this.arr[currentIndex];
        this.arr[minIndex] = currentValue;
        this.arr[currentIndex] = minValue;
        currentIndex = minIndex;
      } else break;
    }
    return removedValue;
  }
}
