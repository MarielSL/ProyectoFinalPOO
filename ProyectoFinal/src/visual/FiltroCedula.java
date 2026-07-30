package visual;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroCedula extends DocumentFilter {

    private static final int MAX_DIGITOS = 11;

    @Override
    public void insertString(
            FilterBypass fb,
            int offset,
            String texto,
            AttributeSet atributos
    ) throws BadLocationException {

        replace(fb, offset, 0, texto, atributos);
    }

    @Override
    public void replace(
            FilterBypass fb,
            int offset,
            int longitud,
            String texto,
            AttributeSet atributos
    ) throws BadLocationException {

        String textoActual = fb.getDocument().getText(
                0,
                fb.getDocument().getLength()
        );

        String nuevoTexto =
                textoActual.substring(0, offset)
                + (texto == null ? "" : texto)
                + textoActual.substring(offset + longitud);

        String soloDigitos = nuevoTexto.replaceAll("[^0-9]", "");

        if (soloDigitos.length() > MAX_DIGITOS) {
            soloDigitos = soloDigitos.substring(0, MAX_DIGITOS);
        }

        String cedulaFormateada = formatearCedula(soloDigitos);

        fb.replace(
                0,
                fb.getDocument().getLength(),
                cedulaFormateada,
                atributos
        );
    }

    private String formatearCedula(String numeros) {

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < numeros.length(); i++) {
            if (i == 3) {
                resultado.append("-");
            }

            if (i == 10) {
                resultado.append("-");
            }

            resultado.append(numeros.charAt(i));
        }

        return resultado.toString();
    }
}