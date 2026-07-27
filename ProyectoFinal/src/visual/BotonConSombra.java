package visual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class BotonConSombra extends JButton {

	private int radio;
	private static final int SOMBRA_OFFSET = 5;
	private static final int SOMBRA_GROSOR = 8;
	private Color colorNormal;
	private Color colorHover;
	private boolean hoverPersonalizado = false; 

	public BotonConSombra(String texto, int radio) {
	    super(texto);
	    this.radio = radio;
	    setContentAreaFilled(false);
	    setFocusPainted(false);
	    setBorderPainted(false);
	    setOpaque(false);
	    addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            if (colorHover != null) {
	                setBackground(colorHover);
	            }
	        }
	        @Override
	        public void mouseExited(MouseEvent e) {
	            if (colorNormal != null) {
	                setBackground(colorNormal);
	            }
	        }
	    });
	}

	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		if (colorNormal == null || !bg.equals(colorHover)) {
			colorNormal = bg;
			if (!hoverPersonalizado) {
				colorHover = oscurecer(bg, 0.85f);
			}
		}
	}

	private Color oscurecer(Color color, float factor) {
		int r = Math.max((int) (color.getRed() * factor), 0);
		int g = Math.max((int) (color.getGreen() * factor), 0);
		int b = Math.max((int) (color.getBlue() * factor), 0);
		return new Color(r, g, b);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);
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

	public void setColorHover(Color colorHover) {
	    this.colorHover = colorHover;
	    this.hoverPersonalizado = true; 
	}
}