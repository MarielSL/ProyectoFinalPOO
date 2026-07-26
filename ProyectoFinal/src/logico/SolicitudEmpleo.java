package logico;

import java.time.LocalDate;

public class SolicitudEmpleo {
	
	private String id;
	private EstadoSolicitud estado;
	private Persona candidato;
	private Oferta oferta;
	private float porcentajeCoincidencia;
	private LocalDate fechaSolicitud;
	
	public SolicitudEmpleo(String id, EstadoSolicitud estado, Persona candidato, Oferta oferta,
			float porcentajeCoincidencia, LocalDate fechaSolicitud) {
		super();
		this.id = id;
		this.estado = estado;
		this.candidato = candidato;
		this.oferta = oferta;
		this.porcentajeCoincidencia = porcentajeCoincidencia;
		this.fechaSolicitud = fechaSolicitud;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}

	public Persona getCandidato() {
		return candidato;
	}

	public void setCandidato(Persona candidato) {
		this.candidato = candidato;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public float getPorcentajeCoincidencia() {
		return porcentajeCoincidencia;
	}

	public void setPorcentajeCoincidencia(float porcentajeCoincidencia) {
		this.porcentajeCoincidencia = porcentajeCoincidencia;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getId() {
		return id;
	}
	

	

}
