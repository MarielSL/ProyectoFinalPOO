package red;

import java.io.Serializable;
import logico.Oferta;
import logico.Persona;
import logico.SolicitudEmpleo;

public class DatosMejorMatchCandidato implements Serializable {

    private Persona candidato;
    private SolicitudEmpleo solicitud;
    private Oferta mejorOferta;
    private float mejorPorcentaje;

    public DatosMejorMatchCandidato(Persona candidato, SolicitudEmpleo solicitud, Oferta mejorOferta, float mejorPorcentaje) {
        this.candidato = candidato;
        this.solicitud = solicitud;
        this.mejorOferta = mejorOferta;
        this.mejorPorcentaje = mejorPorcentaje;
    }

	public Persona getCandidato() {
		return candidato;
	}

	public SolicitudEmpleo getSolicitud() {
		return solicitud;
	}

	public Oferta getMejorOferta() {
		return mejorOferta;
	}

	public float getMejorPorcentaje() {
		return mejorPorcentaje;
	}


}