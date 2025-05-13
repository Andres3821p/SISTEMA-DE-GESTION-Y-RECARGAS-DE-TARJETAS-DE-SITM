package paneles;

import gestor.GestorDatos;
import modelos.Administrador;
import modelos.Tarjeta;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuAdministrador extends JDialog {

    public MenuAdministrador(JFrame frame, Administrador admin) {
        super(frame, "Panel de Administración: " + admin.getNombre(), true);
        setLayout(new BorderLayout());

        DefaultListModel<Usuario> modeloUsuarios = new DefaultListModel<>();
        ArrayList<Usuario> usuarios = GestorDatos.getUsuarios();
        for (Usuario u : usuarios) {
            modeloUsuarios.addElement(u);
        }

        JList<Usuario> listaUsuarios = new JList<>(modeloUsuarios);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaUsuarios);

        JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
        JButton btnVerTarjetas = new JButton("Ver Tarjetas del Usuario");
        JButton btnVolver = new JButton("Volver");

        // Usar FlowLayout para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnVerTarjetas);
        panelBotones.add(btnEliminarUsuario);
        panelBotones.add(btnVolver);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Mostrar los detalles del usuario seleccionado
        listaUsuarios.addListSelectionListener(e -> {
            Usuario seleccionado = listaUsuarios.getSelectedValue();
            if (seleccionado != null) {
                JPanel panelDetalles = new JPanel();
                panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));

                JLabel lblDetalles = new JLabel("Detalles del Usuario:");
                JLabel lblNombre = new JLabel("Nombre: " + seleccionado.getNombre());
                JLabel lblId = new JLabel("ID: " + seleccionado.getId());
                JLabel lblTelefono = new JLabel("Teléfono: " + seleccionado.getTelefono());

                panelDetalles.add(lblDetalles);
                panelDetalles.add(lblNombre);
                panelDetalles.add(lblId);
                panelDetalles.add(lblTelefono);

                add(panelDetalles, BorderLayout.NORTH);
                revalidate();
            }
        });

        btnVerTarjetas.addActionListener(e -> {
            Usuario seleccionado = listaUsuarios.getSelectedValue();
            if (seleccionado != null) {
                mostrarTarjetasDeUsuario(this, seleccionado);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario.");
            }
        });

        btnEliminarUsuario.addActionListener(e -> {
            Usuario seleccionado = listaUsuarios.getSelectedValue();
            if (seleccionado != null) {
                int opcion = JOptionPane.showConfirmDialog(this,
                        "¿Eliminar al usuario: " + seleccionado + "?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    GestorDatos.eliminarUsuario(seleccionado.getId());
                    modeloUsuarios.removeElement(seleccionado);
                }
            }
        });

        btnVolver.addActionListener(e -> dispose());

        setSize(450, 300);
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    private void mostrarTarjetasDeUsuario(JDialog parent, Usuario usuario) {
        JDialog dialog = new JDialog(parent, "Tarjetas de " + usuario.getNombre(), true);
        dialog.setLayout(new BorderLayout());

        DefaultListModel<Tarjeta> modeloTarjetas = new DefaultListModel<>();
        for (Tarjeta t : usuario.getTarjetas()) {
            modeloTarjetas.addElement(t);
        }

        JList<Tarjeta> listaTarjetas = new JList<>(modeloTarjetas);
        JScrollPane scroll = new JScrollPane(listaTarjetas);

        JButton btnEliminarTarjeta = new JButton("Eliminar Tarjeta");
        JButton btnCerrar = new JButton("Cerrar");

        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        panelBotones.add(btnEliminarTarjeta);
        panelBotones.add(btnCerrar);

        dialog.add(scroll, BorderLayout.CENTER);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        btnEliminarTarjeta.addActionListener(e -> {
            Tarjeta seleccionada = listaTarjetas.getSelectedValue();
            if (seleccionada != null) {
                int opcion = JOptionPane.showConfirmDialog(dialog,
                        "¿Eliminar la tarjeta: " + seleccionada.getIp() + "?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    usuario.getTarjetas().remove(seleccionada);
                    modeloTarjetas.removeElement(seleccionada);
                    JOptionPane.showMessageDialog(dialog, "Tarjeta eliminada con éxito.");
                }
            }
        });

        btnCerrar.addActionListener(e -> dialog.dispose());

        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
