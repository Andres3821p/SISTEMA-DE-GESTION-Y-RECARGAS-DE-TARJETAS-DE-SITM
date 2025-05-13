package paneles;

import app.App;
import gestor.GestorDatos;
import modelos.Administrador;
import modelos.Usuario;
import ventanas.VentanaTarjetasAdministrador;

import javax.swing.*;
import java.awt.*;

public class PanelMenuAdministrador extends JPanel {
    public PanelMenuAdministrador(JFrame frame, Administrador admin) {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Usuarios registrados:");
        DefaultListModel<Usuario> modeloLista = new DefaultListModel<>();
        JList<Usuario> listaUsuarios = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaUsuarios);

        // Panel para botones con FlowLayout
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Alineación de los botones
        JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
        JButton btnVerTarjetas = new JButton("Ver Tarjetas");
        JButton btnEliminarTarjeta = new JButton("Eliminar Tarjeta");
        JButton btnVolver = new JButton("Volver");

        botones.add(btnEliminarUsuario);
        botones.add(btnVerTarjetas);
        botones.add(btnEliminarTarjeta);
        botones.add(btnVolver);

        add(lblTitulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        // Llenar la lista de usuarios
        for (Usuario u : GestorDatos.getUsuarios()) {
            modeloLista.addElement(u);
        }

        // Eliminar usuario
        btnEliminarUsuario.addActionListener(e -> {
            Usuario seleccionado = listaUsuarios.getSelectedValue();
            if (seleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Eliminar usuario " + seleccionado.getNombre() + "?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        GestorDatos.eliminarUsuario(seleccionado.getId());
                        modeloLista.removeElement(seleccionado);
                        JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.");
            }
        });

        // Ver tarjetas de un usuario
        btnVerTarjetas.addActionListener(e -> {
            Usuario seleccionado = listaUsuarios.getSelectedValue();
            if (seleccionado != null) {
                new VentanaTarjetasAdministrador(frame, seleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario para ver sus tarjetas.");
            }
        });

        btnEliminarTarjeta.addActionListener(e -> {
            Usuario seleccionado = listaUsuarios.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una tarjeta.");
                return;
            }

            // Aquí iría la lógica real para eliminar una tarjeta del usuario seleccionado.
            // Esto es un ejemplo de placeholder:
            JOptionPane.showMessageDialog(this, "Funcionalidad de eliminar tarjeta no implementada aún.");
        });

        // Volver al panel principal
        btnVolver.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "¿Seguro que quieres salir?", "Confirmar Volver", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.setContentPane(new App().panelPrincipal);
                frame.revalidate();
            }
        });
    }
}
