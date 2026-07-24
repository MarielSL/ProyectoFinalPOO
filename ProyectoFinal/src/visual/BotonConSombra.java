package visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class BotonConSombra extends JButton {

	private int radio;
	private static final int SOMBRA_OFFSET = 5;
	private static final int SOMBRA_GROSOR = 8;

	public BotonConSombra(String texto, int radio) {
		super(texto);
		this.radio = radio;
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);

		// Capas de sombra difuminada, debajo del botón
		for (int i = SOMBRA_GROSOR; i > 0; i--) {
			g2.setColor(new Color(0, 0, 0, 0.04f));
			g2.fillRoundRect(
					i / 2,
					SOMBRA_OFFSET + i / 2,
					getWidth() - i,
					getHeight() - SOMBRA_OFFSET - i,
					radio,
					radio
					);
		}

		// Cuerpo del botón, encima de la sombra
		g2.setColor(getBackground());
		g2.fillRoundRect(
				0,
				0,
				getWidth(),
				getHeight() - SOMBRA_OFFSET,
				radio,
				radio
				);

		g2.dispose();
		super.paintComponent(g);
	}
}