package red;

import java.io.Serializable;
import logico.TipoEmpresa;

public class DatosModificarEmpresa implements Serializable {

    private String nombre;
    private String rnc;
    private String direccion;
    private String telefono;
    private TipoEmpresa tipo;
    private String correo;
    private String username;
    private String password;
    private String fotoPerfil;

    public DatosModificarEmpresa(String nombre, String rnc, String direccion, String telefono,
            TipoEmpresa tipo, String correo, String username, String password, String fotoPerfil) {
        this.nombre = nombre;
        this.rnc = rnc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.correo = correo;
        this.username = username;
        this.password = password;
        this.fotoPerfil = fotoPerfil;
    }

	public String getNombre() {
		return nombre;
	}

	public String getRnc() {
		return rnc;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public TipoEmpresa getTipo() {
		return tipo;
	}

	public String getCorreo() {
		return correo;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}


}