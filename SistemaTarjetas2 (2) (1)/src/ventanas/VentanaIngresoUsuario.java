package ventanas;

import gestor.GestorDatos;
import modelos.Usuario;
import paneles.PanelUsuario;

import javax.swing.*;
import java.awt.*;

public class VentanaIngresoUsuario extends JDialog {

    public VentanaIngresoUsuario(JFrame parent) {
        super(parent, "Ingreso de Usuario", true);
        setLayout(new GridLayout(3, 2, 5, 5));

        JTextField txtId = new JTextField();
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        // Ajustar tamaño de los botones para evitar redimensionamiento
        Dimension botonSize = new Dimension(100, 40);
        btnIngresar.setPreferredSize(botonSize);
        btnCancelar.setPreferredSize(botonSize);

        add(new JLabel("ID de Usuario:"));
        add(txtId);
        add(btnIngresar);
        add(btnCancelar);

        // Acción del botón Ingresar
        btnIngresar.addActionListener(e -> {
            String id = txtId.getText().trim();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa un ID.");
                return;
            }

            Usuario usuario = GestorDatos.buscarUsuarioPorId(id);

            if (usuario != null) {
                // Si el usuario es encontrado, abrir el panel de usuario
                new PanelUsuario(this, usuario);
                dispose();  // Cerrar la ventana de ingreso
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
            }
        });

        // Acción del botón Cancelar
        btnCancelar.addActionListener(e -> dispose());

        // Configuración de la ventana
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);

        // Establecer un tamaño mínimo para la ventana
        setMinimumSize(new Dimension(300, 150));
    }
}
