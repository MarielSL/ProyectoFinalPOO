package logico;

import java.time.LocalDate;

public class Tecnico extends Persona {

	private String tecnico;
	private int aniosExp;
	
	public Tecnico(String id, String cedula, String nombre, String apellido, LocalDate fechNacim, String telefono,
			String direccion, Sexo sexo, String ciudad, boolean dispParaMudarse, boolean licenciaConducir,
			boolean estadoEmpleo, Usuario user, String tecnico, int aniosExp) {
		super(id, cedula, nombre, apellido, fechNacim, telefono, direccion, sexo, ciudad, dispParaMudarse,
				licenciaConducir, estadoEmpleo, user);
		this.tecnico = tecnico;
		this.aniosExp = aniosExp;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public int getAniosExp() {
		return aniosExp;
	}

	public void setAniosExp(int aniosExp) {
		this.aniosExp = aniosExp;
	}
	
	
	
	
}
