package red;

import java.io.Serializable;

public class DatosEstadisticasCandidato implements Serializable {

    private String estadoBusqueda;
    private int ofertasDisponibles;
    private float mayorCoincidencia;

    public DatosEstadisticasCandidato(String estadoBusqueda, int ofertasDisponibles, float mayorCoincidencia) {
        this.estadoBusqueda = estadoBusqueda;
        this.ofertasDisponibles = ofertasDisponibles;
        this.mayorCoincidencia = mayorCoincidencia;
    }

	public String getEstadoBusqueda() {
		return estadoBusqueda;
	}

	public int getOfertasDisponibles() {
		return ofertasDisponibles;
	}

	public float getMayorCoincidencia() {
		return mayorCoincidencia;
	}

}