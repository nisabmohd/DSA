package Lists;

public interface LinkedLists<T> {
    public void add(T val);

    public void add(int index, T val);

    public boolean isEmpty();

    public void addFirst(T val);

    public void addLast(T val);

    public void clear();

    public T element();

    public T getFirst();

    public T getLast();

    public T get(int index);

    public int indexOf(T val);

    public int lastIndexOf(T val);

    public T remove(int index);

    public T removeFirst();

    public T removeLast();

    public T remove();

    public void set(int index, T val);

    public int size();
}
