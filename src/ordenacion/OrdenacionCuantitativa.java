package ordenacion;

import laboratorio.Poblacion;
import java.util.Comparator;

public class OrdenacionCuantitativa implements Comparator<Poblacion> {
    @Override
    public int compare(Poblacion p1, Poblacion p2) {
        if (p1.getNumInicialBacterias() > p2.getNumInicialBacterias()) {
            return 1;
        } else if (p1.getNumInicialBacterias() < p2.getNumInicialBacterias()) {
            return -1;
        } else {
            return 0;
        }
    }
}
