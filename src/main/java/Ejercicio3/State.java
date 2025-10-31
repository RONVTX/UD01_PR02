package Ejercicio3;

import java.util.List;

class State implements Comparable<State> {
    final String node;
    final double cost;
    final Mode prevMode;      // modo por el que llegamos a 'node' (null si inicio)
    final int transfers;
    final List<Edge> path;    // camino (lista de aristas) desde origen a este nodo

    public State(String node, double cost, Mode prevMode, int transfers, List<Edge> path) {
        this.node = node;
        this.cost = cost;
        this.prevMode = prevMode;
        this.transfers = transfers;
        this.path = path;
    }

    @Override
    public int compareTo(State o) {
        return Double.compare(this.cost, o.cost);
    }
}