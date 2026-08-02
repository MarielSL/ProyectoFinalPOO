package red;

import java.io.Serializable;
import java.util.ArrayList;

public class DatosGraficasEmpresa implements Serializable {

    private ArrayList<String> puestos;
    private ArrayList<Integer> candidatosPorOferta;
    private int ofertasActivas;
    private int ofertasCompletadas;

    public DatosGraficasEmpresa(ArrayList<String> puestos, ArrayList<Integer> candidatosPorOferta,
            int ofertasActivas, int ofertasCompletadas) {
        this.puestos = puestos;
        this.candidatosPorOferta = candidatosPorOferta;
        this.ofertasActivas = ofertasActivas;
        this.ofertasCompletadas = ofertasCompletadas;
    }

	public ArrayList<String> getPuestos() {
		return puestos;
	}

	public ArrayList<Integer> getCandidatosPorOferta() {
		return candidatosPorOferta;
	}

	public int getOfertasActivas() {
		return ofertasActivas;
	}

	public int getOfertasCompletadas() {
		return ofertasCompletadas;
	}


}