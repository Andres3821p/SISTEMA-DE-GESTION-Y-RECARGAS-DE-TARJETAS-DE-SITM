package ventanas;

import modelos.Tarjeta;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistrarTarjeta extends JDialog {

    // Constructor con JDialog como padre
    public VentanaRegistrarTarjeta(JDialog parent, Usuario usuario) {
        super(parent, "Registrar Tarjeta", true);
        setLayout(new GridLayout(3, 2, 5, 5));

        JLabel lblIp = new JLabel("IP de la tarjeta:");
        JTextField txtIp = new JTextField();

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        // Establecer tamaño fijo para los botones
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));

        add(lblIp); add(txtIp);
        add(btnAceptar); add(btnCancelar);

        btnAceptar.addActionListener(e -> {
            String ip = txtIp.getText().trim();
            // Validación del IP (máximo 10 dígitos numéricos)
            if (!ip.matches("\\d{1,10}")) {
                JOptionPane.showMessageDialog(this, "El IP debe tener solo números (máximo 10 dígitos).");
                return;
            }

            usuario.getTarjetas().add(new Tarjeta(ip));  // Agregar la tarjeta al usuario
            JOptionPane.showMessageDialog(this, "Tarjeta registrada correctamente.");
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Constructor con JFrame como padre (opcional)
    public VentanaRegistrarTarjeta(JFrame frame, Usuario usuario) {
        super(frame, "Registrar Tarjeta", true);
        setLayout(new GridLayout(3, 2, 5, 5));

        JLabel lblIp = new JLabel("IP de la tarjeta:");
        JTextField txtIp = new JTextField();

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        // Establecer tamaño fijo para los botones
        btnAceptar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));

        add(lblIp); add(txtIp);
        add(btnAceptar); add(btnCancelar);

        btnAceptar.addActionListener(e -> {
            String ip = txtIp.getText().trim();
            // Validación del IP (máximo 10 dígitos numéricos)
            if (!ip.matches("\\d{1,10}")) {
                JOptionPane.showMessageDialog(this, "El IP debe tener solo números (máximo 10 dígitos).");
                return;
            }

            usuario.getTarjetas().add(new Tarjeta(ip));  // Agregar la tarjeta al usuario
            JOptionPane.showMessageDialog(this, "Tarjeta registrada correctamente.");
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        setSize(300, 150);
        setLocationRelativeTo(frame);
        setVisible(true);
    }
}
