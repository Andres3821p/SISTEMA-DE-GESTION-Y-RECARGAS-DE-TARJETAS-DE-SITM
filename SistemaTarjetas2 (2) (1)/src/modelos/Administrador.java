package modelos;

public class Administrador extends Persona {

    public Administrador(String nombre, String apellido, String id, String telefono) {
        super(nombre, apellido, id, telefono);
    }

    // Método adicional para mostrar un reporte
    public void generarReporte() {
        System.out.println("Generando reporte de administración...");
    }

    // Sobreescribir el método toString() para mostrar un mensaje personalizado
    @Override
    public String toString() {
        return "Administrador: " + getNombre() + " " + getApellido() + " (ID: " + getId() + ")";
    }

    // Un ejemplo de método que puede diferenciar a un administrador de otros tipos de personas
    public void gestionarUsuarios() {
        System.out.println("Gestionando usuarios...");
    }
}
