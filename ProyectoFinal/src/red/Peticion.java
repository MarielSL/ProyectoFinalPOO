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
        OBTENER_ESTADISTICAS,
        OBTENER_OFERTAS,             
        OBTENER_OFERTAS_EMPRESA,     
        OBTENER_ESTADISTICAS_CANDIDATO, 
        OBTENER_ESTADISTICAS_EMPRESA,
        OBTENER_TODAS_EMPRESAS,      
        OBTENER_TODOS_USUARIOS,      
        OBTENER_DASHBOARD_ADMIN,
        OBTENER_TODOS_CANDIDATOS,
        OBTENER_MEJOR_MATCH_EMPRESA,
        MODIFICAR_EMPRESA,     
        MODIFICAR_SOLICITANTE,  
        MODIFICAR_SOLICITUD,
        CREAR_RESPALDO,
        OBTENER_GRAFICAS_EMPRESA,
        OBTENER_GRAFICAS_ADMIN
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