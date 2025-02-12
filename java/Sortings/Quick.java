package Sortings;

import java.util.Comparator;

public class Quick {

    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        sort(arr, 0, arr.length - 1, comparator);
    }

    private static <T> void sort(T[] arr, int low, int high, Comparator<T> comparator) {
        if (low >= high) return;
        T pivot = arr[(low + high) / 2];
        int s = low, e = high;
        while (s <= e) {
            while (comparator.compare(arr[s], pivot) < 0) {
                s++;
            }
            while (comparator.compare(arr[e], pivot) > 0) {
                e--;
            }
            if (s <= e) {
                Util.swap(arr, s, e);
                s++;
                e--;
            }
        }
        sort(arr, low, e, comparator);
        sort(arr, s, high, comparator);
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, Comparable::compareTo);
    }
}
