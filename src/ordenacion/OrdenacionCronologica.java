package ordenacion;

import laboratorio.Poblacion;
import java.util.Comparator;

/**
 * @author Ana Ventura-Traveset
 */
public class OrdenacionCronologica implements Comparator<Poblacion> { //al implementar la interfaz Comparator, hay que implementar su método compareTo()

    /**
     * Ordenación por orden cronológico. Si la fecha de inicio es la misma, se ordena por número de bacterias iniciales.
     * Si el número de bacterias iniciales es el mismo, se ordena por nombre de población.
     * */
    @Override
    public int compare(Poblacion p1, Poblacion p2) {
            if (p1.getFechaInicio().equals(p2.getFechaInicio())) {
                if (p1.getNumInicialBacterias() == p2.getNumInicialBacterias()) {
                    return p1.getNombrePoblacion().compareTo(p2.getNombrePoblacion());
                } else {
                    if (p1.getNumInicialBacterias() < p2.getNumInicialBacterias()) {
                        return 1;
                    }
                    else {
                        return -1;
                    }                }
            } else {
                return p1.getFechaInicio().compareTo(p2.getFechaInicio());
            }
    }

}
