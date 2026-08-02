package red;

import java.io.Serializable;
import logico.AreaLaboral;
import logico.Jornada;
import logico.Modalidad;
import logico.Sexo;
import logico.TipoPersona;

public class DatosPublicarOferta implements Serializable {

    private Sexo sexo;
    private TipoPersona tipoCandidato;
    private String puesto;
    private int cantPuestos;
    private boolean licencia;
    private boolean dispMudar;
    private Jornada jornada;
    private String ciudad;
    private float salario;
    private String descripPuesto;
    private int aniosExp;
    private Modalidad modalidad;
    private AreaLaboral areaLaboral;

    public DatosPublicarOferta(Sexo sexo, TipoPersona tipoCandidato, String puesto, int cantPuestos,
            boolean licencia, boolean dispMudar, Jornada jornada, String ciudad, float salario,
            String descripPuesto, int aniosExp, Modalidad modalidad, AreaLaboral areaLaboral) {
        this.sexo = sexo;
        this.tipoCandidato = tipoCandidato;
        this.puesto = puesto;
        this.cantPuestos = cantPuestos;
        this.licencia = licencia;
        this.dispMudar = dispMudar;
        this.jornada = jornada;
        this.ciudad = ciudad;
        this.salario = salario;
        this.descripPuesto = descripPuesto;
        this.aniosExp = aniosExp;
        this.modalidad = modalidad;
        this.areaLaboral = areaLaboral;
    }

	public Sexo getSexo() {
		return sexo;
	}

	public TipoPersona getTipoCandidato() {
		return tipoCandidato;
	}

	public String getPuesto() {
		return puesto;
	}

	public int getCantPuestos() {
		return cantPuestos;
	}

	public boolean isLicencia() {
		return licencia;
	}

	public boolean isDispMudar() {
		return dispMudar;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public String getCiudad() {
		return ciudad;
	}

	public float getSalario() {
		return salario;
	}

	public String getDescripPuesto() {
		return descripPuesto;
	}

	public int getAniosExp() {
		return aniosExp;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public AreaLaboral getAreaLaboral() {
		return areaLaboral;
	}


}