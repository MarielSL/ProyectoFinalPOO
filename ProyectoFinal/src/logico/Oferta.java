package logico;

import java.util.ArrayList;

public class Oferta {
	
	private String id;
	private Sexo sexo;
	private Persona tipoCandidato;
	private String puesto;
	private int cantPuestos;
	private boolean licencia;
	private boolean dispMudar;
	private EstadoOferta estado;
	private String jornada;
	private String ciudad;
	private float rangoOferta;
	private String descripPuesto;
	private ArrayList<SolicitudEmpleo> solicitudes;
	private Empresa empresa;
	
	public Oferta(String id, Sexo sexo, Persona tipoCandidato, String puesto, int cantPuestos, boolean licencia,
			boolean dispMudar, EstadoOferta estado, String jornada, String ciudad, float rangoOferta,
			String descripPuesto, ArrayList<SolicitudEmpleo> solicitudes, Empresa empresa) {
		super();
		this.id = id;
		this.sexo = sexo;
		this.tipoCandidato = tipoCandidato;
		this.puesto = puesto;
		this.cantPuestos = cantPuestos;
		this.licencia = licencia;
		this.dispMudar = dispMudar;
		this.estado = estado;
		this.jornada = jornada;
		this.ciudad = ciudad;
		this.rangoOferta = rangoOferta;
		this.descripPuesto = descripPuesto;
		this.solicitudes = solicitudes;
		this.empresa = empresa;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Persona getTipoCandidato() {
		return tipoCandidato;
	}

	public void setTipoCandidato(Persona tipoCandidato) {
		this.tipoCandidato = tipoCandidato;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public int getCantPuestos() {
		return cantPuestos;
	}

	public void setCantPuestos(int cantPuestos) {
		this.cantPuestos = cantPuestos;
	}

	public boolean isLicencia() {
		return licencia;
	}

	public void setLicencia(boolean licencia) {
		this.licencia = licencia;
	}

	public boolean isDispMudar() {
		return dispMudar;
	}

	public void setDispMudar(boolean dispMudar) {
		this.dispMudar = dispMudar;
	}

	public EstadoOferta getEstado() {
		return estado;
	}

	public void setEstado(EstadoOferta estado) {
		this.estado = estado;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public float getRangoOferta() {
		return rangoOferta;
	}

	public void setRangoOferta(float rangoOferta) {
		this.rangoOferta = rangoOferta;
	}

	public String getDescripPuesto() {
		return descripPuesto;
	}

	public void setDescripPuesto(String descripPuesto) {
		this.descripPuesto = descripPuesto;
	}

	public ArrayList<SolicitudEmpleo> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(ArrayList<SolicitudEmpleo> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getId() {
		return id;
	}
	
	
	
	

}
