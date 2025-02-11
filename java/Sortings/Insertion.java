package Sortings;

import java.util.Comparator;

public class Insertion {
    public static <T> void sort(T[] arr, Comparator<T> comp) {
        for (int i = 0; i < arr.length - 1; i++) {
            int j = i + 1;
            while (j > 0) {
                if (comp.compare(arr[j], arr[j - 1]) < 0) {
                    Util.swap(arr, j - 1, j);
                } else break;
                j--;
            }
        }
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, Comparable::compareTo);
    }
}
