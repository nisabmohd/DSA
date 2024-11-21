package Queues;

public interface Queues<T> {
    public void add(T val);

    public T remove();

    public int size();

    public T peek();

    public boolean isEmpty();

    public void clear();
}
