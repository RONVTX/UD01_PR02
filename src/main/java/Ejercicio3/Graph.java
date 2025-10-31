package Ejercicio3;

import java.util.*;

class Graph {
    private final Map<String, List<Edge>> adj = new HashMap<>();

    public void addNode(String id) {
        adj.computeIfAbsent(id, k -> new ArrayList<>());
    }

    public void addEdge(Edge e) {
        addNode(e.from);
        addNode(e.to);
        adj.get(e.from).add(e);
        if (!e.directed) {
            // añadir arista inversa si no es dirigida
            Edge rev = new Edge(e.to, e.from, e.tiempoBase, e.mode, e.peakMultiplier, false);
            adj.get(e.to).add(rev);
        }
    }

    public List<Edge> neighbors(String node) {
        return adj.getOrDefault(node, Collections.emptyList());
    }

    public Set<String> allNodes() {
        return adj.keySet();
    }
}
