package Ejercicio1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class ArbolLetras {
    Nodo raiz;

    // Construir árbol a partir de una cadena (nivel por nivel)
    public void construirDesdeCadena(String letras) {
        if (letras == null || letras.isEmpty()) {
            System.out.println("No se ingresaron letras.");
            return;
        }

        // Crear nodos para cada letra
        List<Nodo> nodos = new ArrayList<>();
        for (char c : letras.toCharArray()) {
            if (Character.isLetter(c)) {
                nodos.add(new Nodo(Character.toUpperCase(c)));
            }
        }

        if (nodos.isEmpty()) {
            System.out.println("No hay letras válidas para construir el árbol.");
            return;
        }

        raiz = nodos.get(0);

        // Construcción nivel por nivel (usando índices)
        for (int i = 0; i < nodos.size(); i++) {
            int izquierda = 2 * i + 1;
            int derecha = 2 * i + 2;

            if (izquierda < nodos.size()) nodos.get(i).izq = nodos.get(izquierda);
            if (derecha < nodos.size()) nodos.get(i).der = nodos.get(derecha);
        }

        System.out.println("\nÁrbol construido correctamente a partir de las letras: " + letras.toUpperCase());
    }

    // Mostrar el árbol por niveles (BFS)
    public void mostrarPorNiveles() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        System.out.println("\nÁrbol por niveles:");
        while (!cola.isEmpty()) {
            int nivel = cola.size();
            for (int i = 0; i < nivel; i++) {
                Nodo actual = cola.poll();
                System.out.print(actual.letra + " ");
                if (actual.izq != null) cola.add(actual.izq);
                if (actual.der != null) cola.add(actual.der);
            }
            System.out.println(); // salto de línea por nivel
        }
    }

    // Búsqueda en profundidad (DFS)
    public boolean buscarCamino(Nodo nodo, char objetivo, List<Character> camino, List<Character> direcciones) {
        if (nodo == null) return false;

        camino.add(nodo.letra);

        if (nodo.letra == objetivo) return true;

        // izquierda
        direcciones.add('L');
        if (buscarCamino(nodo.izq, objetivo, camino, direcciones)) return true;
        direcciones.remove(direcciones.size() - 1);

        // derecha
        direcciones.add('R');
        if (buscarCamino(nodo.der, objetivo, camino, direcciones)) return true;
        direcciones.remove(direcciones.size() - 1);

        camino.remove(camino.size() - 1);
        return false;
    }

    public void buscarLetra(char letra) {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        List<Character> camino = new ArrayList<>();
        List<Character> direcciones = new ArrayList<>();

        boolean encontrado = buscarCamino(raiz, letra, camino, direcciones);

        if (encontrado) {
            System.out.println("\nLetra '" + letra + "' encontrada.");
            System.out.println("➡ Camino de letras: " + camino);
            System.out.println("➡ Direcciones tomadas: " + direcciones);
        } else {
            System.out.println("\nLa letra '" + letra + "' no se encuentra en el árbol.");
        }
    }
}