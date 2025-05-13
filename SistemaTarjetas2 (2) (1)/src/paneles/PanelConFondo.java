package paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class PanelConFondo extends JPanel {
    private Image fondo;

    public PanelConFondo() {
        try {
            fondo = ImageIO.read(getClass().getResource("/recursos/TRANSCA.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Evita que se estiren
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
