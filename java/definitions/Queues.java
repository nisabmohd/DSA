package definitions;

public interface Queues<T> {
    public void add(T val);

    public T remove();

    public int size();

    default public T peek() {
        throw new Error("Method not implemeted");
    }

    public boolean isEmpty();

    public void clear();
}
