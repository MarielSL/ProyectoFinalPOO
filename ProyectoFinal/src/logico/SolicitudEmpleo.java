package logico;

import java.io.Serializable;
import java.time.LocalDate;

public class SolicitudEmpleo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private EstadoSolicitud estado;
	private Persona candidato;
	private LocalDate fechaSolicitud;
	private AreaLaboral areaLaboral;
	private float sueldoEsperado;
	private Modalidad modalidad;
	private String puesto;
	private Jornada jornada;
	
	public SolicitudEmpleo(String id, EstadoSolicitud estado, Persona candidato, LocalDate fechaSolicitud,
			AreaLaboral areaLaboral, float sueldoEsperado, Modalidad modalidad, String puesto, Jornada jornada) {
		super();
		this.id = id;
		this.estado = estado;
		this.candidato = candidato;
		this.fechaSolicitud = fechaSolicitud;
		this.areaLaboral = areaLaboral;
		this.sueldoEsperado = sueldoEsperado;
		this.modalidad = modalidad;
		this.puesto = puesto;
		this.jornada = jornada;
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

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public AreaLaboral getAreaLaboral() {
		return areaLaboral;
	}

	public void setAreaLaboral(AreaLaboral areaLaboral) {
		this.areaLaboral = areaLaboral;
	}

	public float getSueldoEsperado() {
		return sueldoEsperado;
	}

	public void setSueldoEsperado(float sueldoEsperado) {
		this.sueldoEsperado = sueldoEsperado;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	public String getId() {
		return id;
	}
	
	
	
}
