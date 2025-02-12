package Trees;

import definitions.Queues;

import java.util.*;

// Also known as PriorityQueue
// Defaults to MinHeap
public class Heap<T> implements Queues<T> {
    private final Comparator<T> comparator;
    private int size;
    private ArrayList<T> list;

    public Heap() {
        init();
        comparator = getObjectComparator();
    }

    public Heap(Comparator<T> comparator) {
        init();
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }

    public Heap(Collection<T> data) {
        this();
        Objects.requireNonNull(data);
        ArrayList<T> givenList = new ArrayList<>(data);
        buildHeap(givenList);
    }

    public Heap(Comparator<T> comparator, Collection<T> data) {
        init();
        Objects.requireNonNull(data);
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
        ArrayList<T> givenList = new ArrayList<>(data);
        buildHeap(givenList);
    }

    @SuppressWarnings("unchecked")
    private Comparator<T> getObjectComparator() {
        return (a, b) -> ((Comparable<T>) a).compareTo(b);
    }

    @Override
    public void add(T val) {
        list.add(size, val);
        var currentIndex = size;
        while (currentIndex > 1) {
            var currentVal = list.get(currentIndex);
            var parentIndex = currentIndex / 2;
            var parentValue = list.get(parentIndex);
            if (comparator.compare(parentValue, currentVal) > 0) {
                list.set(currentIndex, parentValue);
                list.set(parentIndex, currentVal);
                currentIndex = parentIndex;
            } else break;
        }
        size++;
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        T returnVal = list.get(1);
        size--;
        list.set(1, list.get(size));
        list.set(size, null);
        int currentIndex = 1;
        while (currentIndex < size) {
            int leftIndex = 2 * currentIndex;
            int rightIndex = (2 * currentIndex) + 1;
            int minIndex = currentIndex;
            if (leftIndex < size && comparator.compare(list.get(minIndex), list.get(leftIndex)) > 0) {
                minIndex = leftIndex;
            }
            if (rightIndex < size && comparator.compare(list.get(minIndex), list.get(rightIndex)) > 0) {
                minIndex = rightIndex;
            }
            if (minIndex != currentIndex) {
                var minIndexValue = list.get(minIndex);
                var currentIndexValue = list.get(currentIndex);
                list.set(currentIndex, minIndexValue);
                list.set(minIndex, currentIndexValue);
                currentIndex = minIndex;
            } else break;
        }

        return returnVal;
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
        return list.stream().skip(1).filter(Objects::nonNull).toList().toString();
    }

    private void buildHeap(ArrayList<T> givenArray) {
        list.addAll(givenArray);
        size += givenArray.size();
        for (int i = size() / 2; i > 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int currentIndex) {
        int leftIndex = currentIndex * 2;
        int rightIndex = (currentIndex * 2) + 1;
        var minIndex = currentIndex;
        if (leftIndex < size - 1 &&
                comparator.compare(list.get(minIndex), list.get(leftIndex)) > 0) {
            minIndex = leftIndex;
        }
        if (rightIndex < size - 1 &&
                comparator.compare(list.get(minIndex), list.get(rightIndex)) > 0) {
            minIndex = rightIndex;
        }
        if (currentIndex != minIndex) {
            var minIndexValue = list.get(minIndex);
            var currentIndexValue = list.get(currentIndex);
            list.set(currentIndex, minIndexValue);
            list.set(minIndex, currentIndexValue);
            heapify(minIndex);
        }
    }

}
