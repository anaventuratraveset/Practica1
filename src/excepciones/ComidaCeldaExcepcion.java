package excepciones;

import javax.swing.*;

public class ComidaCeldaExcepcion extends Exception {
    /**
     * Esta excepción se lanza en el método de eliminación de comida de la clase Celda
     * en el caso de que si la eliminación de x cantidad sobre la cantidad total de comida de la celda
     * supone tener una cantidad de comida negativa en la celda.
     * */
    public ComidaCeldaExcepcion(String mensaje) {
        super(mensaje);
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
