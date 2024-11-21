package Queues;

import java.util.Arrays;

public class CircularQueue<T> {

    private Object[] arr;
    private int front, rear, count;

    public CircularQueue(int size) {
        arr = new Object[size];
        init();
    }

    private void init() {
        count = 0;
        front = -1;
        rear = -1;
    }

    private boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    private boolean isFull() {
        return (rear + 1) % arr.length == front;
    }

    public boolean add(T val) {
        if (isFull()) return false;
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % arr.length;
        }
        arr[rear] = val;
        count++;
        return true;
    }

    public T remove() {
        if (isEmpty()) return null;
        var val = (T) arr[front];
        arr[front] = null;
        front = (front + 1) % arr.length;
        if (isEmpty()) init();
        else count--;
        return val;
    }


    public int count() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

}
