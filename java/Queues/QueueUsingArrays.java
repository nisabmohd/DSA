package Queues;

import java.util.Arrays;

public class QueueUsingArrays<T> {
    private Object[] arr;
    private int count;

    public QueueUsingArrays(int size) {
        arr = new Object[size];
        count = 0;
    }

    public int count() {
        return count;
    }


    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == arr.length;
    }

    public boolean add(T val) {
        if (isFull()) return false;
        arr[count++] = val;
        return true;
    }

    public T remove() {
        if (isEmpty()) return null;
        var val = (T) arr[0];
        for (int i = 1; i < count; i++) {
            arr[i - 1] = arr[i];
        }
        count--;
        arr[count] = null;
        return val;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
