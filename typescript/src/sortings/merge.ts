import { CompareFn } from "../util.ts";

function sortHelper<T>(
  arr: T[],
  low: number,
  high: number,
  compare: CompareFn<T>,
) {
  if (low >= high) return;
  const half = Math.round((low + high) / 2);
  sortHelper(arr, low, half - 1, compare);
  sortHelper(arr, half, high, compare);
  merge(
    { arr, start: low, end: half - 1 },
    { arr, start: half, end: high },
    compare,
  );
}

type ArrayWithRange<T> = {
  arr: T[];
  start: number;
  end: number;
};

function merge<T>(
  a: ArrayWithRange<T>,
  b: ArrayWithRange<T>,
  compare: CompareFn<T>,
) {
  const ansArr: T[] = [];
  let i = a.start, j = b.start;
  while (i <= a.end && j <= b.end) {
    if (compare(a.arr[i], b.arr[j]) <= 0) {
      ansArr.push(a.arr[i++]);
    } else {
      ansArr.push(b.arr[j++]);
    }
  }
  while (i <= a.end) ansArr.push(a.arr[i++]);
  while (j <= b.end) ansArr.push(b.arr[j++]);
  for (let k = 0; k < ansArr.length; k++) {
    a.arr[a.start + k] = ansArr[k];
  }
  return ansArr;
}

export default function sort<T>(arr: T[], compare: CompareFn<T>) {
  sortHelper(arr, 0, arr.length - 1, compare);
}
