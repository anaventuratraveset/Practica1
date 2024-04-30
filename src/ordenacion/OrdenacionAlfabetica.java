package ordenacion;

import laboratorio.Poblacion;
import java.util.Comparator;
public class OrdenacionAlfabetica implements Comparator<Poblacion> {
    @Override
    public int compare(Poblacion p1, Poblacion p2) {
        return p1.getNombrePoblacion().compareTo(p2.getNombrePoblacion());
    }
}
