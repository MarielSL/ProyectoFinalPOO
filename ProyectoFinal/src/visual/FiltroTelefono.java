package visual;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroTelefono extends DocumentFilter {

	private static final int MAXIMO_DIGITOS = 10;

	public void insertString(FilterBypass fb, int offset, String texto, AttributeSet atributos) throws BadLocationException {
		actualizar(fb, offset, 0, texto, atributos);
	}

	public void replace(FilterBypass fb, int offset, int longitud, String texto, AttributeSet atributos) throws BadLocationException {
		actualizar(fb, offset, longitud, texto, atributos);
	}

	public void remove(FilterBypass fb, int offset, int longitud) throws BadLocationException {
		actualizar(fb, offset, longitud, "", null);
	}

	private void actualizar(FilterBypass fb, int offset, int longitud, String textoNuevo, AttributeSet atributos) throws BadLocationException {
		String textoActual = fb.getDocument().getText(0, fb.getDocument().getLength());
		String resultado = textoActual.substring(0, offset) + textoNuevo + textoActual.substring(offset + longitud);
		String digitos = resultado.replaceAll("[^0-9]", "");

		if (digitos.length() > MAXIMO_DIGITOS) {
			digitos = digitos.substring(0, MAXIMO_DIGITOS);
		}

		String formateado = formatearTelefono(digitos);

		fb.remove(0, fb.getDocument().getLength());
		fb.insertString(0, formateado, atributos);
	}

	private String formatearTelefono(String digitos) {
		StringBuilder resultado = new StringBuilder();

		for (int i = 0; i < digitos.length(); i++) {
			if (i == 0) {
				resultado.append("(");
			}
			if (i == 3) {
				resultado.append(") ");
			}
			if (i == 6) {
				resultado.append("-");
			}
			resultado.append(digitos.charAt(i));
		}

		return resultado.toString();
	}
}