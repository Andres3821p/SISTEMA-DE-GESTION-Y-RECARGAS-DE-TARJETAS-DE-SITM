package ventanas;

import gestor.GestorDatos;
import modelos.Administrador;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class VentanaRegistro extends JDialog {

    public VentanaRegistro(JFrame frame, boolean esAdmin) {
        super(frame, "Registro de " + (esAdmin ? "Administrador" : "Usuario"), true);
        setLayout(new GridLayout(6, 2, 5, 5));

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblId = new JLabel("ID:");
        JLabel lblTelefono = new JLabel("Teléfono:");

        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtId = new JTextField();
        JTextField txtTelefono = new JTextField();

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        // Establecer tamaño fijo para los botones
        btnRegistrar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));

        add(lblNombre); add(txtNombre);
        add(lblApellido); add(txtApellido);
        add(lblId); add(txtId);
        add(lblTelefono); add(txtTelefono);
        add(btnRegistrar); add(btnCancelar);

        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String id = txtId.getText().trim();
            String telefono = txtTelefono.getText().trim();

            // Validar que los campos no estén vacíos
            if (nombre.isEmpty() || apellido.isEmpty() || id.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            // Validar que solo se introduzcan letras en nombre y apellido
            if (!Pattern.matches("[a-zA-Z]+", nombre) || !Pattern.matches("[a-zA-Z]+", apellido)) {
                JOptionPane.showMessageDialog(this, "El nombre y apellido deben contener solo letras.");
                return;
            }

            // Validar que teléfono solo contenga números
            if (!telefono.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El teléfono debe contener solo números.");
                return;
            }

            // Registrar el administrador o el usuario según corresponda
            if (esAdmin) {
                GestorDatos.agregarAdministrador(new Administrador(nombre, apellido, id, telefono));
                JOptionPane.showMessageDialog(this, "Administrador registrado exitosamente.");
            } else {
                GestorDatos.agregarUsuario(new Usuario(nombre, apellido, id, telefono));
                JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            }

            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        setSize(350, 250);
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    // Método para establecer el fondo con una imagen
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Cargar y dibujar la imagen de fondo
        try {
            Image fondo = new ImageIcon("C:\\Users\\andre\\Desktop\\TRANSCARIBE").getImage();
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
