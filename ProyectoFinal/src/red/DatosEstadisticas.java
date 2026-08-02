package red;

import java.io.Serializable;

public class DatosEstadisticas implements Serializable {

    private int totalOfertas;
    private int ofertasActivas;
    private int totalSolicitantes;
    private int solicitantesDisponibles;
    private int totalPostulaciones;
    private int postulacionesPendientes;
    private int totalEmpresas;
    private int empresasActivas;

    public DatosEstadisticas(int totalOfertas, int ofertasActivas, int totalSolicitantes,
            int solicitantesDisponibles, int totalPostulaciones, int postulacionesPendientes,
            int totalEmpresas, int empresasActivas) {
        this.totalOfertas = totalOfertas;
        this.ofertasActivas = ofertasActivas;
        this.totalSolicitantes = totalSolicitantes;
        this.solicitantesDisponibles = solicitantesDisponibles;
        this.totalPostulaciones = totalPostulaciones;
        this.postulacionesPendientes = postulacionesPendientes;
        this.totalEmpresas = totalEmpresas;
        this.empresasActivas = empresasActivas;
    }

    public int getTotalOfertas() { return totalOfertas; }
    public int getOfertasActivas() { return ofertasActivas; }
    public int getTotalSolicitantes() { return totalSolicitantes; }
    public int getSolicitantesDisponibles() { return solicitantesDisponibles; }
    public int getTotalPostulaciones() { return totalPostulaciones; }
    public int getPostulacionesPendientes() { return postulacionesPendientes; }
    public int getTotalEmpresas() { return totalEmpresas; }
    public int getEmpresasActivas() { return empresasActivas; }
}