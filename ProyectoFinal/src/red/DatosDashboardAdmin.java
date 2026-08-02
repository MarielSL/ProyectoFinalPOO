package red;

import java.io.Serializable;
import java.util.ArrayList;

import logico.Oferta;
import logico.Persona;

public class DatosDashboardAdmin implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Oferta> ofertas;
	private ArrayList<Persona> personas;
	private int totalEmpresas;
	private int actividadPlataforma;
	private int ofertasActivas;
	private int accionesPendientes;

	public DatosDashboardAdmin(ArrayList<Oferta> ofertas, ArrayList<Persona> personas, int totalEmpresas,
			int actividadPlataforma, int ofertasActivas, int accionesPendientes) {
		this.ofertas = ofertas;
		this.personas = personas;
		this.totalEmpresas = totalEmpresas;
		this.actividadPlataforma = actividadPlataforma;
		this.ofertasActivas = ofertasActivas;
		this.accionesPendientes = accionesPendientes;
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

	public int getActividadPlataforma() {
		return actividadPlataforma;
	}

	public int getOfertasActivas() {
		return ofertasActivas;
	}

	public int getAccionesPendientes() {
		return accionesPendientes;
	}
}