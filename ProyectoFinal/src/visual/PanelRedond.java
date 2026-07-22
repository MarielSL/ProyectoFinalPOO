package visual;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PanelRedond extends JPanel {

	private int radio;

    public PanelRedond(int radio) {
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

        g2.setColor(getBackground());
        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                radio,
                radio
        );

        g2.dispose();

        super.paintComponent(g);
    }
}
