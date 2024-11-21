package Lists;

import definitions.LinkedLists;

import java.util.Iterator;

public class CircularLinkedList<T> implements Iterable<T>, LinkedLists<T> {

    private SingleLinkedList.SingleLinkedListNode<T> head, tail;
    private int size = 0;

    private void init(T val) {
        var node = new SingleLinkedList.SingleLinkedListNode<T>(val);
        head = node;
        tail = head;
        size = 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
    }

    @Override
    public void add(T val) {
        addLast(val);
    }

    @Override
    public void add(int index, T val) {
        if (!isValidInsertIndex(index)) return;
        if (index == size) {
            addLast(val);
            return;
        }
        var temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        var next = temp.next;
        var node = new SingleLinkedList.SingleLinkedListNode<>(val, next);
        temp.next = node;
        size++;
    }

    @Override
    public void addFirst(T val) {
        if (isEmpty()) {
            init(val);
            return;
        }
        var node = new SingleLinkedList.SingleLinkedListNode<T>(val, head);
        tail.next = node;
        node.next = head;
        head = node;
        size++;
    }

    @Override
    public void addLast(T val) {
        if (isEmpty()) {
            init(val);
            return;
        }
        var node = new SingleLinkedList.SingleLinkedListNode(val, head);
        tail.next = node;
        node.next = head;
        tail = node;
        size++;
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

    private boolean isValidInsertIndex(int index) {
        return index > 0 && index <= size;
    }

    private boolean isValidGetIndex(int index) {
        return index > 0 && index < size;
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
    public int indexOf(T val) {
        var temp = head;
        int i = 0;
        do {
            if (temp.val.equals(val)) return i;
            temp = temp.next;
            i++;
        } while (temp != head);
        return -1;
    }

    @Override
    public int lastIndexOf(T val) {
        var temp = head;
        int i = 0;
        int foundIndex = -1;
        do {
            if (temp.val.equals(val)) foundIndex = i;
            temp = temp.next;
            i++;
        } while (temp != null);
        return foundIndex;
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T remove(int index) {
        if (!isValidGetIndex(index)) return null;
        var node = head;
        while (index - 1 > 0) {
            node = node.next;
            index--;
        }
        SingleLinkedList.SingleLinkedListNode next = null;
        if (node.next != null) next = node.next.next;
        node.next = next;
        size--;
        return null;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        var val = head.val;
        head = head.next;
        if (head == null) {
            clear();
            return val;
        }
        tail = head;
        size--;
        return val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        var val = tail.val;
        if (head == tail) {
            clear();
            return val;
        }
        var temp = head;
        while (temp.next != tail) {
            temp = temp.next;
        }
        temp.next = head;
        size--;
        return val;
    }

    @Override
    public void set(int index, T val) {
        if (!isValidGetIndex(index)) return;
        var node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }
        node.val = val;

    }

    @Override
    public int size() {
        return this.size;
    }

    public class CircularLinkedListItr<T> implements Iterator<T> {
        private SingleLinkedList.SingleLinkedListNode node = CircularLinkedList.this.head;
        int attempt = 0;

        @Override
        public boolean hasNext() {
            return attempt < CircularLinkedList.this.size;
        }

        @Override
        public T next() {
            var val = (T) node.val;
            node = node.next;
            attempt++;
            return val;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
