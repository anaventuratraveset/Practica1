package ordenacion;

import laboratorio.Poblacion;
import java.util.Comparator;

public class OrdenacionCronologica implements Comparator<Poblacion> { //al implementar la interfaz Comparator, hay que implementar sus métodos

    @Override
    public int compare(Poblacion p1, Poblacion p2) {
            return p1.getFechaInicio().compareTo(p2.getFechaInicio());

    }

}
