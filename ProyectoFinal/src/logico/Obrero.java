package logico;

import java.time.LocalDate;

public class Obrero extends Persona {

	private String habilidades;

	public Obrero(String id, String cedula, String nombre, String apellido, LocalDate fechNacim, String telefono,
			String direccion, Sexo sexo, String ciudad, boolean dispParaMudarse, boolean licenciaConducir,
			boolean estadoEmpleo, Usuario user, String habilidades) {
		super(id, cedula, nombre, apellido, fechNacim, telefono, direccion, sexo, ciudad, dispParaMudarse,
				licenciaConducir, estadoEmpleo, user);
		this.habilidades = habilidades;
	}

	public String getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}
	
	
	
	
	
	
}
