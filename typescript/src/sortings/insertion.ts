import { CompareFn, swap } from "../util.ts";

export default function sort<T>(arr: T[], compare: CompareFn<T>) {
  for (let i = 1; i < arr.length; i++) {
    let j = i;
    while (j > 0) {
      if (compare(arr[j], arr[j - 1]) < 0) {
        swap(arr, j - 1, j);
      } else break;
      j--;
    }
  }
}
