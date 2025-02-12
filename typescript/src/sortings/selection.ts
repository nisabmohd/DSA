import { CompareFn, swap } from "../util.ts";

function select<T>(arr: T[], s: number, e: number, compare: CompareFn<T>) {
  let selectedIndex = s;
  for (let i = s + 1; i <= e; i++) {
    if (compare(arr[i], arr[selectedIndex]) > 0) selectedIndex = i;
  }
  return selectedIndex;
}

export default function sort<T>(arr: T[], compare: CompareFn<T>) {
  for (let i = 0; i < arr.length; i++) {
    const lastIndex = arr.length - 1 - i;
    const selectedIndex = select(arr, 0, lastIndex, compare);
    swap(arr, selectedIndex, lastIndex);
  }
}
