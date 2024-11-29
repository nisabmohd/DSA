package Trees;

import definitions.Queues;

import java.util.ArrayList;
import java.util.Comparator;

// Also known as PriorityQueue
// Defaults to MinHeap
public class Heap<T extends Comparable<T>> implements Queues<T> {
    private Comparator<T> comparator;
    private int size;
    private ArrayList<T> list;

    public Heap() {
        init();
        this.comparator = Comparable::compareTo;
    }

    public Heap(Comparator<T> comparator) {
        this();
        this.comparator = comparator;
    }

    @Override
    public void add(T val) {
        list.add(size, val);
        var currentIndex = size;
        while (currentIndex > 1) {
            var element = list.get(currentIndex);
            var parentIndex = currentIndex / 2;
            var parentValue = list.get(parentIndex);
            if (comparator.compare(parentValue, element) > 0) {
                list.set(currentIndex, parentValue);
                list.set(parentIndex, element);
                currentIndex = parentIndex;
            } else break;
        }
        size++;
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        var removedValue = list.get(1);
        size--;
        var lastValue = list.get(size);
        list.set(size, null);
        if (size > 1) {
            list.set(1, lastValue);
        }
        var currentIndex = 1;
        int lastIndex = size - 1;
        while (currentIndex < lastIndex) {
            var leftChildIndex = 2 * currentIndex;
            var rightChildIndex = (2 * currentIndex) + 1;
            var leftChildValue = list.get(leftChildIndex);
            var rightChildValue = list.get(rightChildIndex);
            var currentVal = list.get(currentIndex);
            var minValueIndex = currentIndex;
            if (leftChildIndex < size && comparator.compare(leftChildValue, currentVal) < 0) {
                minValueIndex = leftChildIndex;
            }
            if (rightChildIndex < size && comparator.compare(rightChildValue, currentVal) > 0) {
                minValueIndex = rightChildIndex;
            }
            if (minValueIndex == currentIndex) break;
            var minValue = list.get(minValueIndex);
            list.set(minValueIndex, currentVal);
            list.set(currentIndex, minValue);
            currentIndex = minValueIndex;
        }
        return removedValue;
    }

    @Override
    public int size() {
        return size - 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 1;
    }

    @Override
    public T peek() {
        return list.get(1);
    }

    private void init() {
        list = new ArrayList<>();
        list.add(null);
        size = 1;
    }

    @Override
    public void clear() {
        init();
    }

    public String toString() {
        return list.stream().skip(1).filter(item -> item != null).toList().toString();
    }

}
