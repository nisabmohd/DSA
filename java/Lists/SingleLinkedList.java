package Lists;

import java.util.Iterator;

public class SingleLinkedList<T> implements Iterable<T> {

    public class SingleLinkedListIterator<E> implements Iterator<E> {
        private SingleLinkedListNode node = SingleLinkedList.this.head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            var ans = node.val;
            node = node.next;
            return (E) ans;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator<>();
    }

    public static class SingleLinkedListNode<V> {
        V val;
        SingleLinkedListNode next;

        public SingleLinkedListNode(V val) {
            this.val = val;
        }

        public SingleLinkedListNode(V val, SingleLinkedListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private SingleLinkedListNode<T> head, tail;
    private int size = 0;

    private SingleLinkedListNode createNode(T val) {
        return new SingleLinkedListNode<>(val);
    }

    private void init(T val) {
        var node = createNode(val);
        head = node;
        tail = head;
        size = 1;
    }

    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
    }

    public void add(T val) {
        addLast(val);
    }

    private void add(int index, T val) {
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
        var node = new SingleLinkedListNode<>(val, next);
        temp.next = node;
        size++;
    }

    public void addFirst(T val) {
        if (isEmpty()) {
            init(val);
            return;
        }
        var node = createNode(val);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(T val) {
        if (isEmpty()) {
            init(val);
            return;
        }
        var node = createNode(val);
        tail.next = node;
        tail = tail.next;
        size++;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public T element() {
        return getFirst();
    }

    private boolean isValidInsertIndex(int index) {
        return index > 0 && index <= size;
    }

    private boolean isValidGetIndex(int index) {
        return index > 0 && index < size;
    }

    public T get(int index) {
        if (isEmpty() || !isValidGetIndex(index)) return null;
        if (size - 1 == index) return getLast();
        var temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    public T getFirst() {
        if (isEmpty()) return null;
        return head.val;
    }

    public T getLast() {
        if (isEmpty()) return null;
        return tail.val;
    }

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


    public T remove() {
        return removeFirst();
    }

    public T remove(int index) {
        if (!isValidGetIndex(index)) return null;
        var node = head;
        while (index - 1 > 0) {
            node = node.next;
            index--;
        }
        SingleLinkedListNode next = null;
        if (node.next != null) next = node.next.next;
        node.next = next;
        size--;
        return null;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        var val = head.val;
        head = head.next;
        size--;
        return val;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        var temp = head;
        while (temp != tail) {
            temp = temp.next;
        }
        temp.next = null;
        tail = temp;
        size--;
        return tail.val;
    }

    public void set(int index, T val) {
        if (!isValidGetIndex(index)) return;
        var node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }
        node.val = val;

    }

    public int size() {
        return this.size;
    }

}
