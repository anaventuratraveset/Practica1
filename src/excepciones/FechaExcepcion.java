package excepciones;
import javax.swing.*;

/**
 * @author Ana Ventura-Traveset
 */

public class FechaExcepcion extends RuntimeException{
    /**
     * Lanza esta excepción si la fecha de finalización es anterior a la fecha de inicio
     * o si la fecha de fin es anterior a la fecha de pico (en el caso de la comida pico)
     * @param mensaje
     * */
    public FechaExcepcion(String mensaje){
        super(mensaje);
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
