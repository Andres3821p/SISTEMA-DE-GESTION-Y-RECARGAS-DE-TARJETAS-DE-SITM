package ventanas;

import componentes.BotonTransparente;
import gestor.GestorDatos;
import paneles.PanelConFondo;
import redes.PanelRedes;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal {
    private JPanel panelPrincipal = new PanelConFondo();

    private JButton btnRegistrarUsuario = new BotonTransparente("Registrarse como Usuario");
    private JButton btnRegistrarAdmin = new BotonTransparente("Registrarse como Administrador");
    private JButton btnIngresarUsuario = new BotonTransparente("Ingresar como Usuario");
    private JButton btnIngresarAdmin = new BotonTransparente("Ingresar como Administrador");
    private JButton btnGuardarSalir = new BotonTransparente("Guardar y Salir");

    public VentanaPrincipal(JFrame parentFrame) {
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Agregar espacio flexible arriba
        panelPrincipal.add(Box.createVerticalGlue());

        Dimension botonSize = new Dimension(300, 50); // Aumenta tamaño visual
        JButton[] botones = new JButton[]{
                btnRegistrarUsuario,
                btnRegistrarAdmin,
                btnIngresarUsuario,
                btnIngresarAdmin,
                btnGuardarSalir
        };

        for (JButton btn : botones) {
            btn.setPreferredSize(botonSize);
            btn.setMaximumSize(botonSize);
            btn.setMinimumSize(botonSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setMargin(new Insets(0, 20, 0, 0));
            panelPrincipal.add(btn);
            panelPrincipal.add(Box.createVerticalStrut(15)); // Espacio entre botones
        }

        // Agregar espacio flexible abajo
        panelPrincipal.add(Box.createVerticalStrut(20));

        panelPrincipal.add(new PanelRedes());

        panelPrincipal.add(Box.createVerticalGlue());

        // Acciones
        btnRegistrarUsuario.addActionListener(e -> new VentanaRegistroUsuario(parentFrame));
        btnRegistrarAdmin.addActionListener(e -> new VentanaRegistroAdministrador(parentFrame));
        btnIngresarUsuario.addActionListener(e -> new VentanaIngresoUsuario(parentFrame));
        btnIngresarAdmin.addActionListener(e -> new VentanaIngresoAdministrador(parentFrame));
        btnGuardarSalir.addActionListener(e -> {
            GestorDatos.guardarTodo();
            JOptionPane.showMessageDialog(parentFrame, "Datos guardados correctamente.");
            parentFrame.dispose();
            System.exit(0);
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
