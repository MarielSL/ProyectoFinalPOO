package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class DecisionCandidato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Persona candidato;
	private EstadoDecision estado;
	
	public DecisionCandidato(Persona candidato, EstadoDecision estado) {
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

	public EstadoDecision getEstado() {
		return estado;
	}

	public void setEstado(EstadoDecision estado) {
		this.estado = estado;
	}
	
	
}
