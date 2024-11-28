package Trees;

import definitions.Queues;
import java.util.Comparator;

// todo
// Also known as PriorityQueue
public class Heap<T extends Comparable<T>> implements Queues<T> {
    private Comparator<T> comparator;
    private int size;

    public Heap() {

    }

    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void add(T val) {

    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
