package logico;

import java.time.LocalDate;

public class Tecnico extends Persona {

	private String tecnico;
	
	public Tecnico(String id, String cedula, String nombre, String apellido, LocalDate fechNacim, String telefono,
			String direccion, Sexo sexo, String ciudad, boolean dispParaMudarse, boolean licenciaConducir,
			boolean estadoEmpleo, Usuario user,int yearsExp, String tecnico) {
		super(id, cedula, nombre, apellido, fechNacim, telefono, direccion, sexo, ciudad, dispParaMudarse,
				licenciaConducir, estadoEmpleo, user, yearsExp);
		this.tecnico = tecnico;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}


	
	
	
}
