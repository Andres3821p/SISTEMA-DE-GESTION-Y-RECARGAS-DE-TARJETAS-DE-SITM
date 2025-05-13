package paneles;

import modelos.Usuario;
import ventanas.VentanaEliminarTarjeta;
import ventanas.VentanaRecargarTarjeta;
import ventanas.VentanaRegistrarTarjeta;

import javax.swing.*;
import java.awt.*;

public class MenuUsuario extends JDialog {

    public MenuUsuario(Frame frame, Usuario usuario) { // CAMBIO: Frame en vez de JDialog
        super(frame, "Menú Usuario: " + usuario.getNombre(), true);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnRegistrarTarjeta = new JButton("Registrar Tarjeta");
        JButton btnRecargarTarjeta = new JButton("Recargar Tarjeta");
        JButton btnEliminarTarjeta = new JButton("Eliminar Tarjeta");
        JButton btnVolver = new JButton("Volver");

        add(btnRegistrarTarjeta);
        add(btnRecargarTarjeta);
        add(btnEliminarTarjeta);
        add(btnVolver);

        btnRegistrarTarjeta.addActionListener(e -> new VentanaRegistrarTarjeta(this, usuario));
        btnRecargarTarjeta.addActionListener(e -> new VentanaRecargarTarjeta(this, usuario));
        btnEliminarTarjeta.addActionListener(e -> new VentanaEliminarTarjeta(this, usuario));
        btnVolver.addActionListener(e -> dispose());

        setSize(400, 300);
        setLocationRelativeTo(frame);
        setVisible(true);
    }
}
