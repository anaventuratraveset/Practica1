package excepciones;

import javax.swing.*;

/**
 * @author Ana Ventura-Traveset
 *
 */
public class ComidaMaxExcepcion extends Exception {

    /**
     * Esta excepción se lanza en la clase gestionLab en el caso de la comida introducida por el usuario
     * sea superior a la cantidad máxima permitida (300000 microgramos).
     * @param mensaje
     */
    public ComidaMaxExcepcion(String mensaje) {
        super(mensaje);
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
