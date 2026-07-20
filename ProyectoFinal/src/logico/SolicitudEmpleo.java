package logico;

import java.time.LocalDate;

public class SolicitudEmpleo {
	
	private String id;
	private String puestoDeseado;
	private String areaInteres;
	private String ubicacionPreferida;
	private Modalidad modalidad;
	private Jornada jornada;
	private EstadoSolicitud estado;
	private Persona candidato;
	private Oferta oferta;
	private float porcentajeCoincidencia;
	private boolean dispMudar;
	private int experiencia;
	private LocalDate fechaSolicitud;
	private boolean licencia;
	
	public SolicitudEmpleo(String id, String puestoDeseado, String areaInteres, String ubicacionPreferida,
			Modalidad modalidad, Jornada jornada, EstadoSolicitud estado, Persona candidato, Oferta oferta,
			float porcentajeCoincidencia, boolean dispMudar, int experiencia, LocalDate fechaSolicitud, boolean licencia) {
		super();
		this.id = id;
		this.puestoDeseado = puestoDeseado;
		this.areaInteres = areaInteres;
		this.ubicacionPreferida = ubicacionPreferida;
		this.modalidad = modalidad;
		this.jornada = jornada;
		this.estado = estado;
		this.candidato = candidato;
		this.oferta = oferta;
		this.porcentajeCoincidencia = porcentajeCoincidencia;
		this.dispMudar = dispMudar;
		this.experiencia = experiencia;
		this.fechaSolicitud = fechaSolicitud;
		this.licencia = licencia;
	}

	public String getPuestoDeseado() {
		return puestoDeseado;
	}

	public void setPuestoDeseado(String puestoDeseado) {
		this.puestoDeseado = puestoDeseado;
	}

	public String getAreaInteres() {
		return areaInteres;
	}

	public void setAreaInteres(String areaInteres) {
		this.areaInteres = areaInteres;
	}

	public String getUbicacionPreferida() {
		return ubicacionPreferida;
	}

	public void setUbicacionPreferida(String ubicacionPreferida) {
		this.ubicacionPreferida = ubicacionPreferida;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
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

	public boolean isDispMudar() {
		return dispMudar;
	}

	public void setDispMudar(boolean dispMudar) {
		this.dispMudar = dispMudar;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
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

	public boolean isLicencia() {
		return licencia;
	}

	public void setLicencia(boolean licencia) {
		this.licencia = licencia;
	}
	
	
	
	

}
