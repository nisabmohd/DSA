package Queues;

import definitions.Queues;

import java.util.Arrays;

public class CircularQueue<T> implements Queues<T> {

    private Object[] arr;
    private int front, rear, count;

    public CircularQueue(int capacity) {
        arr = new Object[capacity];
        init();
    }

    private void init() {
        count = 0;
        front = -1;
        rear = -1;
    }

    @Override
    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    @Override
    public void clear() {
        Arrays.fill(arr, null);
        count = 0;
    }

    private boolean isFull() {
        return (rear + 1) % arr.length == front;
    }

    @Override
    public void add(T val) {
        if (isFull()) return;
        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % arr.length;
        }
        arr[rear] = val;
        count++;
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        var val = (T) arr[front];
        arr[front] = null;
        front = (front + 1) % arr.length;
        if (isEmpty()) init();
        else count--;
        return val;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public T peek() {
        return (T) arr[front];
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

}
