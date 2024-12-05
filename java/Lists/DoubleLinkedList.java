package Lists;

import definitions.LinkedLists;

import java.util.Iterator;

public class DoubleLinkedList<T> implements Iterable<T>, LinkedLists<T> {

    public static class DoubleLinkedListNode<E> {
        E val;
        DoubleLinkedListNode<E> prev, next;

        public DoubleLinkedListNode(E val) {
            this.val = val;
        }

        public DoubleLinkedListNode(E val, DoubleLinkedListNode<E> prev, DoubleLinkedListNode<E> next) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }

    }

    public class DoubleLinkedListIterator<E> implements Iterator<E> {
        private DoubleLinkedListNode node = DoubleLinkedList.this.head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            var val = node.val;
            node = node.next;
            return (E) val;
        }
    }

    public class DoubleLinkedListDescIterator<E> implements Iterator<E> {
        private DoubleLinkedListNode node = DoubleLinkedList.this.tail;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            var val = node.val;
            node = node.prev;
            return (E) val;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<>();
    }

    public Iterator<T> descendingIterator() {
        return new DoubleLinkedListDescIterator<>();
    }

    private DoubleLinkedListNode<T> head, tail;
    private int size = 0;

    @Override
    public void add(T val) {
        addLast(val);
    }

    private boolean isValidInsertIndex(int index) {
        return index > 0 && index <= size;
    }

    private boolean isValidGetIndex(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public void add(int index, T val) {
        if (!isValidInsertIndex(index)) return;
        if (index == size) {
            addLast(val);
            return;
        }
        var node = head;
        while (index - 1 > 0) {
            node = node.next;
            index--;
        }
        var next = node.next;
        var newNode = new DoubleLinkedListNode<>(val, node, next);
        node.next = newNode;
        next.prev = newNode;
        size++;
    }

    private void init(T val) {
        head = new DoubleLinkedListNode<>(val);
        tail = head;
        size = 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
    }

    @Override
    public void addFirst(T val) {
        if (isEmpty()) {
            init(val);
            return;
        }
        var node = new DoubleLinkedListNode<>(val, null, head);
        head.prev = node;
        head = node;
        size++;
    }

    @Override
    public void addLast(T val) {
        if (isEmpty()) {
            init(val);
            return;
        }
        tail.next = new DoubleLinkedListNode<>(val, tail, null);
        size++;
        tail = tail.next;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T getFirst() {
        if (isEmpty()) return null;
        return head.val;
    }

    @Override
    public T getLast() {
        if (isEmpty()) return null;
        return tail.val;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || !isValidGetIndex(index)) return null;
        if (size - 1 == index) return getLast();
        var temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    @Override
    public int indexOf(T val) {
        var temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.val.equals(val)) return i;
            temp = temp.next;
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T val) {
        var temp = head;
        int i = 0;
        int foundIndex = -1;
        while (temp != null) {
            if (temp.val.equals(val)) foundIndex = i;
            temp = temp.next;
            i++;
        }
        return foundIndex;
    }

    @Override
    public T remove(int index) {
        if (isEmpty() || !isValidGetIndex(index)) return null;
        if (index == size - 1) return removeLast();
        if (index == 0) return removeFirst();
        var temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        var val = temp.val;
        var prev = temp.prev;
        var next = temp.next;
        if (prev != null)
            prev.next = next;
        if (next != null)
            next.prev = prev;
        size--;
        return val;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        var val = head.val;
        head = head.next;
        if (head != null)
            head.prev = null;
        else tail = null;
        size--;
        return val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        var val = tail.val;
        tail = tail.prev;
        if (tail != null)
            tail.next = null;
        else head = null;
        size--;
        return val;
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public void set(int index, T val) {
        if (isEmpty() || !isValidGetIndex(index)) return;
        var temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.val = val;
    }

    @Override
    public int size() {
        return this.size;
    }

}
