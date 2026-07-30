package visual;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroSoloLetras extends DocumentFilter {

    private static final String PATRON_LETRAS = "[A-Za-zÀ-ÿÑñ\\s]*";

    @Override
    public void insertString(FilterBypass fb, int offset, String texto, AttributeSet atributos)
            throws BadLocationException {
        if (texto != null && texto.matches(PATRON_LETRAS)) {
            super.insertString(fb, offset, texto, atributos);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int longitud, String texto, AttributeSet atributos)
            throws BadLocationException {
        if (texto == null || texto.matches(PATRON_LETRAS)) {
            super.replace(fb, offset, longitud, texto, atributos);
        }
    }
}