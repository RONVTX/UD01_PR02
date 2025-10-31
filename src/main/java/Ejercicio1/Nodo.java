package Ejercicio1;

class Nodo {
    char letra;
    Nodo izq, der;

    public Nodo(char letra) {
        this.letra = letra;
        izq = der = null;
    }
}