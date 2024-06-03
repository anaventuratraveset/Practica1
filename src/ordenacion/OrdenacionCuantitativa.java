package ordenacion;

import laboratorio.Poblacion;
import java.util.Comparator;

/**
 * Clase que implementa la interfaz Comparator para ordenar poblaciones por número de bacterias iniciales.
 * @author Ana Ventura-Traveset
 */
public class OrdenacionCuantitativa implements Comparator<Poblacion> {

    /**
     * Ordenación cuantitativa de poblaciones
     * Se ordena por número de bacterias iniciales (de menos a más).
     * Verifica que los objetos no sean nulos.
     * @param p1, p2
     * * */
    @Override
    public int compare(Poblacion p1, Poblacion p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Uno de los objetos es nulo.");
        } else {
                int result = Integer.compare(p1.getNumInicialBacterias(), p2.getNumInicialBacterias());
                // Si p1 es mayor que p2, result será mayor que 0
                // Si p1 es menor que p2, result será menor que 0
                return result;
        }
    }
}