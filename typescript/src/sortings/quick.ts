import { CompareFn, swap } from "../util.ts";

function sortHelper<T>(
  arr: T[],
  low: number,
  high: number,
  compare: CompareFn<T>,
) {
  if (low >= high) return;
  const pivotElement = arr[Math.round((low + high) / 2)];
  let s = low, e = high;
  while (s <= e) {
    while (compare(arr[s], pivotElement) < 0) {
      s++;
    }
    while (compare(arr[e], pivotElement) > 0) {
      e--;
    }
    if (s <= e) {
      swap(arr, s, e);
      s++;
      e--;
    }
  }
  sortHelper(arr, low, e, compare);
  sortHelper(arr, s, high, compare);
}

export default function sort<T>(arr: T[], compare: CompareFn<T>) {
  sortHelper(arr, 0, arr.length - 1, compare);
}
