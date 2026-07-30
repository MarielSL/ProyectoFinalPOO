package visual;

import java.util.regex.Pattern;

public class Validaciones {
	
	private static final String PATRON_SOLO_LETRAS = "^[A-Za-z\u00C0-\u00FF\u00D1\u00F1\\s]+$";
	private static final String PATRON_SOLO_NUMEROS = "^[0-9]+$";
	private static final String PATRON_CORREO = "^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$";
	
	public static boolean soloLetras(String texto) {
		if (texto == null) {
			return false;
		}
		String limpio = texto.trim();
		if (limpio.isEmpty()) {
			return false;
		}
		return Pattern.matches(PATRON_SOLO_LETRAS, limpio);
	}
	
	public static boolean soloNumeros(String texto) {
		if (texto == null) {
			return false;
		}
		String limpio = texto.trim();
		if (limpio.isEmpty()) {
			return false;
		}
		return Pattern.matches(PATRON_SOLO_NUMEROS, limpio);
	}
	
	public static boolean camposLlenos(String... campos) {
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
		if (texto == null) {
			return false;
		}
		String limpio = texto.trim();
		if (limpio.isEmpty()) {
			return false;
		}
		return Pattern.matches(PATRON_CORREO, limpio);
	}
	
	public static boolean telefonoValido(String texto, int cantidadDigitos) {
		if (texto == null) {
			return false;
		}
		String soloDigitos = texto.replaceAll("[^0-9]", "");
		return soloDigitos.length() == cantidadDigitos;
	}
}