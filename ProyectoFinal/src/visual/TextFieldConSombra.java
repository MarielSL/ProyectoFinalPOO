package visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class TextFieldConSombra extends JTextField {

	private int radio;
	private static final int SOMBRA_OFFSET = 4;
	private static final int SOMBRA_GROSOR = 6;

	public TextFieldConSombra(int radio) {
		this.radio = radio;
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0, 12, SOMBRA_OFFSET, 12));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);

		// Capas de sombra difuminada, debajo del campo de texto
		for (int i = SOMBRA_GROSOR; i > 0; i--) {
			g2.setColor(new Color(0, 0, 0, 0.03f));
			g2.fillRoundRect(
					i / 2,
					SOMBRA_OFFSET + i / 2,
					getWidth() - i,
					getHeight() - SOMBRA_OFFSET - i,
					radio,
					radio
					);
		}

		// Fondo del campo de texto, encima de la sombra
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

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);
		g2.setColor(new Color(180, 180, 180));
		g2.drawRoundRect(
				0,
				0,
				getWidth() - 1,
				getHeight() - 1 - SOMBRA_OFFSET,
				radio,
				radio
				);
		g2.dispose();
	}
}