package visual;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroLongitudMaxima extends DocumentFilter {

	private int maximo;

	public FiltroLongitudMaxima(int maximo) {
		this.maximo = maximo;
	}

	public void insertString(FilterBypass fb, int offset, String texto, AttributeSet atributos) throws BadLocationException {
		int longitudActual = fb.getDocument().getLength();
		if (longitudActual + texto.length() <= maximo) {
			super.insertString(fb, offset, texto, atributos);
		}
	}

	public void replace(FilterBypass fb, int offset, int cantidadAReemplazar, String texto, AttributeSet atributos) throws BadLocationException {
		int longitudActual = fb.getDocument().getLength();
		int longitudDespues = longitudActual - cantidadAReemplazar + texto.length();
		if (longitudDespues <= maximo) {
			super.replace(fb, offset, cantidadAReemplazar, texto, atributos);
		}
	}
}