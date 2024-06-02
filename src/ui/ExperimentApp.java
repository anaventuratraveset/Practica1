package ui;

import gestion.GestionLab;
import gestion.GestionSimulacion;
import laboratorio.Experimento;
import laboratorio.Plato;
import laboratorio.Poblacion;

import javax.swing.*; // Para importar los componentes de Swing (de nuestra interfaz gráfica, ej. el JOptionPane)
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static dataio.FileManager.abrirArchivo;
import static dataio.FileManager.guardarArchivo;

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
 * @author Ana Ventura-Traveset
 */


public class ExperimentApp extends JFrame {
    Experimento experimento;
    public ExperimentApp() {

        setTitle("Aplicación para gestionar cultivos de bacterias"); // JFrame es una ventana
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // para que esté en el centro
        setUpMenu();
    }

    private void setUpMenu() {
        // Menú
        JMenu menu = new JMenu();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

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

        // las cosas que necesito que haya mi fileMenu (o debería poner todos?)
        fileMenu.add(abrirArchivo);
        fileMenu.add(crearExperimento);
        fileMenu.add(guardar);
        fileMenu.add(guardarComo);

        // Aqui crearía un panel, del cultivo y lo añadiria a mi ventana haciendo simplemente add(panelCultivo)

        // Voy a añadirle a cada item un evento
        abrirArchivo.addActionListener(e -> abroArchivo());
        crearExperimento.addActionListener(e -> creoExperiment());
        crearPoblacion.addActionListener(e -> creoPoblacion());
        visualizarNombres.addActionListener(e -> visualizoNombres());
        borrarPoblacion.addActionListener(e -> borroPoblacion());
        infoPoblacion.addActionListener(e -> muestroInfoPob());
        simulacionMontecarlo.addActionListener(e -> simuloMontecarlo());
        guardar.addActionListener(e -> guardoExperimento());
        guardarComo.addActionListener(e -> guardoComoExperimento());
        salir.addActionListener(e -> salgo());

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

        // Añado el menu al menu bar
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void abroArchivo() {
        try {
            JFileChooser fileChooser = new JFileChooser(); // para abrir el archivo
            // Pedirle el nombre del experimento ya no es necesario porque el nombre del experimento ya está en el archivo
            int resultado = fileChooser.showOpenDialog(null); // para que me lo abra en mi ventana tengo q pasarle la ventana como param
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String nombreExperimento = file.getName().substring(0, file.getName().length() - 4); // para quitarle el .txt
                experimento = abrirArchivo(nombreExperimento);
                if(experimento == null) {
                    if (nombreExperimento.contains(".txt")) {
                        JOptionPane.showMessageDialog(null, "El nombre del archivo no existe.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El nombre del archivo no existe o es incorrecto. (Pruebe añadiendo .txt al final)");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Has seleccionado el archivo: " + file.getName());
                }
                JOptionPane.showMessageDialog(null, experimento.toString());
            }
            // en JOptionPane.showMessageDialog(), se abre una ventana con un JTextField para escribir dentro (solo puede ser String)
            // sirve para pedirle info al usuario, enseñándole el mensaje (petición)
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo.");
        } catch (Exception ignored) {
        }
    }

    // Crea un experimento nuevo
    private void creoExperiment() {
        String nombreNuevoExperimento = JOptionPane.showInputDialog("Escriba el nombre del nuevo experimento:");
        int numPoblaciones;
        while (true) {
            numPoblaciones = Integer.parseInt((JOptionPane.showInputDialog("Escriba el número de poblaciones:"))); // Integer.parseInt() para transformar el String en int
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
            JOptionPane.showMessageDialog(null, "Poblacion " + (i + 1) + ":\n");
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
            System.out.println(experimento.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Experimento es null, línea" + new Throwable().getStackTrace()[0].getLineNumber());
            System.out.println("Experimento es null, línea" + new Throwable().getStackTrace()[0].getLineNumber());
        }
    }

    /*
      Crea una población de bacterias y la añade al experimento actual
      */
    private void creoPoblacion() {
        Poblacion poblacion = null;
        if (experimento != null) {
            try {
                poblacion = GestionLab.createPoblacion(experimento);
                JOptionPane.showMessageDialog(null, "Población creada correctamente.");
                JOptionPane.showMessageDialog(null, poblacion.toString());
            } catch(ParseException parseException) {
                JOptionPane.showMessageDialog(null, "No se ha parseado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se ha creado correctamente la población.");
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /*
      Visualiza el nombre de cada población de bacterias del experimento actual
      */
    private void visualizoNombres() {
        if (experimento != null){
            GestionLab.ordenarPoblaciones(experimento);
            JOptionPane.showMessageDialog(null, experimento.toStringNombres());
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /*
      Borra poblacion de bacterias del experimento actual
      */
    private void borroPoblacion() {
        if (experimento != null){
            String nombrePoblacion = JOptionPane.showInputDialog("Escriba el nombre de la población a borrar:");
            GestionLab.borrarPoblacion(nombrePoblacion, experimento);
            JOptionPane.showMessageDialog(null, "Población borrada correctamente.");
        }
        else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /*
      Muestra información de una población en concreto del experimento actual
      */
    private void muestroInfoPob() {
        if (experimento != null){
            String nombrePoblacion = JOptionPane.showInputDialog("Escriba el nombre de la población que desea ver la información:");
            try {
                Poblacion poblacionEncontrada = GestionLab.buscarPoblacion(nombrePoblacion, experimento);
                JOptionPane.showMessageDialog(null, poblacionEncontrada.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado la población.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /*
      Realiza y visualiza una simulación de Montecarlo de una población de bacterias del experimento actual
      */
    private void simuloMontecarlo() {
        if (experimento != null){
            String nombrePoblacion = JOptionPane.showInputDialog("Escriba el nombre de la población a simular:");
            try {
                Poblacion poblacionSimulada = GestionLab.buscarPoblacion(nombrePoblacion, experimento);
                JOptionPane.showMessageDialog(null, poblacionSimulada.toString());
                Plato platoCultivo = new Plato(poblacionSimulada.getNumInicialBacterias(), poblacionSimulada.getDosisComidaDiaria()[0]); // aqui inicializo el plato
                GestionSimulacion gestionSimulacion = new GestionSimulacion(); //tengo que crearlo pq montecarlo() NO es static
                gestionSimulacion.monteCarlo(poblacionSimulada, platoCultivo);
                JOptionPane.showMessageDialog(null, "Simulación realizada correctamente. \nPuede visualizarla por consola siendo [nºbact, comida]." +
                        "\nMapa de colores asociado al número de bacterias vivas en cada celda:" +
                        "\n0: Blanco, 1-4: Amarillo, 5-9: Verde, 10-14: Azul, 15-19: Morado, +20: Rojo.");
                // JOptionPane.showMessageDialog(null, gestionSimulacion.monteCarlo(poblacionSimulada, platoCultivo), "Resultado de la simulación", JOptionPane.INFORMATION_MESSAGE);
                // algo asi: JMenuBar.add(label);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado la población.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /*
      Guarda el experimento actual
      */
    private void guardoExperimento() {
        if (experimento != null){
            boolean guardar = guardarArchivo(experimento.getNombreExperimento(), experimento);
            if (guardar) {
                JOptionPane.showMessageDialog(null, "Archivo guardado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido guardar el archivo.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /*
      Guarda como el experimento actual
      */
    private void guardoComoExperimento() {
        if (experimento != null){
            String nombreExperimento = JOptionPane.showInputDialog("Escriba el nombre con el que desea guardar el experimento:");
            experimento.setNombreExperimento(nombreExperimento);
            boolean guardar = guardarArchivo(experimento.getNombreExperimento(), experimento);
            if (guardar) {
                JOptionPane.showMessageDialog(null, "Archivo guardado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido guardar el archivo.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /*
      Sale del programa
      */
    private void salgo() {
        System.exit(0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        ExperimentApp ventanaSwing = new ExperimentApp();
        ventanaSwing.setVisible(true);
    }
}





