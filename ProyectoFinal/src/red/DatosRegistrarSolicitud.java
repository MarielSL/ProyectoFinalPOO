package red;

import java.io.Serializable;
import logico.AreaLaboral;
import logico.Jornada;
import logico.Modalidad;

public class DatosRegistrarSolicitud implements Serializable {

    private String puesto;
    private AreaLaboral areaLaboral;
    private float sueldoEsperado;
    private Modalidad modalidad;
    private Jornada jornada;

    public DatosRegistrarSolicitud(String puesto, AreaLaboral areaLaboral, float sueldoEsperado,
            Modalidad modalidad, Jornada jornada) {
        this.puesto = puesto;
        this.areaLaboral = areaLaboral;
        this.sueldoEsperado = sueldoEsperado;
        this.modalidad = modalidad;
        this.jornada = jornada;
    }

	public String getPuesto() {
		return puesto;
	}

	public AreaLaboral getAreaLaboral() {
		return areaLaboral;
	}

	public float getSueldoEsperado() {
		return sueldoEsperado;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public Jornada getJornada() {
		return jornada;
	}


}