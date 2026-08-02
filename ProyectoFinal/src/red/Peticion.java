package red;

import java.io.Serializable;

public class Peticion implements Serializable {

    public enum Tipo {
        LOGIN,
        REGISTRAR_EMPRESA,
        REGISTRAR_SOLICITANTE,
        PUBLICAR_OFERTA,
        REGISTRAR_SOLICITUD,
        OBTENER_MATCH,       
        DECIDIR_CANDIDATO,
        OBTENER_ESTADISTICAS
    }

    private Tipo tipo;
    private Object datos;

    public Peticion(Tipo tipo, Object datos) {
        this.tipo = tipo;
        this.datos = datos;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Object getDatos() {
        return datos;
    }
}