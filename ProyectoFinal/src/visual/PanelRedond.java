package visual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PanelRedond extends JPanel {

    private int radio;
    private Color colorBorde = null;
    private int grosorBorde = 0;

    public PanelRedond(int radio) {
        this.radio = radio;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Fondo redondeado
        g2.setColor(getBackground());
        g2.fillRoundRect(
            0,
            0,
            getWidth() - 1,
            getHeight() - 1,
            radio,
            radio
        );

        // Solo dibuja el borde si fue configurado
        if (colorBorde != null && grosorBorde > 0) {

            g2.setColor(colorBorde);
            g2.setStroke(new BasicStroke(grosorBorde));

            int ajuste = grosorBorde / 2;

            g2.drawRoundRect(
                ajuste,
                ajuste,
                getWidth() - grosorBorde - 1,
                getHeight() - grosorBorde - 1,
                radio,
                radio
            );
        }

        g2.dispose();
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
        repaint();
    }

    public void setGrosorBorde(int grosorBorde) {
        this.grosorBorde = grosorBorde;
        repaint();
    }

    public void quitarBorde() {
        this.colorBorde = null;
        this.grosorBorde = 0;
        repaint();
    }
}