package paneles;

import app.App;
import gestor.GestorDatos;
import modelos.Administrador;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelIngresoAdministrador extends JPanel {
    public PanelIngresoAdministrador(JFrame frame, App app) {
        setLayout(new GridLayout(3, 2, 10, 10)); // Añadido espaciado entre los componentes

        JLabel lblId = new JLabel("Ingrese su ID:");
        JTextField txtId = new JTextField();

        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        add(lblId);
        add(txtId);
        add(btnIngresar);
        add(btnCancelar);

        // Crear un mapa para la búsqueda eficiente de administradores por ID
        HashMap<String, Administrador> administradoresMap = new HashMap<>();
        for (Administrador a : GestorDatos.getAdministradores()) {
            administradoresMap.put(a.getId(), a);
        }

        btnIngresar.addActionListener(e -> {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un ID.");
                return;
            }

            Administrador admin = administradoresMap.get(id);
            if (admin != null) {
                frame.setContentPane(new PanelMenuAdministrador(frame, admin));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(this, "Administrador no encontrado. Verifique el ID ingresado.");
            }
        });

        btnCancelar.addActionListener(e -> {
            // Usar la instancia de app pasada en lugar de crear una nueva
            frame.setContentPane(app.panelPrincipal);
            frame.revalidate();
        });
    }
}
