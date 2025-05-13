package modelos;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private String id;
    private String telefono;

    public Persona(String nombre, String apellido, String id, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;
    }

    // Método para validar el teléfono (ejemplo con solo números)
    public boolean validarTelefono() {
        return telefono.matches("\\d+"); // Puede personalizarse según el formato que necesites
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - ID: " + id;
    }

    // Sobrescribir equals y hashCode para comparar por ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona persona = (Persona) obj;
        return id.equals(persona.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
