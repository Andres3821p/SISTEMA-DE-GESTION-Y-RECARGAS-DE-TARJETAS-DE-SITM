package ventanas;

import modelos.Tarjeta;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaRecargarTarjeta extends JDialog {

    public VentanaRecargarTarjeta(JDialog parent, Usuario usuario) {
        super(parent, "Recargar Tarjeta", true);
        setLayout(new BorderLayout());

        // Crear modelo y lista de tarjetas
        DefaultListModel<Tarjeta> modelo = new DefaultListModel<>();
        for (Tarjeta t : usuario.getTarjetas()) {
            modelo.addElement(t);
        }

        JList<Tarjeta> lista = new JList<>(modelo);
        JScrollPane scroll = new JScrollPane(lista);

        JTextField txtMonto = new JTextField();
        JButton btnRecargar = new JButton("Recargar");
        JButton btnVolver = new JButton("Volver");

        // Establecer tamaño fijo para los botones
        btnRecargar.setPreferredSize(new Dimension(100, 40));
        btnVolver.setPreferredSize(new Dimension(100, 40));

        // Panel inferior para el monto y los botones
        JPanel panelInferior = new JPanel(new GridLayout(2, 2, 5, 5));
        panelInferior.add(new JLabel("Monto a recargar:"));
        panelInferior.add(txtMonto);
        panelInferior.add(btnRecargar);
        panelInferior.add(btnVolver);

        // Agregar los componentes a la ventana
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Acción de recargar
        btnRecargar.addActionListener(e -> {
            Tarjeta seleccionada = lista.getSelectedValue();
            if (seleccionada == null) {
                JOptionPane.showMessageDialog(this, "Selecciona una tarjeta.");
                return;
            }

            try {
                double monto = Double.parseDouble(txtMonto.getText().trim());
                if (monto <= 0) throw new NumberFormatException();
                seleccionada.recargar(monto);
                JOptionPane.showMessageDialog(this, "Tarjeta recargada. Nuevo saldo: " + seleccionada.getSaldo());
                lista.repaint(); // Repaint para mostrar el saldo actualizado
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Monto inválido.");
            }
        });

        // Acción de volver
        btnVolver.addActionListener(e -> dispose());

        setSize(400, 250);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public VentanaRecargarTarjeta(JFrame dialog, Usuario usuario) {
    }
}
