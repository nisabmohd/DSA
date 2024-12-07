package Graphs;

import definitions.Graphs;

import java.util.*;

public class UniDirectionalGraph<T> implements Graphs<T> {

    private final Map<T, Set<T>> adjMap;

    public UniDirectionalGraph() {
        adjMap = new HashMap<>();
    }

    @Override
    public void addEdge(T source, T destination) {
        if (source == null) return;
        if (!adjMap.containsKey(source)) adjMap.put(source, new HashSet<>());
        if (destination == null) return;
        if (!adjMap.containsKey(destination)) adjMap.put(destination, new HashSet<>());
        adjMap.get(source).add(destination);
    }

    @Override
    public void removeEdge(T source, T destination) {
        if (!adjMap.containsKey(source)) return;
        if (destination == null) {
            this.adjMap.forEach((k, v) -> {
                if (k.equals(source)) {
                    v.remove(source);
                }
            });
            this.adjMap.remove(source);
            return;
        }
        adjMap.get(source).remove(destination);
    }

    @Override
    public List<List<T>> breadthFirstSearch() {
        List<List<T>> ans = new LinkedList<>();
        Set<T> vis = new HashSet<>();
        adjMap.forEach((k, v) -> {
            if (!vis.contains(k))
                ans.add(breadthFirstSearch(k, vis));
        });
        return ans;
    }

    @Override
    public List<T> breadthFirstSearch(T source) {
        return breadthFirstSearch(source, new HashSet<>());
    }

    private List<T> breadthFirstSearch(T source, Set<T> vis) {
        List<T> ans = new LinkedList<>();
        var queueLookup = new HashSet<>();
        if (!adjMap.containsKey(source)) return ans;
        Queue<T> q = new LinkedList<>();
        q.add(source);
        queueLookup.add(source);
        while (!q.isEmpty()) {
            var node = q.remove();
            vis.add(node);
            adjMap.get(node).forEach(adjNode -> {
                if (!vis.contains(adjNode) && !queueLookup.contains(adjNode)) {
                    q.add(adjNode);
                    queueLookup.add(adjNode);
                }
            });
            ans.add(node);
        }
        return ans;
    }

    @Override
    public List<List<T>> depthFirstSearch() {
        Set<T> vis = new HashSet<>();
        List<List<T>> ans = new LinkedList<>();
        adjMap.forEach((k, v) -> {
            if (!vis.contains(k)) ans.add(depthFirstSearch(k, vis));
        });
        return ans;
    }

    @Override
    public List<T> depthFirstSearch(T source) {
        return depthFirstSearch(source, new HashSet<>());
    }

    private List<T> depthFirstSearch(T source, Set<T> vis) {
        List<T> ans = new LinkedList<>();
        if (!adjMap.containsKey(source)) return ans;
        vis.add(source);
        ans.add(source);
        adjMap.get(source).forEach(adjNode -> {
            if (!vis.contains(adjNode)) {
                ans.addAll(depthFirstSearch(adjNode, vis));
            }
        });
        return ans;
    }

    @Override
    public String toString() {
        return this.adjMap.toString();
    }

}
