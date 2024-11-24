package Queues;

public class CircularDeque<T> {
    private Object[] arr;
    private int size, front, rear, capacity;

    public CircularDeque(int capacity) {
        this.arr = new Object[capacity + 1];
        this.capacity = capacity + 1;
        this.rear = 0;
        this.front = 0;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity - 1;
    }

    public void addFirst(T val) {
        if (isFull()) return;
        front = (front - 1 + capacity) % capacity;
        arr[front] = val;
        size++;
    }


    public void addLast(T val) {
        if (isFull()) return;
        arr[rear] = val;
        rear = (rear + 1) % capacity;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        var val = (T) arr[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        var val = (T) arr[rear];
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return val;
    }

    public T peekFirst() {
        if (isEmpty()) return null;
        return (T) arr[front];
    }

    public T peekLast() {
        if (isEmpty()) return null;
        var index = (rear - 1 + capacity) % capacity;
        return (T) arr[index];
    }

}
