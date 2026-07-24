package visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelConSombra extends JPanel {

    private int radio;
    private static final int SOMBRA_OFFSET = 6;
    private static final int SOMBRA_GROSOR = 10;

    public PanelConSombra(int radio) {
        this.radio = radio;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Capas de sombra difuminada, redondeadas igual que la forma principal
        for (int i = SOMBRA_GROSOR; i > 0; i--) {
            g2.setColor(new Color(0, 0, 0, 0.03f));
            g2.fillRoundRect(
                    i / 2,
                    SOMBRA_OFFSET + i / 2,
                    getWidth() - i,
                    getHeight() - i,
                    radio,
                    radio
            );
        }

        // Forma principal redondeada, encima de la sombra
        g2.setColor(getBackground());
        g2.fillRoundRect(
                0,
                0,
                getWidth() - SOMBRA_OFFSET,
                getHeight() - SOMBRA_OFFSET,
                radio,
                radio
        );

        g2.dispose();
        super.paintComponent(g);
    }
}