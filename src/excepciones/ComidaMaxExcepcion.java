package excepciones;
import javax.swing.*;

/**
 * @author Ana Ventura-Traveset
 *
 */
public class ComidaMaxExcepcion extends Exception {

    /**
     * Excepción que se lanzará si la cantidad de comida es superior a la cantidad máxima (300000)
     * @param mensaje
     */
    public ComidaMaxExcepcion(String mensaje) {
        super(mensaje);
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
