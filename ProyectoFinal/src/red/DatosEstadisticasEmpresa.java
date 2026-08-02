package red;

import java.io.Serializable;

public class DatosEstadisticasEmpresa implements Serializable {

    private int ofertasActivas;
    private int candidatosCompatibles;
    private int contratadosEsteMes;

    public DatosEstadisticasEmpresa(int ofertasActivas, int candidatosCompatibles, int contratadosEsteMes) {
        this.ofertasActivas = ofertasActivas;
        this.candidatosCompatibles = candidatosCompatibles;
        this.contratadosEsteMes = contratadosEsteMes;
    }

	public int getOfertasActivas() {
		return ofertasActivas;
	}

	public int getCandidatosCompatibles() {
		return candidatosCompatibles;
	}

	public int getContratadosEsteMes() {
		return contratadosEsteMes;
	}


}