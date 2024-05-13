package ordenacion;
import laboratorio.Poblacion;
import java.util.Comparator;

/**
 * @author Ana Ventura-Traveset
 */
public class OrdenacionAlfabetica implements Comparator<Poblacion> { //Comparator es una interfaz genérica. Es un ejemplo polimorfismo paramétrico.

    /**
     * Ordenación alfabética de poblaciones. Si tienen el mismo nombre, se ordenan por fecha de inicio.
     * Si la fecha de inicio es la misma, se ordenan por número de bacterias iniciales.
     * */
    @Override
    public int compare(Poblacion p1, Poblacion p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Uno de los objetos es nulo.");
        }
        else {
            if (p1.getNombrePoblacion().equals(p2.getNombrePoblacion())) {
                if (p1.getFechaInicio().equals(p2.getFechaInicio())) {
                    if (p1.getNumInicialBacterias() < p2.getNumInicialBacterias()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return p1.getFechaInicio().compareTo(p2.getFechaInicio());
                }
            } else {
                return p1.getNombrePoblacion().compareTo(p2.getNombrePoblacion());
            }
        }
    }



    //otras maneras más pros de hacer lo mismo
//    @Override
//    public int compare(Poblacion p1, Poblacion p2) {
//        if (p1.getNombrePoblacion().equals(p2.getNombrePoblacion())) {
//            if (p1.getFechaInicio().equals(p2.getFechaInicio())) {
//                if (p1.getNumInicialBacterias() < p2.getNumInicialBacterias()) {
//                    return 1;
//                }
//                return -1;
//            }
//            return p1.getFechaInicio().compareTo(p2.getFechaInicio());
//        }
//        return p1.getNombrePoblacion().compareTo(p2.getNombrePoblacion());
//    }
//    @Override
//    public int compare(Poblacion p1, Poblacion p2) {
//        if (!p1.getNombrePoblacion().equals(p2.getNombrePoblacion()))     return p1.getNombrePoblacion().compareTo(p2.getNombrePoblacion());
//        if (!p1.getFechaInicio().equals(p2.getFechaInicio()))             return p1.getFechaInicio().compareTo(p2.getFechaInicio());
//        if (!(p1.getNumInicialBacterias() < p2.getNumInicialBacterias())) return -1;
//        return 1;
//    }
}