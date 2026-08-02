package red;

import java.io.Serializable;

public class Respuesta implements Serializable {

    private boolean exito;
    private Object datos;

    public Respuesta(boolean exito, Object datos) {
        this.exito = exito;
        this.datos = datos;
    }

    public boolean isExito() {
        return exito;
    }

    public Object getDatos() {
        return datos;
    }
}