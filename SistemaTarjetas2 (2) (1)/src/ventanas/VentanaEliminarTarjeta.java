package ventanas;

import modelos.Tarjeta;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaEliminarTarjeta extends JDialog {

    public VentanaEliminarTarjeta(JDialog parent, Usuario usuario) {
        super(parent, "Eliminar Tarjeta", true);
        setLayout(new BorderLayout());

        // Modelo para las tarjetas
        DefaultListModel<Tarjeta> modelo = new DefaultListModel<>();
        for (Tarjeta t : usuario.getTarjetas()) {
            modelo.addElement(t);
        }

        // Lista para mostrar las tarjetas
        JList<Tarjeta> lista = new JList<>(modelo);
        JScrollPane scroll = new JScrollPane(lista);

        // Botones con tamaño fijo
        JButton btnEliminar = new JButton("Eliminar Seleccionada");
        JButton btnCancelar = new JButton("Cancelar");

        Dimension botonSize = new Dimension(150, 40);
        btnEliminar.setPreferredSize(botonSize);
        btnCancelar.setPreferredSize(botonSize);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 10));
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);

        // Agregar componentes a la ventana
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón Eliminar
        btnEliminar.addActionListener(e -> {
            Tarjeta seleccionada = lista.getSelectedValue();
            if (seleccionada != null) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de eliminar la tarjeta: " + seleccionada.getIp() + "?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    usuario.getTarjetas().remove(seleccionada);
                    modelo.removeElement(seleccionada);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor selecciona una tarjeta para eliminar.");
            }
        });

        // Acción del botón Cancelar
        btnCancelar.addActionListener(e -> dispose());

        // Configurar la ventana
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setVisible(true);

        // Establecer un tamaño mínimo para la ventana
        setMinimumSize(new Dimension(400, 250));
    }

    // Constructor adicional no utilizado
    public VentanaEliminarTarjeta(JFrame frame, Usuario usuario) {
    }
}
