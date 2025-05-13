package paneles;

import app.App;
import gestor.GestorDatos;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;

public class PanelRegistroUsuario extends JPanel {

    public PanelRegistroUsuario(JFrame frame) {
        setLayout(new GridLayout(7, 2, 5, 5));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblApellido = new JLabel("Apellido:");
        JTextField txtApellido = new JTextField();

        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField();

        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();

        JLabel lblMensajeError = new JLabel();
        lblMensajeError.setForeground(Color.RED);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnVolver = new JButton("Volver");

        add(lblNombre); add(txtNombre);
        add(lblApellido); add(txtApellido);
        add(lblId); add(txtId);
        add(lblTelefono); add(txtTelefono);
        add(lblMensajeError);  // Muestra errores si los hay
        add(btnRegistrar);
        add(btnVolver);

        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String id = txtId.getText().trim();
            String telefono = txtTelefono.getText().trim();


            // Validaciones
            if (nombre.isEmpty() || apellido.isEmpty() || id.isEmpty() || telefono.isEmpty()) {
                lblMensajeError.setText("Todos los campos son obligatorios.");
                return;
            }

            if (!validarTelefono(telefono)) {
                lblMensajeError.setText("Teléfono inválido.");
                return;
            }

            if (GestorDatos.buscarUsuarioPorId(id) != null) {
                lblMensajeError.setText("El ID ya está registrado.");
                return;
            }

            Usuario nuevoUsuario = new Usuario(nombre, apellido, id, telefono);
            GestorDatos.agregarUsuario(nuevoUsuario);

            // Limpiar campos después de registrar
            txtNombre.setText("");
            txtApellido.setText("");
            txtId.setText("");
            txtTelefono.setText("");
            lblMensajeError.setText("");

            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");

        });

        btnVolver.addActionListener(e -> {
            frame.setContentPane(new App().panelPrincipal);
            frame.revalidate();
        });
    }

    // Método para validar el formato del teléfono (opcional)
    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{10}");
    }
}
