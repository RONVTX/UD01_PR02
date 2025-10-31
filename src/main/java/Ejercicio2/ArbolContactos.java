package Ejercicio2;

import java.util.HashMap;
import java.util.Map;

class ArbolContactos {
    private Nodo raiz;
    private Map<String, Contacto> indiceTelefono = new HashMap<>();

    // INSERTAR contacto
    public void insertar(Contacto contacto) {
        if (indiceTelefono.containsKey(contacto.telefono)) {
            System.out.println("Ya existe un contacto con ese teléfono.");
            return;
        }
        raiz = insertarRec(raiz, contacto);
        indiceTelefono.put(contacto.telefono, contacto);
        System.out.println("Contacto agregado correctamente.");
    }

    private Nodo insertarRec(Nodo actual, Contacto c) {
        if (actual == null) return new Nodo(c);

        if (c.compareTo(actual.contacto) < 0)
            actual.izq = insertarRec(actual.izq, c);
        else if (c.compareTo(actual.contacto) > 0)
            actual.der = insertarRec(actual.der, c);
        else
            System.out.println("Contacto duplicado (nombre/apellido).");

        return actual;
    }

    // MOSTRAR contactos (inorden)
    public void mostrarInorden() {
        System.out.println("\nListado de contactos (ordenado):");
        if (raiz == null) {
            System.out.println("No hay contactos registrados.");
            return;
        }
        mostrarInordenRec(raiz);
    }

    private void mostrarInordenRec(Nodo nodo) {
        if (nodo != null) {
            mostrarInordenRec(nodo.izq);
            System.out.println(" - " + nodo.contacto);
            mostrarInordenRec(nodo.der);
        }
    }

    // BUSCAR por teléfono
    public void buscarPorTelefono(String telefono) {
        Contacto c = indiceTelefono.get(telefono);
        if (c != null) {
            System.out.println("\nContacto encontrado: " + c);
        } else {
            System.out.println("\nNo existe contacto con ese teléfono.");
        }
    }

    // BUSCAR por prefijo
    public void buscarPorPrefijo(String prefijo) {
        System.out.println("\nContactos que comienzan con '" + prefijo + "':");
        buscarPrefijoRec(raiz, prefijo.toLowerCase());
    }

    private void buscarPrefijoRec(Nodo nodo, String prefijo) {
        if (nodo == null) return;
        buscarPrefijoRec(nodo.izq, prefijo);
        String clave = (nodo.contacto.apellidos + " " + nodo.contacto.nombre).toLowerCase();
        if (clave.startsWith(prefijo)) System.out.println(" - " + nodo.contacto);
        buscarPrefijoRec(nodo.der, prefijo);
    }

    // BUSCAR por rango alfabético
    public void buscarPorRango(String inicio, String fin) {
        System.out.println("\nContactos entre '" + inicio + "' y '" + fin + "':");
        buscarRangoRec(raiz, inicio.toLowerCase(), fin.toLowerCase());
    }

    private void buscarRangoRec(Nodo nodo, String ini, String fin) {
        if (nodo == null) return;
        String clave = (nodo.contacto.apellidos + " " + nodo.contacto.nombre).toLowerCase();

        if (clave.compareTo(ini) > 0)
            buscarRangoRec(nodo.izq, ini, fin);

        if (clave.compareTo(ini) >= 0 && clave.compareTo(fin) <= 0)
            System.out.println(" - " + nodo.contacto);

        if (clave.compareTo(fin) < 0)
            buscarRangoRec(nodo.der, ini, fin);
    }

    // ELIMINAR contacto
    public void eliminar(String telefono) {
        if (!indiceTelefono.containsKey(telefono)) {
            System.out.println("No existe un contacto con ese teléfono.");
            return;
        }
        Contacto c = indiceTelefono.get(telefono);
        raiz = eliminarRec(raiz, c);
        indiceTelefono.remove(telefono);
        System.out.println("Contacto eliminado correctamente.");
    }

    private Nodo eliminarRec(Nodo nodo, Contacto c) {
        if (nodo == null) return null;

        int cmp = c.compareTo(nodo.contacto);
        if (cmp < 0)
            nodo.izq = eliminarRec(nodo.izq, c);
        else if (cmp > 0)
            nodo.der = eliminarRec(nodo.der, c);
        else {
            if (nodo.izq == null) return nodo.der;
            if (nodo.der == null) return nodo.izq;

            // reemplazo por el sucesor inorden
            nodo.contacto = minimo(nodo.der).contacto;
            nodo.der = eliminarRec(nodo.der, nodo.contacto);
        }
        return nodo;
    }

    private Nodo minimo(Nodo nodo) {
        while (nodo.izq != null) nodo = nodo.izq;
        return nodo;
    }

    // MODIFICAR contacto
    public void modificar(String telefono, String nuevoNombre, String nuevoApellido, String nuevoEmail) {
        Contacto c = indiceTelefono.get(telefono);
        if (c == null) {
            System.out.println("No existe un contacto con ese teléfono.");
            return;
        }
        eliminar(telefono); // eliminamos el nodo viejo
        insertar(new Contacto(nuevoNombre, nuevoApellido, telefono, nuevoEmail));
        System.out.println("Contacto modificado correctamente.");
    }
}
