package logico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.stream.FileImageInputStream;

public class BolsaEmpleo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	private Usuario loginUser;
	
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
			bolsaEmpleo = cargarDatos();
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
	
	public Usuario getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(Usuario loginUser) {
		this.loginUser = loginUser;
	}
	
	public void regUser(Usuario user) {
		usuarios.add(user);
		generadorIdUser++;
		guardarDatos();
	}
	
	public void regPersona(Persona persona) {
		personas.add(persona);
		generadorIdPersona++;
		guardarDatos();
	}
	
	public void regEmpresa(Empresa empresa) {
		empresas.add(empresa);
		generadorIdEmpresa++;
		guardarDatos();
	}
	
	public void refOferta (Oferta oferta) {
		ofertas.add(oferta);
		generadorIdOferta++;
		guardarDatos();
	}
	
	public void regSolicitud(String idOferta, SolicitudEmpleo solicitud) {
		Oferta aux = buscarOferta(idOferta);
		aux.getSolicitudes().add(solicitud);
		solicitudes.add(solicitud);
		loginUser.getPersona().getSolicitudes().add(solicitud);
		generadorIdSolicitud++;
		guardarDatos();
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
	
	
	public float calcCoincidencia(Oferta oferta, Persona solicitante) {
		float porcentaje = 0;
		
		TipoPersona tipoSolicitado = oferta.getTipoCandidato();
		if( (tipoSolicitado == TipoPersona.UNIVERSITARIO && solicitante instanceof Universitario) ||
			(tipoSolicitado == TipoPersona.OBRERO && solicitante instanceof Obrero) ||
			(tipoSolicitado == TipoPersona.TECNICO && solicitante instanceof Tecnico)) {
			porcentaje += 25;
		}
		float expEsperada = oferta.getAniosExp();
		float expSolicitante = solicitante.getYearsExp();
		
		if(expSolicitante >= expEsperada) {
			porcentaje += 25;
		}
		else {
			porcentaje += (expSolicitante / expEsperada ) * 25;
		}
		
		if(oferta.getCiudad().equalsIgnoreCase(solicitante.getCiudad())) {
			porcentaje+=10;
		}
	
		
		if((!oferta.isLicencia()) || (oferta.isLicencia() && solicitante.isLicenciaConducir())) {
			porcentaje += 15;
		}
		
		if((!oferta.isDispMudar()) || (oferta.isDispMudar() && solicitante.isDispParaMudarse()) ) {
			porcentaje += 15;
		}
		if(oferta.getSexo().equals(Sexo.CUALQUIERA) || (oferta.getSexo() == solicitante.getSexo())) {
			porcentaje += 10;
		}

		return porcentaje;
	}

	
	public boolean validUserPassword(String username, String password) {
		boolean valid = false;
		Usuario aux = buscarUser(username);
		
		if(aux == null) {
			return valid;
		}
		
		if(aux.getPassword().equals(password)) {
			valid = true;
		}
		
		return valid;
	}
	
	public boolean confirmLogin(String text, String text2) {
		boolean login = false;
		for (Usuario usuario : usuarios) {
			
			if(usuario.getUsername().equals(text) && usuario.getPassword().equals(text2)){
				loginUser = usuario;
				login = true;
			}
		}
		return login;
	}
	
	public boolean verifUsuario(String text, String text2) {
		boolean login = false;
		for (Usuario usuario : usuarios) {
			if(usuario.getUsername().equals(text) && usuario.getPassword().equals(text2)){
				login = true;
			}
		}
		return login;
	}
	
	public boolean isEmpressRep(String rnc) {
		boolean rep = false;
		int ind = 0;
		
		while(!rep && ind<empresas.size()) {
			if(empresas.get(ind).getRnc().equals(rnc)) {
				rep = true;
			}
		}
		
		return rep;
	}
	
	public void modEmpresa(Empresa myEmpresa) {
		int indexEmpresa = buscarEmpresaIndex(myEmpresa.getId());
		empresas.set(indexEmpresa, myEmpresa);
		guardarDatos();
	}
	
	private int buscarEmpresaIndex(String idEmpresa) {
		int index = -1;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < empresas.size()) {
			if(empresas.get(ind).getId().equals(idEmpresa)){
				index = ind;
				encontrado = true;
			}
			ind++;
		}
		
		return index;
	}
	
	public void modUsuario(Usuario user) {
		int indexUser = buscarUsuarioindex(user.getId());
		usuarios.set(indexUser, user);
		guardarDatos();
	}
	
	private int buscarUsuarioindex(String idUsuario) {
		int index = -1;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < usuarios.size()) {
			if(usuarios.get(ind).getId().equals(idUsuario)){
				index = ind;
				encontrado = true;
			}
			ind++;
		}
		
		return index;
	}
	
	public void modSolicitante(Persona solicitante) {
		int indexSolicitante = buscarSolicitanteIndex(solicitante.getId());
		personas.set(indexSolicitante, solicitante);
		guardarDatos();
		
	}
	
	private int buscarSolicitanteIndex(String idSolicitante) {
		int index = -1;
		boolean encontrado = false;
		int ind = 0;
		
		while(!encontrado && ind < personas.size()) {
			if(personas.get(ind).getId().equals(idSolicitante)){
				index = ind;
				encontrado = true;
			}
			ind++;
		}
		
		return index;
	}
	
	public void guardarDatos() {
		try( ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("BolsaEmpleo.dat"))){
			salida.writeObject(this);
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static BolsaEmpleo cargarDatos() {
		try(ObjectInputStream entrada = new ObjectInputStream (new FileInputStream("BolsaEmpleo.dat"))){
			return (BolsaEmpleo) entrada.readObject();
		
		}catch(FileNotFoundException e) {
			return new BolsaEmpleo();
		
		}catch(IOException e) {
			e.printStackTrace();
			return new BolsaEmpleo();
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			return new BolsaEmpleo();
		}
	}
	    
}
