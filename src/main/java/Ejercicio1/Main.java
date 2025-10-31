package Ejercicio1;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolLetras arbol = new ArbolLetras();

        System.out.print("Ingrese una cadena de letras para construir el árbol: ");
        String entrada = sc.nextLine().trim();

        if (entrada.isEmpty()) {
            System.out.println("No se puede construir un árbol vacío. Finalizando...");
            return;
        }

        arbol.construirDesdeCadena(entrada);
        arbol.mostrarPorNiveles();

        while (true) {
            System.out.print("\nIngrese una letra a buscar (o '0' para salir): ");
            String busqueda = sc.nextLine().trim().toUpperCase();

            if (busqueda.equals("0")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            if (busqueda.isEmpty() || busqueda.length() != 1 || !Character.isLetter(busqueda.charAt(0))) {
                System.out.println("Entrada no válida. Ingrese una sola letra.");
                continue;
            }

            arbol.buscarLetra(busqueda.charAt(0));
        }

        sc.close();
    }
}
