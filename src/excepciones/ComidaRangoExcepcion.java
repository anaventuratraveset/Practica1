package excepciones;

import javax.swing.*;

public class ComidaRangoExcepcion extends Exception {
    /**
     * Excepción que se lanzará en la clase gestionLab si la cantidad de comida introducida por el usuario
     * no está en el rango permitido
     * (esto varía según el número de patrón que se esté siguiendo)
     * Ej: si el patrón es 1, la cantidad de comida pico ha de ser mayor que ambas cantidades iniciales y finales
     *
     * @param mensaje
     */

    public ComidaRangoExcepcion(String mensaje) {
        super(mensaje);
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
