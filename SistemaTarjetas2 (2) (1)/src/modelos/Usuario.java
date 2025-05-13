package modelos;

import java.util.ArrayList;

public class Usuario extends Persona {
    private ArrayList<Tarjeta> tarjetas;

    public Usuario(String nombre, String apellido, String id, String telefono) {
        super(nombre, apellido, id, telefono);
        this.tarjetas = new ArrayList<>();
    }

    // Obtener las tarjetas
    public ArrayList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    // Agregar una tarjeta con validación (si es necesario)
    public boolean agregarTarjeta(Tarjeta tarjeta) {
        if (tarjeta != null && !tarjetas.contains(tarjeta)) {
            tarjetas.add(tarjeta);
            return true;
        }
        return false;
    }

    // Eliminar una tarjeta
    public boolean eliminarTarjeta(Tarjeta tarjeta) {
        return tarjetas.remove(tarjeta);
    }

    // Buscar una tarjeta por IP (o alguna otra propiedad)
    public Tarjeta buscarTarjeta(String ip) {
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getIp().equals(ip)) {
                return tarjeta;
            }
        }
        return null; // Retorna null si no encuentra la tarjeta
    }

    // Sobrescribir el método toString para una presentación más amigable
    @Override
    public String toString() {
        return "Usuario: " + getNombre() + " " + getApellido() + ", ID: " + getId() + ", Teléfono: " + getTelefono();
    }
}
