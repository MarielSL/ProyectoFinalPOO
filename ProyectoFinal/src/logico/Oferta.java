package logico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Oferta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private Empresa empresa;
	private Modalidad modalidad;
	private LocalDate FechaPublicacion;
	private AreaLaboral areaLaboral;
	private ArrayList<DecisionCandidato> decisionesCandidatos;


	public Oferta(String id, Sexo sexo, TipoPersona tipoCandidato, String puesto, int cantPuestos, boolean licencia,
			boolean dispMudar, EstadoOferta estado, Jornada jornada, String ciudad, float salario,
			String descripPuesto, int aniosExp, Empresa empresa, Modalidad modalidad, LocalDate FechaPublicacion, AreaLaboral areaLaboral) {
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
		this.empresa = empresa;
		this.modalidad = modalidad;
		this.FechaPublicacion = FechaPublicacion;
		this.areaLaboral = areaLaboral;
		this.decisionesCandidatos = new ArrayList<>();
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

	public LocalDate getFechaPublicacion() {
		return FechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		FechaPublicacion = fechaPublicacion;
	}

	public AreaLaboral getAreaLaboral() {
		return areaLaboral;
	}

	public void setAreaLaboral(AreaLaboral areaLaboral) {
		this.areaLaboral = areaLaboral;
	}

	public ArrayList<DecisionCandidato> getDecisionesCandidatos() {
		return decisionesCandidatos;
	}

	public void setDecisionesCandidatos(ArrayList<DecisionCandidato> candidatosRechazados) {
		this.decisionesCandidatos = candidatosRechazados;
	}

	public DecisionCandidato buscarDecision(Persona candidato) {

		if (candidato == null) {
			return null;
		}

		for (DecisionCandidato decision : decisionesCandidatos) {

			if (decision.getCandidato() != null&& decision.getCandidato().getId().equals(candidato.getId())) {
				return decision;
			}
		}

		return null;
	}

	public void guardarDecision(Persona candidato, EstadoDecision estado) {

		if (candidato == null) {
			return;
		}

		DecisionCandidato decision = buscarDecision(candidato);

		if (decision == null) {
			decisionesCandidatos.add(new DecisionCandidato( candidato, estado));

		} else {
			decision.setEstado(estado);
		}
	}
	
	public int cantContratados () {
		int cant = 0;
		
		if(decisionesCandidatos == null) {
			return cant;
		}
		
		for (DecisionCandidato decisionCandidato : decisionesCandidatos) {
			if(decisionCandidato.getEstado() == EstadoDecision.CONTRATADO) {
				cant++;
			}
		}
		return cant;
	}

	


}
