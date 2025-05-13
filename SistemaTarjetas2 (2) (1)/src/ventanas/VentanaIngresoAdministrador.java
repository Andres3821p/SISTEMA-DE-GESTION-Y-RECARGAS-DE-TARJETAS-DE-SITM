package ventanas;

import gestor.GestorDatos;
import modelos.Administrador;
import paneles.PanelAdministrador;

import javax.swing.*;
import java.awt.*;

public class VentanaIngresoAdministrador extends JDialog {

    public VentanaIngresoAdministrador(JFrame parent) {
        super(parent, "Ingreso de Administrador", true);
        setLayout(new GridLayout(3, 2, 5, 5));

        JTextField txtId = new JTextField();
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        // Ajustar tamaño fijo de los botones
        Dimension botonSize = new Dimension(100, 40);
        btnIngresar.setPreferredSize(botonSize);
        btnCancelar.setPreferredSize(botonSize);

        add(new JLabel("ID de Administrador:"));
        add(txtId);
        add(btnIngresar);
        add(btnCancelar);

        // Acción para el botón "Ingresar"
        btnIngresar.addActionListener(e -> {
            String id = txtId.getText().trim();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa un ID.");
                return;
            }

            Administrador admin = GestorDatos.buscarAdministradorPorId(id);

            if (admin != null) {
                // Si el administrador existe, abrir el panel de administrador
                new PanelAdministrador(this);
                dispose();  // Cerrar la ventana de ingreso
            } else {
                JOptionPane.showMessageDialog(this, "Administrador no encontrado.");
            }
        });

        // Acción para el botón "Cancelar"
        btnCancelar.addActionListener(e -> dispose());

        // Configurar la ventana
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);

        // Establecer un tamaño mínimo para la ventana
        setMinimumSize(new Dimension(300, 150));
    }
}
