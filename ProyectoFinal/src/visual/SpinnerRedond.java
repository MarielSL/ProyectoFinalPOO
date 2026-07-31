package visual;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class SpinnerRedond extends JSpinner {

    private int radio;

    public SpinnerRedond(int radio) {
        super();
        this.radio = radio;

        setOpaque(false);
        setBorder(new EmptyBorder(4, 10, 4, 6));
        setUI(new BasicSpinnerUI());
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
                getWidth() - 1,
                getHeight() - 1,
                radio,
                radio
        );

        g2.setColor(new Color(190, 190, 190));
        g2.drawRoundRect(
                0,
                0,
                getWidth() - 1,
                getHeight() - 1,
                radio,
                radio
        );

        g2.dispose();

        super.paintComponent(g);
    }
    
    public void aplicarColorSpinner(JSpinner spinner, Color color) {

        spinner.setBackground(color);

        JComponent editor = spinner.getEditor();

        if (editor instanceof JSpinner.DefaultEditor) {
            JFormattedTextField campo =
                    ((JSpinner.DefaultEditor) editor).getTextField();

            campo.setBackground(color);
            campo.setOpaque(true);
            campo.setBorder(null);
        }

        for (Component componente : spinner.getComponents()) {
            componente.setBackground(color);

            if (componente instanceof Container) {
                cambiarColorComponentes(
                        (Container) componente,
                        color
                );
            }
        }
    }

    private void cambiarColorComponentes(
            Container contenedor,
            Color color) {

        for (Component componente :
                contenedor.getComponents()) {

            componente.setBackground(color);

            if (componente instanceof Container) {
                cambiarColorComponentes(
                        (Container) componente,
                        color
                );
            }
        }
    }
}