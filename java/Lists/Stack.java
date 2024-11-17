package Lists;

public class Stack<E> {
    private DoubleLinkedList<E> list;
    private Integer maxCapacity;

    public Stack() {
        this.list = new DoubleLinkedList<>();
        maxCapacity = null;
    }

    public Stack(int maxCapacity) {
        this.list = new DoubleLinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    public void push(E val) {
        if (maxCapacity != null && list.size() == maxCapacity) throw new RuntimeException("Stack overflow");
        list.addLast(val);
    }

    public int size() {
        return list.size();
    }

    public E peek() {
        return list.getLast();
    }

    public E pop() {
        return list.removeLast();
    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
