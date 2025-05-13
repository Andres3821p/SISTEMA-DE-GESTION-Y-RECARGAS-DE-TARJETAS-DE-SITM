package paneles;

import modelos.Tarjeta;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;

public class PanelUsuario extends JDialog {

    public PanelUsuario(JDialog parent, Usuario usuario) {
        super(parent, "Panel de Usuario: " + usuario.getNombre(), true);
        setLayout(new GridLayout(5, 1, 5, 5));

        JButton btnRegistrarTarjeta = new JButton("Registrar Tarjeta");
        JButton btnRecargarTarjeta = new JButton("Recargar Tarjeta");
        JButton btnEliminarTarjeta = new JButton("Eliminar Tarjeta");
        JButton btnVolver = new JButton("Volver");

        add(btnRegistrarTarjeta);
        add(btnRecargarTarjeta);
        add(btnEliminarTarjeta);
        add(btnVolver);

        btnRegistrarTarjeta.addActionListener(e -> registrarTarjeta(usuario));
        btnRecargarTarjeta.addActionListener(e -> recargarTarjeta(usuario));
        btnEliminarTarjeta.addActionListener(e -> eliminarTarjeta(usuario));
        btnVolver.addActionListener(e -> dispose());

        setSize(350, 300);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Método para registrar tarjeta
    private void registrarTarjeta(Usuario usuario) {
        String ip = JOptionPane.showInputDialog(this, "Ingrese IP de la tarjeta (máximo 10 dígitos):");
        if (ip != null && ip.matches("\\d{1,10}")) {
            if (!ipYaRegistrada(usuario, ip)) {
                usuario.getTarjetas().add(new Tarjeta(ip));
                showMessage("Tarjeta registrada.");
            } else {
                showMessage("IP ya registrada.");
            }
        } else if (ip != null) {
            showMessage("IP inválida.");
        }
    }

    // Método para verificar si la IP ya está registrada
    private boolean ipYaRegistrada(Usuario usuario, String ip) {
        return usuario.getTarjetas().stream().anyMatch(t -> t.getIp().equals(ip));
    }

    // Método para recargar tarjeta
    private void recargarTarjeta(Usuario usuario) {
        if (usuario.getTarjetas().isEmpty()) {
            showMessage("No tienes tarjetas registradas.");
            return;
        }

        Tarjeta tarjeta = (Tarjeta) JOptionPane.showInputDialog(this, "Selecciona tarjeta:",
                "Recargar", JOptionPane.PLAIN_MESSAGE, null,
                usuario.getTarjetas().toArray(), null);

        if (tarjeta != null) {
            String montoStr = JOptionPane.showInputDialog(this, "Monto a recargar:");
            try {
                double monto = Double.parseDouble(montoStr);
                if (monto > 0) {
                    tarjeta.recargar(monto);
                    showMessage("Tarjeta recargada. Saldo: $" + tarjeta.getSaldo());
                } else {
                    showMessage("Monto inválido.");
                }
            } catch (NumberFormatException ex) {
                showMessage("Monto inválido.");
            }
        }
    }

    // Método para eliminar tarjeta
    private void eliminarTarjeta(Usuario usuario) {
        if (usuario.getTarjetas().isEmpty()) {
            showMessage("No hay tarjetas para eliminar.");
            return;
        }

        Tarjeta tarjeta = (Tarjeta) JOptionPane.showInputDialog(this, "Selecciona tarjeta para eliminar:",
                "Eliminar", JOptionPane.PLAIN_MESSAGE, null,
                usuario.getTarjetas().toArray(), null);

        if (tarjeta != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar esta tarjeta?\n" + tarjeta,
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                usuario.getTarjetas().remove(tarjeta);
                showMessage("Tarjeta eliminada.");
            }
        }
    }

    // Método general para mostrar mensajes
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
