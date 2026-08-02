package red;

import java.io.Serializable;
import java.util.ArrayList;

public class DatosGraficasAdmin implements Serializable {

    private int solicitantesEmpleados;
    private int empresasActivas;

    private ArrayList<String> nombresEmpresasTop;
    private ArrayList<Integer> ofertasPorEmpresaTop;

    private int solicitudesMes;
    private int ofertasMes;

    private int solicitudesRecibidas;
    private int solicitudesAceptadas;

    private int hombresEmpleados;
    private int mujeresEmpleadas;

    public DatosGraficasAdmin(int solicitantesEmpleados, int empresasActivas,
            ArrayList<String> nombresEmpresasTop, ArrayList<Integer> ofertasPorEmpresaTop,
            int solicitudesMes, int ofertasMes,
            int solicitudesRecibidas, int solicitudesAceptadas,
            int hombresEmpleados, int mujeresEmpleadas) {
        this.solicitantesEmpleados = solicitantesEmpleados;
        this.empresasActivas = empresasActivas;
        this.nombresEmpresasTop = nombresEmpresasTop;
        this.ofertasPorEmpresaTop = ofertasPorEmpresaTop;
        this.solicitudesMes = solicitudesMes;
        this.ofertasMes = ofertasMes;
        this.solicitudesRecibidas = solicitudesRecibidas;
        this.solicitudesAceptadas = solicitudesAceptadas;
        this.hombresEmpleados = hombresEmpleados;
        this.mujeresEmpleadas = mujeresEmpleadas;
    }

	public int getSolicitantesEmpleados() {
		return solicitantesEmpleados;
	}

	public int getEmpresasActivas() {
		return empresasActivas;
	}

	public ArrayList<String> getNombresEmpresasTop() {
		return nombresEmpresasTop;
	}

	public ArrayList<Integer> getOfertasPorEmpresaTop() {
		return ofertasPorEmpresaTop;
	}

	public int getSolicitudesMes() {
		return solicitudesMes;
	}

	public int getOfertasMes() {
		return ofertasMes;
	}

	public int getSolicitudesRecibidas() {
		return solicitudesRecibidas;
	}

	public int getSolicitudesAceptadas() {
		return solicitudesAceptadas;
	}

	public int getHombresEmpleados() {
		return hombresEmpleados;
	}

	public int getMujeresEmpleadas() {
		return mujeresEmpleadas;
	}

   
}