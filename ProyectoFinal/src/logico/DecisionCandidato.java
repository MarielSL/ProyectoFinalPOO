package logico;

import java.util.ArrayList;

public class DecisionCandidato {

	private Persona candidato;
	private EstadoCandidato estado;
	
	public DecisionCandidato(Persona candidato, EstadoCandidato estado) {
		super();
		this.candidato = candidato;
		this.estado = estado;
	}

	public Persona getCandidato() {
		return candidato;
	}

	public void setCandidato(Persona candidato) {
		this.candidato = candidato;
	}

	public EstadoCandidato getEstado() {
		return estado;
	}

	public void setEstado(EstadoCandidato estado) {
		this.estado = estado;
	}
	
	
}
