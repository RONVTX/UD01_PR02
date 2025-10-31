package Ejercicio2;

class Contacto implements Comparable<Contacto> {
    String nombre;
    String apellidos;
    String telefono;
    String email;

    public Contacto(String nombre, String apellidos, String telefono, String email) {
        this.nombre = nombre.trim();
        this.apellidos = apellidos.trim();
        this.telefono = telefono.trim();
        this.email = email == null ? "" : email.trim();
    }

    // Criterio de orden: apellidos + nombre
    @Override
    public int compareTo(Contacto otro) {
        String clave1 = (apellidos + " " + nombre).toLowerCase();
        String clave2 = (otro.apellidos + " " + otro.nombre).toLowerCase();
        return clave1.compareTo(clave2);
    }

    @Override
    public String toString() {
        return apellidos + ", " + nombre + " | Tel: " + telefono +
                (email.isEmpty() ? "" : " | Email: " + email);
    }
}