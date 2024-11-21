package Queues;

import Lists.DoubleLinkedList;
import definitions.Queues;

public class Queue<T> implements Queues<T> {

    // could have extended directly to DLL but want to limit the features like standard queue
    private DoubleLinkedList<T> list;

    public Queue() {
        this.list = new DoubleLinkedList<>();
    }


    @Override
    public void add(T val) {
        list.addLast(val);
    }

    @Override
    public T remove() {
        return list.removeFirst();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T peek() {
        return list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }
}
