package logico;

import java.util.ArrayList;

public class BolsaEmpleo {

	private ArrayList<Usuario> usuarios;
	private ArrayList<Persona> personas;
	private ArrayList<Empresa> empresas;
	private ArrayList<SolicitudEmpleo> solicitudes;
	private ArrayList<Oferta> ofertas;
	public static int generadorIdPersona =0;
	public static int generadorIdOferta =0;
	public static int generadorIdSolicitud =0;
	public static int generadorIdUser =0;
	private static BolsaEmpleo bolsaEmpleo = null;
	
	private BolsaEmpleo() {
		super();
		usuarios = new ArrayList<>();
		personas = new ArrayList<>();
		empresas = new ArrayList<>();
		solicitudes = new ArrayList<>();
		ofertas = new ArrayList<>();
	}
	public static BolsaEmpleo getInstancia() {
		if(bolsaEmpleo == null) {
			bolsaEmpleo = new BolsaEmpleo();
		}
		return bolsaEmpleo;
	}
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	public ArrayList<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(ArrayList<Empresa> empresas) {
		this.empresas = empresas;
	}
	public ArrayList<SolicitudEmpleo> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(ArrayList<SolicitudEmpleo> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	
	
}
