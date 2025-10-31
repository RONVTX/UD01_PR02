package Ejercicio2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolContactos agenda = new ArbolContactos();

        while (true) {
            System.out.println("\nLISTÍN TELEFÓNICO");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Eliminar contacto");
            System.out.println("3. Modificar contacto");
            System.out.println("4. Buscar por teléfono");
            System.out.println("5. Buscar por prefijo");
            System.out.println("6. Buscar por rango alfabético");
            System.out.println("7. Mostrar todos (ordenado)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String ape = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String tel = sc.nextLine();
                    System.out.print("Email (opcional): ");
                    String mail = sc.nextLine();
                    agenda.insertar(new Contacto(nom, ape, tel, mail));
                    break;

                case "2":
                    System.out.print("Teléfono a eliminar: ");
                    agenda.eliminar(sc.nextLine());
                    break;

                case "3":
                    System.out.print("Teléfono del contacto a modificar: ");
                    String tmod = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nnom = sc.nextLine();
                    System.out.print("Nuevo apellido: ");
                    String nape = sc.nextLine();
                    System.out.print("Nuevo email: ");
                    String nmail = sc.nextLine();
                    agenda.modificar(tmod, nnom, nape, nmail);
                    break;

                case "4":
                    System.out.print("Teléfono a buscar: ");
                    agenda.buscarPorTelefono(sc.nextLine());
                    break;

                case "5":
                    System.out.print("Prefijo a buscar: ");
                    agenda.buscarPorPrefijo(sc.nextLine());
                    break;

                case "6":
                    System.out.print("Inicio del rango: ");
                    String ini = sc.nextLine();
                    System.out.print("Fin del rango: ");
                    String fin = sc.nextLine();
                    agenda.buscarPorRango(ini, fin);
                    break;

                case "7":
                    agenda.mostrarInorden();
                    break;

                case "0":
                    System.out.println("Saliendo del Listín Telefónico");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
