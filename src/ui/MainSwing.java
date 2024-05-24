package ui;

import dataio.FileManager;
import gestionLab.GestionLab;
import gestionLab.GestionSimulacion;
import laboratorio.Experimento;
import laboratorio.Plato;
import laboratorio.Poblacion;

import javax.swing.*; // para importar los componentes de Swing (de nuestra interfaz gráfica, ej el JOptionPane)
import java.awt.*;
import java.awt.event.*; // para importar los JBottons, ActionEvent, ActionListener, etc
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import static dataio.FileManager.abrirArchivo;
import static dataio.FileManager.guardarArchivo;
import static dataio.UserInput.readInt;

/**
 * Esta clase es para tener la ventana de mi main
 * Componentes Swing:
 * JFrame: ventana
 * JMenuBar: barra de menu
 * JMenu: menu
 * JMenuItem: items del menu
 * JTextField: para que el usuario pueda escribir
 * JLabel: etiqueta para mostrar información (ej: al lado de un JTextField, para indicar que escribir en él)
 * JPanel: para añadir los componentes a la ventana
 * JButton: para que el usuario pueda hacer click
 *
 * @autor Ana Ventura-Traveset
 * */
public class MainSwing extends JFrame /*implements ActionListener*/ {

    public static void main(String[] args) {
        MainSwing mainSwing = new MainSwing();
        mainSwing.setVisible(true);

        Experimento experimento;

        JFrame ventana = new JFrame("Aplicación para gestionar cultivos de bacterias"); // JFrame es una ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null); // para que esté en el centro
        ventana.setSize(700, 300);

        JMenu menu = new JMenu();
        JMenuBar menuBar = new JMenuBar();

        menu.setText("Menu");
        // Creo las opciones del menu (items)
        JMenuItem abrirArchivo = new JMenuItem("Abrir un archivo que contenga un experimento");
        JMenuItem crearExperimento = new JMenuItem("Crear un nuevo experimento");
        JMenuItem crearPoblacion = new JMenuItem("Crear una población de bacterias y añadirla al experimento actual");
        JMenuItem visualizarNombres = new JMenuItem("Visualizar los nombres de todas las poblaciones de bacterias del experiment actual");
        JMenuItem borrarPoblacion = new JMenuItem("Borrar una población de bacterias del experimento actual");
        JMenuItem infoPoblacion = new JMenuItem("Ver información detallada de una población de bacterias del experimento actual");
        JMenuItem simulacionMontecarlo = new JMenuItem("Realizar y visualizar la simulación correspondiente con una de las poblaciones\n" +
                "de bacterias del experimento");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem guardarComo = new JMenuItem("Guardar como");
        JMenuItem salir = new JMenuItem("Salir");

        /**
         * Estas cosas se llaman lambda body lo de abrirArchivo.addActionListener(e -> { ... });
         * Abre el archivo que contenga el experimento
         * Me muestra la info pero no sé si me abre el archivo y lo guarda en memoria !
         * */
        abrirArchivo.addActionListener(new ActionListener() { // cuando creo un objeto de tipo ActionListener, estoy creando una clase interna anonima que implementa la interfaz ActionListener
            // esto me permite definir actionPerformed
            // lo del ActionListener es para que cuando haga click en el item del menu, se ejecute lo que hay dentro de actionPerformed
            Experimento experimento ;

            public void actionPerformed(ActionEvent e) { // actionPerformed es la acción
                try {
                    JFileChooser fileChooser = new JFileChooser(); // para abrir el archivo
                    String nombreExperimentoAbrir = JOptionPane.showInputDialog("Escriba el nombre del experimento a abrir:");
                    int resultado = fileChooser.showOpenDialog(null);
                    if (resultado == JFileChooser.APPROVE_OPTION ) {
                        File file = fileChooser.getSelectedFile();
                        if (nombreExperimentoAbrir == file.getName()){
                        experimento = abrirArchivo(file.getName());
                        JOptionPane.showMessageDialog(null, experimento.toString());
                        }
                    }

                    // en JOptionPane.showMessageDialog(), se abre una ventana con un JTextField para escribir dentro (sólo puede ser String)
                    // sirve para pedirle info al usuario, enseñándole el mensaje (petición)
                } catch (FileNotFoundException fnf) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR.");
                }
            }
        });

       /**
        * Crea un experimento nuevo
        * */
        crearExperimento.addActionListener(new ActionListener() {
            Experimento experimento;
            public void actionPerformed(ActionEvent e) {
                String nombreNuevoExperimento = JOptionPane.showInputDialog("Escriba el nombre del nuevo experimento:");
                int numPoblaciones;
                while (true) {
                    numPoblaciones = Integer.parseInt((JOptionPane.showInputDialog("Escriba el número de poblaciones:"))); // Integer.parseInt() para transformar el String en int
                    // aqui se ralla porque no es un String sino un int
                    if (numPoblaciones < 0) {
                        JOptionPane.showInputDialog("El número de poblaciones no puede ser negativo.");
                    } else if (numPoblaciones == 0) {
                        JOptionPane.showInputDialog("No se ha creado ninguna población.");
                    } else {
                        break;
                    }
                }
                experimento = new Experimento(nombreNuevoExperimento);
                for (int i = 0; i < numPoblaciones; i++) {
                    JOptionPane.showMessageDialog(null, "Poblacion " + (i+1) + ":\n");
                    // showMessageDialog() solo enseña un mensaje, sin pedir al usuario que escriba nada
                    try {
                        GestionLab.createPoblacion(experimento);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "No se ha podido crear el experimento.");
                        ex.printStackTrace();
                    }
                }
                if (numPoblaciones > 1) {
                    GestionLab.ordenarPoblaciones(experimento);
                }
                if (experimento != null) { // si el experimento se ha creado
                    JOptionPane.showMessageDialog(null, "Experimento " + experimento.getNombreExperimento() + " ha sido creado correctamente.");
                }
                System.out.println(experimento.toString());
            }
        });

//        private void openExperiment() {
//            JFileChooser fileChooser = new JFileChooser();
//            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//                // el this non funciona pq no le digo a mi clase que implements ActionListener
//                File file = fileChooser.getSelectedFile();
//                // Load experiment data from file
//
//            }
        /**
         *
         * NO funciona
         * Crea una población de bacterias y la añade al experimento actual
         * TENGO QUE VER COMO HACER PARA QUE EL EXPERIMENTO UNA VEZ ABIERTO, NO ME VUELVA A PEDIR SU NOMBRE
         * */
        crearPoblacion.addActionListener(new ActionListener() {
            Experimento experimento;
            Poblacion poblacion;
            public void actionPerformed(ActionEvent e) {
                String nombreExperimento = JOptionPane.showInputDialog("Escriba el nombre del experimento al que añadir la población:");
                try {
                    experimento = abrirArchivo(nombreExperimento);
                    JOptionPane.showMessageDialog(null, experimento.toString());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    poblacion = GestionLab.createPoblacion(experimento);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error.");
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Población creada correctamente.");
                JOptionPane.showMessageDialog(null, poblacion.toString());
            }
        });

        /**
         * Visualiza el nombre de cada población de bacterias del experimento actual
         * */
        visualizarNombres.addActionListener(new ActionListener() {
            Experimento experimento;
            public void actionPerformed(ActionEvent e) {
                String nombreExperimento = JOptionPane.showInputDialog("Escriba el nombre del experimento a visualizar:");
                try {
                    experimento = abrirArchivo(nombreExperimento);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, experimento.toStringNombres());
            }
        });

        /**
         * Borra poblacion de bacterias del experimento actual
         * TENGO Q VER COMO HACER PARA Q EL EXP SEA EL Q YA ESTÁ ABIERTO
         * */
        borrarPoblacion.addActionListener(new ActionListener() {
            Experimento experimento;
            public void actionPerformed(ActionEvent e) {
                String nombreExperimento = JOptionPane.showInputDialog("Escriba el nombre del experimento al que borrar la población:");
                try {
                    experimento = abrirArchivo(nombreExperimento);
                    JOptionPane.showMessageDialog(null, experimento.toString());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                String nombrePoblacion = JOptionPane.showInputDialog("Escriba el nombre de la población a borrar:");
                GestionLab.borrarPoblacion(nombrePoblacion, experimento);
                JOptionPane.showMessageDialog(null, "Población borrada correctamente.");
            }
        });

        /**
         * Muestra información de una población en concreto del experimento actual
         * TENGO Q VER COMO HACER PARA Q EL EXP SEA EL Q YA ESTÁ ABIERTO
         * */
        infoPoblacion.addActionListener(new ActionListener() {
            Experimento experimento;
            public void actionPerformed(ActionEvent e) {
                String nombreExperimento = JOptionPane.showInputDialog("Escriba el nombre del experimento que contiene la población que desea ver la información:");
                try {
                    experimento = abrirArchivo(nombreExperimento);
                    JOptionPane.showMessageDialog(null, experimento.toString());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                String nombrePoblacion = JOptionPane.showInputDialog("Escriba el nombre de la población que desea ver la información:");
                try {
                    Poblacion poblacionEncontrada = GestionLab.buscarPoblacion(nombrePoblacion, experimento);
                    JOptionPane.showMessageDialog(null, poblacionEncontrada.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la población.");
                }
            }
        });

        /**
         * Realiza y visualiza una simulación de Montecarlo de una población de bacterias del experimento actual
         * TENGO Q VER COMO HACER PARA Q EL EXP SEA EL Q YA ESTÁ ABIERTO
         * */
        simulacionMontecarlo.addActionListener(new ActionListener() {
            Experimento experimento;
            public void actionPerformed(ActionEvent e) {
                String nombreExperimento = JOptionPane.showInputDialog("Escriba el nombre del experimento que contiene la población que desea simular:");
                try {
                    experimento = abrirArchivo(nombreExperimento);
                    JOptionPane.showMessageDialog(null, experimento.toString());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                String nombrePoblacion = JOptionPane.showInputDialog("Escriba el nombre de la población a simular:");
                try {
                    Poblacion poblacionSimulada = GestionLab.buscarPoblacion(nombrePoblacion, experimento);
                    JOptionPane.showMessageDialog(null, poblacionSimulada.toString());
                    Plato platoCultivo = new Plato(poblacionSimulada.getNumInicialBacterias(), poblacionSimulada.getDosisComidaDiaria()[0]); // aqui inicializo el plato
                    GestionSimulacion gestionSimulacion = new GestionSimulacion(); //tengo que crearlo pq montecarlo() NO es static
                    gestionSimulacion.monteCarlo(poblacionSimulada, platoCultivo);
                    JOptionPane.showMessageDialog(null, "Simulación realizada correctamente.");
                    //JOptionPane.showMessageDialog(null, gestionSimulacion.monteCarlo(poblacionSimulada, platoCultivo), "Resultado de la simulación", JOptionPane.INFORMATION_MESSAGE);
                    // algo asi: JMenuBar.add(label);
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la población.");
                }
            }
        });

        /**
         * Guarda el experimento actual
         * */
        guardar.addActionListener(new ActionListener() {
            Experimento experimento;
            public void actionPerformed(ActionEvent e) {
                String nombreExperimento = JOptionPane.showInputDialog("Escriba el nombre del experimento a guardar:");
//                quito esto de abrir el archivo pq lo q quiero es guardarlo => aún no lo tengo
//                try {
//                    // aqui falla porque le pido que me abra un archivo que aún no ha sido guardado !!!
//                    experimento = abrirArchivo(nombreExperimento);
//                    JOptionPane.showMessageDialog(null, experimento.toString());
//                } catch (FileNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                }
                boolean guardar = guardarArchivo(experimento.getNombreExperimento(), experimento);
                if (guardar) {
                    JOptionPane.showMessageDialog(null, "Archivo guardado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha podido guardar el archivo.");
                }
            }
        });

        /**
         * Guarda como el experimento actual
         * */
        guardarComo.addActionListener(new ActionListener() {
            Experimento experimento;
            public void actionPerformed(ActionEvent e) {
                String nombreExperimento = JOptionPane.showInputDialog("Escriba el nombre del experimento a guardar:");
                try {
                    experimento = abrirArchivo(nombreExperimento);
                    JOptionPane.showMessageDialog(null, experimento.toString());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                experimento.setNombreExperimento(nombreExperimento);
                boolean guardar = guardarArchivo(experimento.getNombreExperimento(), experimento);
                if (guardar) {
                    JOptionPane.showMessageDialog(null, "Archivo guardado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha podido guardar el archivo.");
                }
            }
        });

        /**
         * Sale del programa
         * */
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //Añado los items al menu
        menu.add(abrirArchivo);
        menu.add(crearExperimento);
        menu.add(crearPoblacion);
        menu.add(visualizarNombres);
        menu.add(borrarPoblacion);
        menu.add(infoPoblacion);
        menu.add(simulacionMontecarlo);
        menu.add(guardar);
        menu.add(guardarComo);
        menu.add(salir);

//        public void crearJTextField(){
//            JTextField textField = new JTextField(10);
//            textField.setText(texto);
//        }
//        ventana.add(textField);


        // Añado el menu al menu bar
        menuBar.add(menu);
        ventana.setJMenuBar(menuBar);
        ventana.setVisible(true); // hago que aparezca la ventana en la pantalla
//        panel.add(menuBar, BorderLayout.NORTH);
        //panel.add(menu, BorderLayout.NORTH);
    }
}

