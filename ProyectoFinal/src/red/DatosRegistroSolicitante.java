package red;

import java.io.Serializable;
import java.time.LocalDate;
import logico.Sexo;
import logico.TipoPersona;

public class DatosRegistroSolicitante implements Serializable {

    private String cedula;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacim;
    private String telefono;
    private String direccion;
    private Sexo sexo;
    private String ciudad;
    private boolean dispMudarse;
    private boolean licenciaConducir;
    private boolean estadoEmpleo;
    private int yearsExp;
    private TipoPersona tipo;
    private String campoExtra; // carrera (Universitario) / área técnica (Técnico) / habilidades (Obrero)
    private String username;
    private String password;
    private String correo;
    private String fotoPerfil;

    public DatosRegistroSolicitante(String cedula, String nombre, String apellido, LocalDate fechaNacim,
            String telefono, String direccion, Sexo sexo, String ciudad, boolean dispMudarse,
            boolean licenciaConducir, boolean estadoEmpleo, int yearsExp, TipoPersona tipo,
            String campoExtra, String username, String password, String correo, String fotoPerfil) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacim = fechaNacim;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.dispMudarse = dispMudarse;
        this.licenciaConducir = licenciaConducir;
        this.estadoEmpleo = estadoEmpleo;
        this.yearsExp = yearsExp;
        this.tipo = tipo;
        this.campoExtra = campoExtra;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.fotoPerfil = fotoPerfil;
    }

	public String getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaNacim() {
		return fechaNacim;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public boolean isDispMudarse() {
		return dispMudarse;
	}

	public boolean isLicenciaConducir() {
		return licenciaConducir;
	}

	public boolean isEstadoEmpleo() {
		return estadoEmpleo;
	}

	public int getYearsExp() {
		return yearsExp;
	}

	public TipoPersona getTipo() {
		return tipo;
	}

	public String getCampoExtra() {
		return campoExtra;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getCorreo() {
		return correo;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}


}