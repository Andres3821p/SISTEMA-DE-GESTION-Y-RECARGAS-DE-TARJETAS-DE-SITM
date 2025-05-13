package componentes;

import javax.swing.*;
import java.awt.*;

public class BotonTransparente extends JButton {
    public BotonTransparente(String texto) {
        super(texto);
        setOpaque(false);
        setContentAreaFilled(false);
        setForeground(Color.BLACK);
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.PLAIN, 14));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(255, 255, 255, 100)); // Blanco semi-transparente
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.dispose();
        super.paintComponent(g);
    }
}
