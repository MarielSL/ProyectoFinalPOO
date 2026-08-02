package red;

import java.io.Serializable;
import logico.AreaLaboral;
import logico.Jornada;
import logico.Modalidad;

public class DatosModificarSolicitud implements Serializable {

    private String puesto;
    private AreaLaboral areaLaboral;
    private Jornada jornada;
    private Modalidad modalidad;
    private float sueldoEsperado;

    public DatosModificarSolicitud(String puesto, AreaLaboral areaLaboral, Jornada jornada,
            Modalidad modalidad, float sueldoEsperado) {
        this.puesto = puesto;
        this.areaLaboral = areaLaboral;
        this.jornada = jornada;
        this.modalidad = modalidad;
        this.sueldoEsperado = sueldoEsperado;
    }

	public String getPuesto() {
		return puesto;
	}

	public AreaLaboral getAreaLaboral() {
		return areaLaboral;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public float getSueldoEsperado() {
		return sueldoEsperado;
	}


}