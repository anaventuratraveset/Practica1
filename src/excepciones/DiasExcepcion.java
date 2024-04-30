package excepciones;

/**
 * @author Ana Ventura-Traveset
 */
public class DiasExcepcion extends Exception { //sin el extends Exception no se puede lanzar la excepción, no se puede hacer el throw ni aplicar el super(mensaje)

    /**
     * Excepción que se lanzará si el número de días de incremento es superior a la duración
     * @param mensaje
     */
    public DiasExcepcion(String mensaje) {
        super(mensaje);
    }
}
