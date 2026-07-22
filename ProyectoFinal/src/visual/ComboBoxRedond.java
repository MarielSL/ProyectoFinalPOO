package visual;

import javax.swing.JComboBox;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class ComboBoxRedond<E> extends JComboBox<E> {

    private int radio;

    public ComboBoxRedond(int radio) {
        this.radio = radio;

        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        Shape formaRedondeada = new RoundRectangle2D.Float(
                0,
                0,
                getWidth(),
                getHeight(),
                radio,
                radio
        );

        g2.setColor(getBackground());
        g2.fill(formaRedondeada);

        g2.setClip(formaRedondeada);

        super.paintComponent(g2);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(getForeground());

        g2.drawRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                radio,
                radio
        );

        g2.dispose();
    }
}
