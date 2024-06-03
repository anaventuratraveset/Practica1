package ordenacion;

import laboratorio.Poblacion;
import java.util.Comparator;

/**
 * Clase que implementa la interfaz Comparator para ordenar poblaciones por fecha de inicio.
 * @author Ana Ventura-Traveset
 */
public class OrdenacionCronologica implements Comparator<Poblacion> { //al implementar la interfaz Comparator, hay que implementar su método compareTo()

    /**
     * Ordenación por orden cronológico.
     * Se ordena por fecha de inicio (de la más antigua a la más nueva)
     * Verifica que los objetos no sean nulos.
     * @param p1, p2
     * */
    @Override
    public int compare(Poblacion p1, Poblacion p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Uno de los objetos es nulo.");
        } else {
            return p1.getFechaInicio().compareTo(p2.getFechaInicio());
        }
    }
}
