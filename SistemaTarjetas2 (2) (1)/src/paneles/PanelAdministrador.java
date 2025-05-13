package paneles;

import gestor.GestorDatos;
import modelos.Tarjeta;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;

public class PanelAdministrador extends JDialog {

    public PanelAdministrador(JDialog parent) {
        super(parent, "Panel de Administrador", true);
        setLayout(new BorderLayout());

        DefaultListModel<Usuario> modelo = new DefaultListModel<>();
        for (Usuario u : GestorDatos.getUsuarios()) {
            modelo.addElement(u);
        }

        JList<Usuario> listaUsuarios = new JList<>(modelo);
        JScrollPane scroll = new JScrollPane(listaUsuarios);

        JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
        JButton btnVerTarjetas = new JButton("Ver Tarjetas");
        JButton btnVolver = new JButton("Volver");

        JPanel botones = new JPanel();
        botones.add(btnVerTarjetas);
        botones.add(btnEliminarUsuario);
        botones.add(btnVolver);

        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        btnVerTarjetas.addActionListener(e -> verTarjetasDeUsuario(listaUsuarios, modelo));
        btnEliminarUsuario.addActionListener(e -> eliminarUsuario(listaUsuarios, modelo));
        btnVolver.addActionListener(e -> dispose());

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void verTarjetasDeUsuario(JList<Usuario> listaUsuarios, DefaultListModel<Usuario> modelo) {
        Usuario seleccionado = listaUsuarios.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario.");
            return;
        }

        DefaultListModel<Tarjeta> modeloTarjetas = new DefaultListModel<>();
        for (Tarjeta t : seleccionado.getTarjetas()) {
            modeloTarjetas.addElement(t);
        }

        JList<Tarjeta> listaTarjetas = new JList<>(modeloTarjetas);
        JScrollPane scrollTarjetas = new JScrollPane(listaTarjetas);

        JButton btnEliminarTarjeta = new JButton("Eliminar Tarjeta");
        JButton btnCerrar = new JButton("Cerrar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminarTarjeta);
        panelBotones.add(btnCerrar);

        JDialog dialogo = new JDialog(this, "Tarjetas de " + seleccionado.getNombre(), true);
        dialogo.setLayout(new BorderLayout());
        dialogo.add(scrollTarjetas, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);

        btnEliminarTarjeta.addActionListener(ev -> eliminarTarjeta(seleccionado, listaTarjetas, modeloTarjetas));
        btnCerrar.addActionListener(ev -> dialogo.dispose());

        dialogo.setSize(300, 250);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    private void eliminarTarjeta(Usuario seleccionado, JList<Tarjeta> listaTarjetas, DefaultListModel<Tarjeta> modeloTarjetas) {
        Tarjeta tarjeta = listaTarjetas.getSelectedValue();
        if (tarjeta != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar esta tarjeta?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                seleccionado.getTarjetas().remove(tarjeta);
                modeloTarjetas.removeElement(tarjeta);
            }
        }
    }

    private void eliminarUsuario(JList<Usuario> listaUsuarios, DefaultListModel<Usuario> modelo) {
        Usuario seleccionado = listaUsuarios.getSelectedValue();
        if (seleccionado != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar usuario " + seleccionado.getNombre() + "?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                GestorDatos.eliminarUsuario(seleccionado.getId());
                modelo.removeElement(seleccionado);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para eliminar.");
        }
    }
}
