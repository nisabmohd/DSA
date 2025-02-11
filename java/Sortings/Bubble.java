package Sortings;


import java.util.Comparator;

public class Bubble {
    public static <T> void sort(T[] arr, Comparator<T> comp) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (comp.compare(arr[j], arr[j + 1]) > 0) {
                    Util.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, Comparable::compareTo);
    }
}
