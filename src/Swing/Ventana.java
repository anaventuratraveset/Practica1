package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Creo mi ventana que extiende de JFrame
 * JFrame = Ventana
 * */
 public class Ventana extends JFrame implements ActionListener {
        //Crea ventana que dice "Hola!!!"

    public Ventana() {
            setTitle("Programa manejo de Poblaciones de Bacterias");
            setSize(300, 200);

            /** para poder manejar la ventana
            *  con el ratón, es decir, cerrarla, abrirla, etc.
            *  he creado una clase manejador que extiende de WindowAdapter
            *  y ahi he creado los métodos para abrir, cerrar, minimizar, etc la ventana
            */
            addWindowListener(new manejador());

            /**
             * Creo un panel y lo añado a la ventana
             * Hago esto para ahora, dentro del panel poder añadir layouts (botones, etc)
             * */
            Container contentpane = getContentPane();
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout()); // le doy un layout al panel (en este caso BorderLayout())
            JButton botonPablo = new JButton("Pablito pulsa aqui"); //creo un botón
            botonPablo.setBounds(300, 200, 200, 100); // le doy tamaño y posición
            panel.add(botonPablo); // lo añado al panel

            JButton azul = new JButton("Azul");
            azul.addActionListener(this); // le paso this pq ventana implements ActionListener
            Dimension d = new Dimension();
            d.height = 40;
            d.width = 100;
            azul.setPreferredSize(d);
            contentpane.add(panel);
            panel.add(azul, BorderLayout.SOUTH);
            getContentPane().setBackground(Color.blue);

            JMenu menu = new JMenu();
            JMenuBar menuBar = new JMenuBar();

            menu.setText("Menu");
            // Creo las opciones del menu (items)
            JMenuItem uno = new JMenuItem("Abrir un archivo que contenga un experimento", KeyEvent.VK_N);
            JMenuItem dos = new JMenuItem("Crear un nuevo experimento", KeyEvent.VK_O);
            JMenuItem tres = new JMenuItem("Crear una población de bacterias y añadirla al experimento actual", KeyEvent.VK_S);
            JMenuItem cuatro = new JMenuItem("Visualizar los nombres de todas las poblaciones de bacterias del experimento\n" +
                    "actual", KeyEvent.VK_S);
            JMenuItem cinco = new JMenuItem("Borrar una población de bacterias del experimento actual", KeyEvent.VK_S);
            JMenuItem seis = new JMenuItem("Ver información detallada de una población de bacterias del experimento actual", KeyEvent.VK_S);
            JMenuItem siete = new JMenuItem("Realizar y visualizar la simulación correspondiente con una de las poblaciones\n" +
                    "de bacterias del experimento", KeyEvent.VK_S);
            JMenuItem ocho = new JMenuItem("Guardar", KeyEvent.VK_S);
            JMenuItem nueve = new JMenuItem("Guardar como", KeyEvent.VK_S);
            JMenuItem diez = new JMenuItem("Salir", KeyEvent.VK_S);

            //Añado los items al menu
            menu.add(uno);
            menu.add(dos);
            menu.add(tres);
            menu.add(cuatro);
            menu.add(cinco);
            menu.add(seis);
            menu.add(siete);
            menu.add(ocho);
            menu.add(nueve);
            menu.add(diez);


            // Añado el menu al menu bar
            menuBar.add(menu);
            panel.add(menuBar, BorderLayout.NORTH);
            //panel.add(menu, BorderLayout.NORTH);

    }


        public static void main(String[] args) {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        getContentPane().setBackground(Color.blue);
    }
}
