package ordenacion;
import laboratorio.Poblacion;
import java.util.Comparator;

/**
 * Clase que implementa la interfaz Comparator para ordenar poblaciones alfabéticamente.
 * @author Ana Ventura-Traveset
 */
public class OrdenacionAlfabetica implements Comparator<Poblacion> { //Comparator es una interfaz genérica. Es un ejemplo polimorfismo paramétrico.

    /**
     * Ordenación alfabética de poblaciones.
     * Se ordena por nombre de población.
     * Verifica que los objetos no sean nulos.
     * @param p1, p2
     * */
    @Override
    public int compare(Poblacion p1, Poblacion p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Uno de los objetos es nulo.");
        }
        else {
            return p1.getNombrePoblacion().compareTo(p2.getNombrePoblacion());

        }
    }
}