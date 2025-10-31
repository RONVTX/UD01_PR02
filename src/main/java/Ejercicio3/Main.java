package Ejercicio3;

import java.util.*;



public class Main {
    public static void main(String[] args) {
        // Construimos un grafo de ejemplo (intersecciones/paradas como nodos)
        Graph g = new Graph();

        // nodos
        String A = "A"; // p. ej. Plaza central
        String B = "B";
        String C = "C";
        String D = "D";
        String E = "E";
        String M1 = "Metro1";
        String M2 = "Metro2";

        // Añadir nodos (opcional, addEdge añade también)
        g.addNode(A); g.addNode(B); g.addNode(C); g.addNode(D); g.addNode(E); g.addNode(M1); g.addNode(M2);

        // Añadir aristas (from,to, tiempoBase(min), mode, peakMultiplier, directed)
        // Peatonales (puedes caminar entre A-B, B-C, etc.)
        g.addEdge(new Edge(A, B, 8, Mode.PEATON, 1.0, true));
        g.addEdge(new Edge(B, C, 6, Mode.PEATON, 1.0, true));
        g.addEdge(new Edge(C, D, 10, Mode.PEATON, 1.0, true));
        g.addEdge(new Edge(B, D, 18, Mode.PEATON, 1.0, true));

        // Bicicleta (más rápido en algunos tramos, direccional)
        g.addEdge(new Edge(A, C, 5, Mode.BICICLETA, 1.0, true));
        g.addEdge(new Edge(D, E, 4, Mode.BICICLETA, 1.0, true));

        // Bus (puede verse afectado por hora punta)
        g.addEdge(new Edge(A, M1, 4, Mode.BUS, 1.3, true));  // bus hacia estación Metro1
        g.addEdge(new Edge(M1, C, 7, Mode.BUS, 1.3, true));
        g.addEdge(new Edge(C, M2, 5, Mode.BUS, 1.3, true));

        // Metro (rápido, pero transferir a metro cuenta como transbordo)
        g.addEdge(new Edge(M1, M2, 6, Mode.METRO, 1.1, true));
        g.addEdge(new Edge(M2, E, 7, Mode.METRO, 1.1, true));

        // También aristas inversas donde corresponda (aquí dejamos dirigidas por ejemplo)
        // ...

        RoutePlanner planner = new RoutePlanner(g);

        // Ejemplo 1: ruta rápida A -> E sin restricciones, en hora valle
        {
            Set<Mode> avoid = new HashSet<>(); // no evitamos nada
            double transferPenalty = 3.0; // penalizamos 3 minutos por cada transbordo
            boolean isPeak = false;
            RouteResult r = planner.findRoute(A, E, avoid, transferPenalty, isPeak, 0);
            printResult("A -> E (sin restricciones, valle)", r);
        }

        // Ejemplo 2: evitar bicicleta
        {
            Set<Mode> avoid = EnumSet.of(Mode.BICICLETA);
            double transferPenalty = 3.0;
            boolean isPeak = false;
            RouteResult r = planner.findRoute(A, E, avoid, transferPenalty, isPeak, 0);
            printResult("A -> E (evitar bicicleta)", r);
        }

        // Ejemplo 3: minimizar transbordos (aumentando transferPenalty)
        {
            Set<Mode> avoid = new HashSet<>();
            double transferPenalty = 20.0; // fuerte penalización para minimizar transfers
            boolean isPeak = false;
            RouteResult r = planner.findRoute(A, E, avoid, transferPenalty, isPeak, 0);
            printResult("A -> E (minimizar transbordos)", r);
        }

        // Ejemplo 4: hora punta -> aplica peakMultiplier
        {
            Set<Mode> avoid = new HashSet<>();
            double transferPenalty = 3.0;
            boolean isPeak = true;
            RouteResult r = planner.findRoute(A, E, avoid, transferPenalty, isPeak, 0);
            printResult("A -> E (hora punta)", r);
        }

        // Ejemplo 5: limitar tiempo máximo (ej. buscar rutas < 20 min)
        {
            Set<Mode> avoid = new HashSet<>();
            double transferPenalty = 3.0;
            boolean isPeak = false;
            double maxTime = 20.0;
            RouteResult r = planner.findRoute(A, E, avoid, transferPenalty, isPeak, maxTime);
            printResult("A -> E (máx 20 min)", r);
        }
    }

    static void printResult(String title, RouteResult r) {
        System.out.println("\n=== " + title + " ===");
        if (r == null || r.isEmpty()) {
            System.out.println("No se encontró ruta viable.");
            return;
        }
        System.out.printf("Tiempo total: %.1f min | Transbordos: %d\n", r.totalTime, r.transfers);
        System.out.println("Camino:");
        for (Edge e : r.path) {
            System.out.printf("  %s --[%s, %.1f min]--> %s\n", e.from, e.mode, e.tiempo(false), e.to);
        }
    }
}
