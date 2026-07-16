package logico;

import java.time.LocalDate;

public class Universitario extends Persona {
	
	private String carrera;

	public Universitario(String id, String cedula, String nombre, String apellido, LocalDate fechNacim, String telefono,
			String direccion, Sexo sexo, String ciudad, boolean dispParaMudarse, boolean licenciaConducir,
			boolean estadoEmpleo, Usuario user, String carrera) {
		super(id, cedula, nombre, apellido, fechNacim, telefono, direccion, sexo, ciudad, dispParaMudarse,
				licenciaConducir, estadoEmpleo, user);
		this.carrera = carrera;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	

}
