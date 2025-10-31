package Ejercicio3;

import java.util.List;

class RouteResult {
    final List<Edge> path;
    final double totalTime;
    final int transfers;

    public RouteResult(List<Edge> path, double totalTime, int transfers) {
        this.path = path;
        this.totalTime = totalTime;
        this.transfers = transfers;
    }

    public boolean isEmpty() {
        return path == null || path.isEmpty();
    }
}