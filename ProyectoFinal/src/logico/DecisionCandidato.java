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
	
	public DecisionCandidato BuscarCandidato(ArrayList<DecisionCandidato> candidatos, Persona candidato) {
		DecisionCandidato aux = null;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < candidatos.size()){
			if(candidato.getId().equals(candidatos.get(ind).getCandidato().getId())) {
				aux = candidatos.get(ind);
				encontrado = true;
			}
			ind++;
		}
		
		return null;
		
	}
}
