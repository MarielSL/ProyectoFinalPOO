package logico;

import java.util.ArrayList;

public class Oferta {
	
	private String id;
	private Sexo sexo;
	private TipoPersona tipoCandidato;
	private String puesto;
	private int cantPuestos;
	private boolean licencia;
	private boolean dispMudar;
	private EstadoOferta estado;
	private Jornada jornada;
	private String ciudad;
	private float salario;
	private String descripPuesto;
	private int aniosExp;
	private ArrayList<SolicitudEmpleo> solicitudes;
	private Empresa empresa;
	private Modalidad modalidad;
	
	public Oferta(String id, Sexo sexo, TipoPersona tipoCandidato, String puesto, int cantPuestos, boolean licencia,
			boolean dispMudar, EstadoOferta estado, Jornada jornada, String ciudad, float salario,
			String descripPuesto, int aniosExp, Empresa empresa, Modalidad modalidad) {
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
		this.salario = salario;
		this.descripPuesto = descripPuesto;
		this.aniosExp = aniosExp;
		solicitudes = new ArrayList<>();
		this.empresa = empresa;
		this.modalidad = modalidad;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public TipoPersona getTipoCandidato() {
		return tipoCandidato;
	}

	public void setTipoCandidato(TipoPersona tipoCandidato) {
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

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getAniosExp() {
		return aniosExp;
	}

	public void setAniosExp(int aniosExp) {
		this.aniosExp = aniosExp;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}
	
	public ArrayList<Persona> topSolicitantes() {
		ArrayList<Persona> top = new ArrayList<>();
		int cantidadTop;

		if (solicitudes.size() < 3) {
			cantidadTop = solicitudes.size();
		} 
		else {
			cantidadTop = 3;
		}

		for (int ind = 0; ind < cantidadTop; ind++) { 
			Persona mejor = buscarNextTop(top);
			if (mejor != null) {
				top.add(mejor);
			}
		}
		return top;
	}

	private Persona buscarNextTop(ArrayList<Persona> elegidos) {
		Persona aux = null;
		float mayotPorcen = -1;

		for (SolicitudEmpleo solicitud : solicitudes) {

			if (!(elegidos.contains(solicitud.getCandidato()))) {
				if (solicitud.getPorcentajeCoincidencia() > mayotPorcen) {
					aux = solicitud.getCandidato();
					mayotPorcen = solicitud.getPorcentajeCoincidencia();
				}
			}
		}
		return aux;
	}
			
	

}
