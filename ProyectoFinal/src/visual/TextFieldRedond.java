package visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class TextFieldRedond extends JTextField {

	private int radio;

	public TextFieldRedond(int radio) {
		this.radio = radio;

		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
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
				getHeight() - 1,
				radio,
				radio
				);

		g2.dispose();
	}
}
