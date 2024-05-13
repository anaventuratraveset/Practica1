package ordenacion;

import laboratorio.Poblacion;
import java.util.Comparator;

/**
 * @author Ana Ventura-Traveset
 */
public class OrdenacionCuantitativa implements Comparator<Poblacion> {

    /**
     * Ordenación cuantitativa de poblaciones
     * Se ordena por número de bacterias iniciales. Si el número de bacterias iniciales es el mismo, se ordena por fecha de inicio.
     * Si la fecha de inicio es la misma, se ordena por nombre de población.
     * @param p1, p2
     * * */
    @Override
    public int compare(Poblacion p1, Poblacion p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Uno de los objetos es nulo.");
        } else {
            if (p1.getNumInicialBacterias() == p2.getNumInicialBacterias()) {
                if (p1.getFechaInicio().equals(p2.getFechaInicio())) {
                    return p1.getNombrePoblacion().compareTo(p2.getNombrePoblacion());
                    // compareTo() devuelve el valor 0 si ambas cadenas son lexicográficamente iguales.
                    // Si la cadena comparada es mayor lexicográficamente, entonces se devuelve el valor positivo;
                    // de lo contrario, se devuelve el valor negativo.
                } else {
                    return p1.getFechaInicio().compareTo(p2.getFechaInicio());
                }
            } else {
                int result = Integer.compare(p1.getNumInicialBacterias(), p2.getNumInicialBacterias());
                if (result > 0) {
                    System.out.println("p1 mayor que p2");
                } else if (result < 0) {
                    System.out.println("p1 menor que p2");
                }
//            if (p1.getNumInicialBacterias() < p2.getNumInicialBacterias()) {
//                return 1;
//            }
//            else {
//                return -1;
//            }
                return result;
            }
        }
    }
}