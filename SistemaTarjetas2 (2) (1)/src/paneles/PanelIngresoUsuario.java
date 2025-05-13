package paneles;

import app.App;
import gestor.GestorDatos;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PanelIngresoUsuario extends JPanel {
    public PanelIngresoUsuario(JFrame frame, App app) {
        setLayout(new GridLayout(3, 2, 10, 10)); // Añadido espaciado entre los componentes

        JLabel lblId = new JLabel("Ingrese su ID:");
        JTextField txtId = new JTextField();

        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        add(lblId);
        add(txtId);
        add(btnIngresar);
        add(btnCancelar);

        // Crear un mapa para la búsqueda eficiente de usuarios por ID
        HashMap<String, Usuario> usuariosMap = new HashMap<>();
        for (Usuario u : GestorDatos.getUsuarios()) {
            usuariosMap.put(u.getId(), u);
        }

        btnIngresar.addActionListener(e -> {
            String id = txtId.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un ID.");
                return;
            }

            Usuario usuario = usuariosMap.get(id);
            if (usuario != null) {
                frame.setContentPane(new PanelMenuUsuario(frame, usuario));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado. Verifique el ID ingresado.");
            }
        });

        btnCancelar.addActionListener(e -> {
            // Usar la instancia de app pasada en lugar de crear una nueva
            frame.setContentPane(app.panelPrincipal);
            frame.revalidate();
        });
    }
}
