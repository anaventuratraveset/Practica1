package excepciones;
import javax.swing.*;

/**
 * @author Ana Ventura-Traveset
 *
 */
public class ComidaMinExcepcion extends Exception {

    /**
     * Excepción que se lanzará en la clase gestionLab si la cantidad de comida introducida por el usuario es inferior
     * a la cantidad mínima permitida
     * (yo he establecido que sean 400 microgramos porque así en caso de hacer una simulación de Montecarlo,
     * siempre va a haber como mínimo una cantidad de 1 microgramo por celda a la hora de inicializar el plato de
     * cultivo)
     * @param mensaje
     */
    public ComidaMinExcepcion(String mensaje) {
        super(mensaje);
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
