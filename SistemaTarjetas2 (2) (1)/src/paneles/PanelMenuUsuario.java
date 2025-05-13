package paneles;

import app.App;
import modelos.Usuario;
import ventanas.VentanaEliminarTarjeta;
import ventanas.VentanaRecargarTarjeta;
import ventanas.VentanaRegistrarTarjeta;

import javax.swing.*;
import java.awt.*;

public class PanelMenuUsuario extends JPanel {
    public PanelMenuUsuario(JFrame dialog, Usuario usuario) {  // Cambié JFrame a JDialog
        setLayout(new GridLayout(6, 1));

        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuario.getNombre());
        JButton btnRegistrarTarjeta = new JButton("Registrar Tarjeta");
        JButton btnRecargarTarjeta = new JButton("Recargar Tarjeta");
        JButton btnEliminarTarjeta = new JButton("Eliminar Tarjeta");
        JButton btnVolver = new JButton("Volver");

        add(lblBienvenida);
        add(btnRegistrarTarjeta);
        add(btnRecargarTarjeta);
        add(btnEliminarTarjeta);
        add(btnVolver);

        btnRegistrarTarjeta.addActionListener(e -> {
            new VentanaRegistrarTarjeta(dialog, usuario); // Usamos dialog en lugar de frame
        });

        btnRecargarTarjeta.addActionListener(e -> {
            new VentanaRecargarTarjeta(dialog, usuario); // Usamos dialog en lugar de frame
        });

        btnEliminarTarjeta.addActionListener(e -> {
            new VentanaEliminarTarjeta(dialog, usuario); // Usamos dialog en lugar de frame
        });

        btnVolver.addActionListener(e -> {
            dialog.setContentPane(new App().panelPrincipal);
            dialog.revalidate();
        });
    }
}

