package definitions;

public interface Sets<T> {
    public void clear();

    public void add(T val);

    public boolean contains(T val);

    public boolean isEmpty();

    public T remove(T val);

    public int size();
}
