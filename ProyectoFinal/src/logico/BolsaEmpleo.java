package logico;

import java.util.ArrayList;

public class BolsaEmpleo {

	private ArrayList<Usuario> usuarios;
	private ArrayList<Persona> personas;
	private ArrayList<Empresa> empresas;
	private ArrayList<SolicitudEmpleo> solicitudes;
	private ArrayList<Oferta> ofertas;
	public static int generadorIdPersona =0;
	public static int generadorIdEmpresa =0;
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
	
	public void regUser(Usuario user) {
		usuarios.add(user);
		generadorIdUser++;
	}
	
	public void regPersona(Persona persona) {
		personas.add(persona);
		generadorIdPersona++;
	}
	
	public void regEmpresa(Empresa empresa) {
		empresas.add(empresa);
		generadorIdEmpresa++;
	}
	
	public void refOferta (Oferta oferta) {
		ofertas.add(oferta);
		generadorIdOferta++;
	}
	
	public void regSolicitud(SolicitudEmpleo solicitud) {
		solicitudes.add(solicitud);
		generadorIdSolicitud++;
	}
	
	private Usuario buscarUser(String Id) {
		Usuario aux = null;
		boolean encontrado = false;
		int ind=0;
		
		while(!encontrado && ind<usuarios.size()) {
			if(usuarios.get(ind).getId().equalsIgnoreCase(Id)) {
				aux = usuarios.get(ind);
				encontrado = true;
			}
			ind++;
		}
		
		return aux;
	}
	
	private Persona buscarPersona (String Id) {
		Persona aux = null;
		boolean encontrado = false;
		int ind=0;
		
		while(!encontrado && ind<personas.size()) {
			if(personas.get(ind).getId().equalsIgnoreCase(Id)) {
				aux = personas.get(ind);
				encontrado = true;
			}
			ind++;
		}
		
		return aux;
	}
	
	private Empresa buscarEmpresa (String Id) {
		Empresa aux = null;
		boolean encontrado = false;
		int ind=0;
		
		while(!encontrado && ind<empresas.size()) {
			if(empresas.get(ind).getId().equalsIgnoreCase(Id)) {
				aux = empresas.get(ind);
				encontrado = true;
			}
			ind++;
		}
		
		return aux;
	}
	
	private Oferta buscarOferta (String Id) {
		Oferta aux = null;
		boolean encontrado = false;
		int ind=0;
		
		while(!encontrado && ind<ofertas.size()) {
			if(ofertas.get(ind).getId().equalsIgnoreCase(Id)) {
				aux = ofertas.get(ind);
				encontrado = true;
			}
			ind++;
		}
		
		return aux;
	}
	
	private SolicitudEmpleo buscarSolicitud (String Id) {
		SolicitudEmpleo aux = null;
		boolean encontrado = false;
		int ind=0;
		
		while(!encontrado && ind<solicitudes.size()) {
			if(solicitudes.get(ind).getId().equalsIgnoreCase(Id)) {
				aux = solicitudes.get(ind);
				encontrado = true;
			}
			ind++;
		}
		
		return aux;
	}
	
	public boolean dispUsername(String username) {
		boolean disp = true;
		int ind =0;
		
		while(disp && ind<usuarios.size()) {
			if(usuarios.get(ind).getUsername().equals(username)) {
				disp=false;
			}
			ind++;
		}
		return disp;
	}
}
