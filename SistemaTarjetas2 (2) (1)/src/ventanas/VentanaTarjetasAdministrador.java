package ventanas;

import modelos.Tarjeta;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaTarjetasAdministrador extends JDialog {

    public VentanaTarjetasAdministrador(JFrame frame, Usuario usuario) {
        super(frame, "Tarjetas de " + usuario.getNombre(), true);

        // Panel principal con fondo
        JPanel panelFondo = new JPanel() {
            ImageIcon fondo = new ImageIcon("fondo.jpg"); // Asegúrate de tener esta imagen en tu proyecto
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(new BorderLayout());

        DefaultListModel<Tarjeta> modelo = new DefaultListModel<>();
        for (Tarjeta t : usuario.getTarjetas()) {
            modelo.addElement(t);
        }

        JList<Tarjeta> lista = new JList<>(modelo);
        JScrollPane scroll = new JScrollPane(lista);

        JButton btnEliminar = new JButton("Eliminar Tarjeta");
        JButton btnCerrar = new JButton("Cerrar");

        // Botones en un panel con FlowLayout para que no se expandan
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelBotones.setOpaque(false); // Para que se vea el fondo
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        panelFondo.add(scroll, BorderLayout.CENTER);
        panelFondo.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(panelFondo);

        btnEliminar.addActionListener(e -> {
            Tarjeta seleccionada = lista.getSelectedValue();
            if (seleccionada != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar esta tarjeta?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    usuario.getTarjetas().remove(seleccionada);
                    modelo.removeElement(seleccionada);
                }
            }
        });

        btnCerrar.addActionListener(e -> dispose());

        setSize(400, 250);
        setLocationRelativeTo(frame);
        setVisible(true);
    }
}