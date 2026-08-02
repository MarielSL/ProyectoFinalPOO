package logico;

import java.io.Serializable;

public class ResultMatch implements Serializable {

	private static final long serialVersionUID = 1L;

	private SolicitudEmpleo solicitud;
	private float porcentaje;
	
	public ResultMatch(SolicitudEmpleo solicitud, float porcentaje) {
		super();
		this.solicitud = solicitud;
		this.porcentaje = porcentaje;
	}

	public SolicitudEmpleo getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudEmpleo solicitud) {
		this.solicitud = solicitud;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
}