package Swing;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Aqui manejo mi ventana, la abro, la cierro, la maximizo, la minimizo, la activo, la desactivo
 * */
    public class manejador extends WindowAdapter {

        public static JTextField crearJTextField(String texto) {
            JTextField jTextField = new JTextField();
            jTextField.setText(texto);
            return jTextField;
        }

    }