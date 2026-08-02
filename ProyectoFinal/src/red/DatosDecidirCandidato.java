package red;

import java.io.Serializable;
import logico.EstadoDecision;

public class DatosDecidirCandidato implements Serializable {

    private String ofertaId;
    private String candidatoId;
    private EstadoDecision decision;

    public DatosDecidirCandidato(String ofertaId, String candidatoId, EstadoDecision decision) {
        this.ofertaId = ofertaId;
        this.candidatoId = candidatoId;
        this.decision = decision;
    }

	public String getOfertaId() {
		return ofertaId;
	}

	public String getCandidatoId() {
		return candidatoId;
	}

	public EstadoDecision getDecision() {
		return decision;
	}

   
}