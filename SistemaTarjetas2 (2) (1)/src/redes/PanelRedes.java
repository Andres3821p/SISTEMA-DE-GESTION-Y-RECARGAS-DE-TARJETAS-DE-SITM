package redes;

import javax.swing.*;
import java.awt.*;

public class PanelRedes extends JPanel {
    public PanelRedes() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setOpaque(false); // no tapa fondo
        add(crearIcono("/recursos/whatsapp.png", "WhatsApp"));
        add(crearIcono("/recursos/facebook.png", "Facebook"));
        add(crearIcono("/recursos/instagram.jpg", "Instagram"));
    }

    private JLabel crearIcono(String ruta, String tooltip) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image img = icon.getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setToolTipText(tooltip);
        return label;
    }
}