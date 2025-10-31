package Ejercicio3;

class Edge {
    final String from;
    final String to;
    final double tiempoBase;        // minutos
    final Mode mode;
    final double peakMultiplier;    // multiplicador si es hora punta (ej: 1.5)
    final boolean directed;

    public Edge(String from, String to, double tiempoBase, Mode mode, double peakMultiplier, boolean directed) {
        this.from = from;
        this.to = to;
        this.tiempoBase = tiempoBase;
        this.mode = mode;
        this.peakMultiplier = peakMultiplier;
        this.directed = directed;
    }

    public double tiempo(boolean isPeak) {
        return tiempoBase * (isPeak ? peakMultiplier : 1.0);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s (%.1f min, %s%s)", from, to, tiempoBase, mode,
                (peakMultiplier != 1.0 ? ", peak x" + peakMultiplier : ""));
    }
}
