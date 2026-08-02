package red;

import java.io.Serializable;

public class DatosObtenerMatch implements Serializable {

    private String ofertaId;

    public DatosObtenerMatch(String ofertaId) {
        this.ofertaId = ofertaId;
    }

    public String getOfertaId() {
        return ofertaId;
    }
}