package visual;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class Escalador {
	public static double escalaX;
	public static double escalaY;
	
	static {
		double x;
		double y;
		try {
			Rectangle pantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			x = (double) pantalla.width / 1920;
			y = (double) pantalla.height / 1040;
		} catch (Exception e) {
			x = 1.0;
			y = 1.0;
		}
		escalaX = x;
		escalaY = y;
	}
	
	public static void b(JComponent componente, int x, int y, int ancho, int alto) {
		componente.setBounds((int) Math.round(x * escalaX), (int) Math.round(y * escalaY), (int) Math.round(ancho * escalaX), (int) Math.round(alto * escalaY));
	}
	
	public static int t(int tamano) {
		return (int) Math.round(tamano * escalaY);
	}
}