package logico;

import java.util.ArrayList;

public class Empresa {
	
	private String rnc;
	private String nombre;
	private String telefono;
	private String direccion;
	private TipoEmpresa tipo;
	private boolean estado;
	private ArrayList<Oferta>lasOfertas;
	private Usuario user;
	
	public Empresa(String rnc, String nombre, String telefono, String direccion, TipoEmpresa tipo) {
		super();
		this.rnc = rnc;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.tipo = tipo;
		this.estado = true;
		this.lasOfertas = new ArrayList<>();
	}

	public void agregarOferta(Oferta o) {
		lasOfertas.add(o);
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public TipoEmpresa getTipo() {
		return tipo;
	}

	public void setTipo(TipoEmpresa tipo) {
		this.tipo = tipo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public ArrayList<Oferta> getLasOfertas() {
		return lasOfertas;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
}