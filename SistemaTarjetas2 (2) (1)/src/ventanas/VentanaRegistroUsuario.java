package ventanas;

import gestor.GestorDatos;
import modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class VentanaRegistroUsuario extends JDialog {

    public VentanaRegistroUsuario(JFrame parent) {
        super(parent, "Registro de Usuario", true);
        setLayout(new GridLayout(5, 2, 5, 5));

        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtId = new JTextField();
        JTextField txtTelefono = new JTextField();

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnCancelar = new JButton("Cancelar");

        // Establecer tamaño fijo para los botones
        btnRegistrar.setPreferredSize(new Dimension(100, 40));
        btnCancelar.setPreferredSize(new Dimension(100, 40));

        add(new JLabel("Nombre:")); add(txtNombre);
        add(new JLabel("Apellido:")); add(txtApellido);
        add(new JLabel("ID:")); add(txtId);
        add(new JLabel("Teléfono:")); add(txtTelefono);
        add(btnRegistrar); add(btnCancelar);

        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String id = txtId.getText().trim();
            String telefono = txtTelefono.getText().trim();

            // Validar que los campos no estén vacíos y aplicar las expresiones regulares
            if (nombre.isEmpty() || apellido.isEmpty() ||
                    !Pattern.matches("[a-zA-Z ]+", nombre) ||
                    !Pattern.matches("[a-zA-Z ]+", apellido) ||
                    !Pattern.matches("\\d+", id) ||
                    !Pattern.matches("\\d+", telefono)) {
                JOptionPane.showMessageDialog(this, "Datos inválidos. Asegúrate de que:\n" +
                        "-Todos los campos deben presentar informacion. \n " +
                        "-Nombre y Apellido solo contengan letras.\n" +
                        "- ID y Teléfono sean numéricos.");
                return;
            }

            Usuario nuevo = new Usuario(nombre, apellido, id, telefono);
            GestorDatos.registrarUsuario(nuevo);
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        setSize(350, 220);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Método para establecer el fondo con una imagen
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Cargar y dibujar la imagen de fondo
        try {
            Image fondo = new ImageIcon("ruta/a/tu/imagen.jpg").getImage();
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}