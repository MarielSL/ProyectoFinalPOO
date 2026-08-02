package red;

import java.io.Serializable;
import java.util.ArrayList;

public class DatosGraficasCandidato implements Serializable {

    private ArrayList<String> ofertasTop;
    private ArrayList<Float> porcentajesTop;
    private ArrayList<String> categoriasLaborales;
    private ArrayList<Integer> cantidadPorCategoria;

    //constructor
    public DatosGraficasCandidato(ArrayList<String> ofertasTop, ArrayList<Float> porcentajesTop,
            ArrayList<String> categoriasLaborales, ArrayList<Integer> cantidadPorCategoria) {
        this.ofertasTop = ofertasTop;
        this.porcentajesTop = porcentajesTop;
        this.categoriasLaborales = categoriasLaborales;
        this.cantidadPorCategoria = cantidadPorCategoria;
    }

    public ArrayList<String> getOfertasTop() {
        return ofertasTop;
    }

    public ArrayList<Float> getPorcentajesTop() {
        return porcentajesTop;
    }

    public ArrayList<String> getCategoriasLaborales() {
        return categoriasLaborales;
    }

    public ArrayList<Integer> getCantidadPorCategoria() {
        return cantidadPorCategoria;
    }
}