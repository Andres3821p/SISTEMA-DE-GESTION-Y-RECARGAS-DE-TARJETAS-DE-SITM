package app;

import gestor.GestorDatos;
import ventanas.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class App {

    public Container panelPrincipal;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestor de Tarjetas");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            // Manejo de ventana de cierre
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(frame,
                            "¿Deseas guardar antes de salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        GestorDatos.guardarTodo(); // Guardar los datos antes de salir
                    }
                    frame.dispose();  // Liberar recursos
                    System.exit(0);    // Terminar la aplicación
                }
            });

            // Establecer el panel principal de la ventana
            frame.setContentPane(new VentanaPrincipal(frame).getPanelPrincipal());

            // Ajustar el tamaño de la ventana
            frame.pack();
            frame.setMinimumSize(new Dimension(500, 400)); // Establecer un tamaño mínimo
            frame.setLocationRelativeTo(null); // Centrar ventana en pantalla
            frame.setVisible(true); // Hacer la ventana visible
        });
    }
}
