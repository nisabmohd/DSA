package Sortings;

import java.util.Comparator;

public class Selection {
    private static <T> int selectElement(T[] arr, int min, int max, Comparator<T> c) {
        int index = min;
        for (int i = min + 1; i <= max; i++) {
            if (c.compare(arr[i], arr[index]) > 0) index = i;
        }
        return index;
    }

    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length; i++) {
            var lastIndex = arr.length - 1 - i;
            var selectedIndex = selectElement(arr, 0, lastIndex, comparator);
            Util.swap(arr, selectedIndex, lastIndex);
        }
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, Comparable::compareTo);
    }
}
