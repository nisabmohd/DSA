package definitions;

import java.util.List;

public interface Graphs<T> {

    public void addEdge(T source, T destination);

    public void removeEdge(T source, T destination);

    public List<List<T>> breadthFirstSearch();

    public List<List<T>> depthFirstSearch();

    public List<T> breadthFirstSearch(T source);

    public List<T> depthFirstSearch(T source);
}
