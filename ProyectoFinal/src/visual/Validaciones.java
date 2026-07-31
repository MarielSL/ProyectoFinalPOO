package visual;

import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Validaciones {

    // Patrones compilados una sola vez (mejor rendimiento)
    private static final Pattern PATRON_SOLO_LETRAS = Pattern.compile("^\\p{L}[\\p{L}\\s]*$");
    private static final Pattern PATRON_SOLO_NUMEROS = Pattern.compile("^\\d+$");
    private static final Pattern PATRON_CORREO = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    
    private static final int MAX_DIGITOS_CEDULA = 11;
    private static final int MAX_DIGITOS_TELEFONO = 10;

    // Método genérico para validar contra un patrón
    private static boolean validarConPatron(String texto, Pattern patron) {
        if (texto == null) {
            return false;
        }
        String limpio = texto.trim();
        return !limpio.isEmpty() && patron.matcher(limpio).matches();
    }

    public static boolean soloLetras(String texto) {
        return validarConPatron(texto, PATRON_SOLO_LETRAS);
    }

    public static boolean soloNumeros(String texto) {
        return validarConPatron(texto, PATRON_SOLO_NUMEROS);
    }

    public static boolean camposLlenos(String... campos) {
        if (campos == null) {
            return false;
        }
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean longitudValida(String texto, int minimo, int maximo) {
        if (texto == null) {
            return false;
        }
        int largo = texto.trim().length();
        return largo >= minimo && largo <= maximo;
    }

    public static boolean correoValido(String texto) {
        return validarConPatron(texto, PATRON_CORREO);
    }

    public static boolean telefonoValido(String texto, int cantidadDigitos) {
        if (texto == null) {
            return false;
        }
        String soloDigitos = texto.replaceAll("\\D", "");
        return soloDigitos.length() == cantidadDigitos;
    }

    public static boolean cedulaValida(String cedula) {
        if (cedula == null) {
            return false;
        }
        String soloNumeros = cedula.replaceAll("\\D", "");
        return soloNumeros.length() == MAX_DIGITOS_CEDULA;
    }

    public static String formatearCedula(String texto) {
        if (texto == null) {
            return "";
        }
        String soloDigitos = texto.replaceAll("\\D", "");
        if (soloDigitos.length() > MAX_DIGITOS_CEDULA) {
            soloDigitos = soloDigitos.substring(0, MAX_DIGITOS_CEDULA);
        }

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < soloDigitos.length(); i++) {
            if (i == 3 || i == 7) { // Formato típico: 001-0000000-0
                resultado.append("-");
            }
            resultado.append(soloDigitos.charAt(i));
        }
        return resultado.toString();
    }

    public static String formatearTelefono(String digitos) {
        if (digitos == null) {
            return "";
        }
        String soloDigitos = digitos.replaceAll("\\D", "");
        if (soloDigitos.length() > MAX_DIGITOS_TELEFONO) {
            soloDigitos = soloDigitos.substring(0, MAX_DIGITOS_TELEFONO);
        }

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < soloDigitos.length(); i++) {
            if (i == 0) {
                resultado.append("(");
            }
            if (i == 3) {
                resultado.append(") ");
            }
            if (i == 6) {
                resultado.append("-");
            }
            resultado.append(soloDigitos.charAt(i));
        }
        return resultado.toString();
    }
    
    public static String formatearRnc(String texto) {
        if (texto == null) {
            return "";
        }

        String soloDigitos = texto.replaceAll("\\D", "");

        if (soloDigitos.length() > 9) {
            soloDigitos = soloDigitos.substring(0, 9);
        }

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < soloDigitos.length(); i++) {
            if (i == 1 || i == 3 || i == 8) {
                resultado.append("-");
            }
            resultado.append(soloDigitos.charAt(i));
        }

        return resultado.toString();
    }

    public static DocumentFilter filtroLongitudMaxima(int maximo) {
        return new DocumentFilter() {

            @Override
            public void insertString(FilterBypass fb, int offset, String texto, AttributeSet atributos)
                    throws BadLocationException {
                if (texto == null) {
                    return;
                }
                int longitudActual = fb.getDocument().getLength();
                if (longitudActual + texto.length() <= maximo) {
                    super.insertString(fb, offset, texto, atributos);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int cantidadAReemplazar, String texto,
                                AttributeSet atributos) throws BadLocationException {
                if (texto == null) {
                    super.replace(fb, offset, cantidadAReemplazar, texto, atributos);
                    return;
                }
                int longitudActual = fb.getDocument().getLength();
                int longitudDespues = longitudActual - cantidadAReemplazar + texto.length();
                if (longitudDespues <= maximo) {
                    super.replace(fb, offset, cantidadAReemplazar, texto, atributos);
                }
            }
        };
    }

    public static DocumentFilter filtroCedulaFormateada() {
        return new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) 
                    throws BadLocationException {
                if (string == null) {
                    return;
                }
                String soloDigitos = string.replaceAll("\\D", "");
                if (soloDigitos.isEmpty()) {
                    return;
                }
                
                // Obtener texto actual sin formato
                String textoActual = fb.getDocument().getText(0, fb.getDocument().getLength());
                String textoSinFormato = textoActual.replaceAll("-", "");
                
                // Insertar nuevos dígitos en la posición correcta
                String nuevoTexto = textoSinFormato.substring(0, offset) + soloDigitos + 
                                   textoSinFormato.substring(offset);
                
                if (nuevoTexto.length() > MAX_DIGITOS_CEDULA) {
                    nuevoTexto = nuevoTexto.substring(0, MAX_DIGITOS_CEDULA);
                }
                
                fb.replace(0, fb.getDocument().getLength(), formatearCedula(nuevoTexto), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
                    throws BadLocationException {
                if (text == null) {
                    super.replace(fb, offset, length, text, attrs);
                    return;
                }
                
                String soloDigitos = text.replaceAll("\\D", "");
                String textoActual = fb.getDocument().getText(0, fb.getDocument().getLength());
                String textoSinFormato = textoActual.replaceAll("-", "");
                
                String nuevoTexto = textoSinFormato.substring(0, offset) + soloDigitos + 
                                   textoSinFormato.substring(offset + length);
                
                if (nuevoTexto.length() > MAX_DIGITOS_CEDULA) {
                    nuevoTexto = nuevoTexto.substring(0, MAX_DIGITOS_CEDULA);
                }
                
                fb.replace(0, fb.getDocument().getLength(), formatearCedula(nuevoTexto), attrs);
            }
        };
    }
    
    public static DocumentFilter filtroTelefonoFormateado() {
        return new DocumentFilter() {

            private static final int MAXIMO_DIGITOS = MAX_DIGITOS_TELEFONO; // 10

            @Override
            public void insertString(FilterBypass fb, int offset, String texto, AttributeSet atributos)
                    throws BadLocationException {
                actualizar(fb, offset, 0, texto, atributos);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int longitud, String texto, AttributeSet atributos)
                    throws BadLocationException {
                actualizar(fb, offset, longitud, texto, atributos);
            }

            @Override
            public void remove(FilterBypass fb, int offset, int longitud) throws BadLocationException {
                actualizar(fb, offset, longitud, "", null);
            }

            private void actualizar(FilterBypass fb, int offset, int longitud, String textoNuevo,
                                    AttributeSet atributos) throws BadLocationException {

                String textoActual = fb.getDocument().getText(0, fb.getDocument().getLength());
                String resultado = textoActual.substring(0, offset)
                        + (textoNuevo == null ? "" : textoNuevo)
                        + textoActual.substring(offset + longitud);

                String digitos = resultado.replaceAll("[^0-9]", "");

                if (digitos.length() > MAXIMO_DIGITOS) {
                    digitos = digitos.substring(0, MAXIMO_DIGITOS);
                }

                String formateado = formatearTelefono(digitos);

                fb.remove(0, fb.getDocument().getLength());
                fb.insertString(0, formateado, atributos);
            }
        };
    }
    
    public static DocumentFilter filtroRncFormateado() {
        return new DocumentFilter() {

            private static final int MAXIMO_DIGITOS_RNC = 9;

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                actualizar(fb, offset, 0, string, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                actualizar(fb, offset, length, text, attrs);
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                actualizar(fb, offset, length, "", null);
            }

            private void actualizar(FilterBypass fb, int offset, int length, String textoNuevo,
                                    AttributeSet atributos) throws BadLocationException {

                String textoActual = fb.getDocument().getText(0, fb.getDocument().getLength());

                String resultado = textoActual.substring(0, offset)
                        + (textoNuevo == null ? "" : textoNuevo)
                        + textoActual.substring(offset + length);

                String digitos = resultado.replaceAll("\\D", "");

                if (digitos.length() > MAXIMO_DIGITOS_RNC) {
                    digitos = digitos.substring(0, MAXIMO_DIGITOS_RNC);
                }

                String formateado = formatearRnc(digitos);

                fb.replace(0, fb.getDocument().getLength(), formateado, atributos);
            }
        };
    }
}