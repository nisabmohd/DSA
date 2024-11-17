package Lists;

public interface List<T> {
    public void add(T val);

    public void add(int index, T val);

    public boolean isEmpty();

    public void addFirst(T val);

    public void addLast(T val);
}
