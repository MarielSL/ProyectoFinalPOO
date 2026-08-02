package red;

import java.io.Serializable;
import java.util.ArrayList;
import logico.Oferta;
import logico.Persona;

public class DatosDashboardAdmin implements Serializable {

    private ArrayList<Oferta> ofertas;
    private ArrayList<Persona> personas;
    private int totalEmpresas;

    public DatosDashboardAdmin(ArrayList<Oferta> ofertas, ArrayList<Persona> personas, int totalEmpresas) {
        this.ofertas = ofertas;
        this.personas = personas;
        this.totalEmpresas = totalEmpresas;
    }

	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public int getTotalEmpresas() {
		return totalEmpresas;
	}


}