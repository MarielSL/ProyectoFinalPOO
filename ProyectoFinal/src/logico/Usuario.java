package logico;

public class Usuario {
	
	private String Id;
	private String username;
	private String password;
	private String correo;
	private Empresa empresa;
	private Persona persona;
	private TipoUser tipoUser;
	
	
	public Usuario(String Id,String username, String password, String correo, Empresa empresa, Persona persona,
			TipoUser tipoUser) {
		super();
		this.Id = Id;
		this.username = username;
		this.password = password;
		this.correo = correo;
		this.empresa = empresa;
		this.persona = persona;
		this.tipoUser = tipoUser;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public TipoUser getTipoUser() {
		return tipoUser;
	}


	public void setTipoUser(TipoUser tipoUser) {
		this.tipoUser = tipoUser;
	}

	public String getId() {
		return Id;
	}
	
	

}
