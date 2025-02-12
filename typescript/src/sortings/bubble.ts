import { CompareFn, swap } from "../util.ts";

export default function sort<T>(arr: T[], compare: CompareFn<T>) {
  for (let i = 0; i < arr.length - 1; i++) {
    for (let j = 0; j < arr.length - 1 - i; j++) {
      if (compare(arr[j], arr[j + 1]) > 0) {
        swap(arr, j + 1, j);
      }
    }
  }
}
