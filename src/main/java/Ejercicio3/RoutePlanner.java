package Ejercicio3;

import java.util.*;

class RoutePlanner {
    private final Graph graph;

    public RoutePlanner(Graph graph) {
        this.graph = graph;
    }

    public RouteResult findRoute(String start, String goal, Set<Mode> avoidModes,
                                 double transferPenalty, boolean isPeak, double maxTimeMinutes) {

        if (!graph.allNodes().contains(start) || !graph.allNodes().contains(goal)) {
            return new RouteResult(Collections.emptyList(), Double.POSITIVE_INFINITY, 0);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();
        // mapa para mejores costes vistos por (node, prevMode) -> cost
        Map<String, Map<Mode, Double>> best = new HashMap<>();

        pq.add(new State(start, 0.0, null, 0, new ArrayList<>()));
        best.put(start, new HashMap<>()); // start with null prevMode tracked separately if needed

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            // Si ya hay un mejor camino conocido con mismo prevMode, saltar
            Map<Mode, Double> bestForNode = best.getOrDefault(cur.node, new HashMap<>());
            Double known = (cur.prevMode == null) ? bestForNode.get(null) : bestForNode.get(cur.prevMode);
            if (known != null && cur.cost > known + 1e-9) continue;

            // Llega a destino
            if (cur.node.equals(goal)) {
                return new RouteResult(cur.path, cur.cost, cur.transfers);
            }

            // Explorar vecinos
            for (Edge e : graph.neighbors(cur.node)) {
                // evitar modos prohibidos
                if (avoidModes != null && avoidModes.contains(e.mode)) continue;

                double t = e.tiempo(isPeak);
                boolean isTransfer = (cur.prevMode != null && cur.prevMode != e.mode && cur.path.size() > 0);
                double extra = isTransfer ? transferPenalty : 0.0;
                double nextCost = cur.cost + t + extra;

                if (maxTimeMinutes > 0 && nextCost > maxTimeMinutes) continue;

                int nextTransfers = cur.transfers + (isTransfer ? 1 : 0);

                // estado clave: node + prevMode = e.mode (porque llegamos al neighbor por e.mode)
                Mode nextPrevMode = e.mode;
                Map<Mode, Double> bestMap = best.computeIfAbsent(e.to, k -> new HashMap<>());
                Double previousBest = bestMap.get(nextPrevMode);
                if (previousBest == null || nextCost < previousBest - 1e-9) {
                    // construir nuevo path
                    List<Edge> newPath = new ArrayList<>(cur.path);
                    newPath.add(e);
                    bestMap.put(nextPrevMode, nextCost);
                    pq.add(new State(e.to, nextCost, nextPrevMode, nextTransfers, newPath));
                }
            }
        }

        return new RouteResult(Collections.emptyList(), Double.POSITIVE_INFINITY, 0);
    }
}
