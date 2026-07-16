package logico;

import java.time.LocalDate;

public abstract class Persona {
	private String id;
	private String cedula;
	private String nombre;
	private String apellido;
	private LocalDate fechNacim;
	private String telefono;
	private String direccion;
	private Sexo sexo;
	private String ciudad;
	private boolean dispParaMudarse;
	private boolean licenciaConducir;
	private boolean estadoEmpleo;
	private Usuario user;
	
	public Persona(String id, String cedula, String nombre, String apellido, LocalDate fechNacim, String telefono,
			String direccion, Sexo sexo, String ciudad, boolean dispParaMudarse, boolean licenciaConducir,
			boolean estadoEmpleo, Usuario user) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechNacim = fechNacim;
		this.telefono = telefono;
		this.direccion = direccion;
		this.sexo = sexo;
		this.ciudad = ciudad;
		this.dispParaMudarse = dispParaMudarse;
		this.licenciaConducir = licenciaConducir;
		this.estadoEmpleo = estadoEmpleo;
		this.user = user;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechNacim() {
		return fechNacim;
	}

	public void setFechNacim(LocalDate fechNacim) {
		this.fechNacim = fechNacim;
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public boolean isDispParaMudarse() {
		return dispParaMudarse;
	}

	public void setDispParaMudarse(boolean dispParaMudarse) {
		this.dispParaMudarse = dispParaMudarse;
	}

	public boolean isLicenciaConducir() {
		return licenciaConducir;
	}

	public void setLicenciaConducir(boolean licenciaConducir) {
		this.licenciaConducir = licenciaConducir;
	}

	public boolean isEstadoEmpleo() {
		return estadoEmpleo;
	}

	public void setEstadoEmpleo(boolean estadoEmpleo) {
		this.estadoEmpleo = estadoEmpleo;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}
	
	
	
}
