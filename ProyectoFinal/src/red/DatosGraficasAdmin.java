package red;

import java.io.Serializable;
import java.util.ArrayList;

public class DatosGraficasAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    private int solicitantesEmpleados;
    private int empresasActivas;

    private ArrayList<String> nombresEmpresasTop;
    private ArrayList<Integer> ofertasPorEmpresaTop;

    private int solicitudesMes;
    private int ofertasMes;
    private int contratadosMes;

    private ArrayList<String> nombresAreasLaborales;
    private ArrayList<Integer> ofertasPorAreaLaboral;
    private ArrayList<Integer> solicitudesPorAreaLaboral;

    private int pendientes;
    private int contratados;
    private int rechazados;

    private ArrayList<String> rangosCoincidencia;
    private ArrayList<Integer> cantidadCoincidencias;

    public DatosGraficasAdmin() {
        this.nombresEmpresasTop = new ArrayList<String>();
        this.ofertasPorEmpresaTop = new ArrayList<Integer>();
        this.nombresAreasLaborales = new ArrayList<String>();
        this.ofertasPorAreaLaboral = new ArrayList<Integer>();
        this.solicitudesPorAreaLaboral = new ArrayList<Integer>();
        this.rangosCoincidencia = new ArrayList<String>();
        this.cantidadCoincidencias = new ArrayList<Integer>();
    }

    public DatosGraficasAdmin(int solicitantesEmpleados, int empresasActivas,
            ArrayList<String> nombresEmpresasTop, ArrayList<Integer> ofertasPorEmpresaTop,
            int solicitudesMes, int ofertasMes, int contratadosMes,
            ArrayList<String> nombresAreasLaborales,
            ArrayList<Integer> ofertasPorAreaLaboral,
            ArrayList<Integer> solicitudesPorAreaLaboral,
            int pendientes, int contratados, int rechazados,
            ArrayList<String> rangosCoincidencia,
            ArrayList<Integer> cantidadCoincidencias) {

        this.solicitantesEmpleados = solicitantesEmpleados;
        this.empresasActivas = empresasActivas;
        this.nombresEmpresasTop = nombresEmpresasTop;
        this.ofertasPorEmpresaTop = ofertasPorEmpresaTop;
        this.solicitudesMes = solicitudesMes;
        this.ofertasMes = ofertasMes;
        this.contratadosMes = contratadosMes;
        this.nombresAreasLaborales = nombresAreasLaborales;
        this.ofertasPorAreaLaboral = ofertasPorAreaLaboral;
        this.solicitudesPorAreaLaboral = solicitudesPorAreaLaboral;
        this.pendientes = pendientes;
        this.contratados = contratados;
        this.rechazados = rechazados;
        this.rangosCoincidencia = rangosCoincidencia;
        this.cantidadCoincidencias = cantidadCoincidencias;
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

    public int getContratadosMes() {
        return contratadosMes;
    }

    public ArrayList<String> getNombresAreasLaborales() {
        return nombresAreasLaborales;
    }

    public ArrayList<Integer> getOfertasPorAreaLaboral() {
        return ofertasPorAreaLaboral;
    }

    public ArrayList<Integer> getSolicitudesPorAreaLaboral() {
        return solicitudesPorAreaLaboral;
    }

    public int getPendientes() {
        return pendientes;
    }

    public int getContratados() {
        return contratados;
    }

    public int getRechazados() {
        return rechazados;
    }

    public ArrayList<String> getRangosCoincidencia() {
        return rangosCoincidencia;
    }

    public ArrayList<Integer> getCantidadCoincidencias() {
        return cantidadCoincidencias;
    }
}