package Sortings;

import java.util.Arrays;
import java.util.Comparator;

public class Merge {
    public static <T> void sort(T[] arr, Comparator<T> comp) {
        var result = sortHelper(arr, comp);
        System.arraycopy(result, 0, arr, 0, result.length);
    }

    private static <T> T[] sortHelper(T[] arr, Comparator<T> comp) {
        if (arr.length == 1) return arr;
        int half = arr.length / 2;
        var first = sortHelper(Arrays.copyOfRange(arr, 0, half), comp);
        var second = sortHelper(Arrays.copyOfRange(arr, half, arr.length), comp);
        return merge(first, second, comp);
    }


    @SuppressWarnings("unchecked")
    private static <T> T[] merge(T[] arr1, T[] arr2, Comparator<T> comp) {
        T[] arr = (T[]) new Object[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (comp.compare(arr1[i], arr2[j]) < 0) arr[k++] = arr1[i++];
            else arr[k++] = arr2[j++];
        }
        while (i < arr1.length) {
            arr[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            arr[k++] = arr2[j++];
        }
        return arr;
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, Comparable::compareTo);
    }
}
