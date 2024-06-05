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
import static gestion.GestionLab.createPoblacion;

/**
 * Esta clase es la encargada de la representación gráfica de la aplicación mediante Swing
 * Componentes Swing:
 * JFrame: ventana
 * JMenuBar: barra de menu
 * JMenu: menu
 * JMenuItem: items del menu
 * JFileChooser: para abrir archivos
 * JOptionPane: para mostrar mensajes
 *
 * @author Ana Ventura-Traveset
 */


public class ExperimentApp extends JFrame {
    Experimento experimento;

    /**
     * Constructor de la clase ExperimentApp
     * Configura la ventana y llama a la configuración del menú
     */
    public ExperimentApp() {
        setTitle("Aplicación para gestionar cultivos de bacterias"); // JFrame es una ventana
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // para que esté en el centro
        setUpMenu();
    }

    /**
     * Configura el menú de la aplicación
     */
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

    /**
     * Abre un archivo que contiene un experimento
     * Para ello, se utiliza un JFileChooser para que el usuario pueda seleccionar el archivo
     * y se llama al método abrirArchivo de FileManager
     * Si el archivo no existe o no lo ha podido abrir, muestra un mensaje de error.
     * Si el archivo existe, muestra un mensaje con el nombre del archivo
     * y el contenido del experimento.
     */
    private void abroArchivo() {
        try {
            JFileChooser fileChooser = new JFileChooser(); // para abrir el archivo
            int resultado = fileChooser.showOpenDialog(null); // para que me lo abra en mi ventana tengo q pasarle la ventana como param
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String nombreExperimento = file.getName().substring(0, file.getName().length() - 4); // para quitarle el .txt
                experimento = abrirArchivo(nombreExperimento);
                JOptionPane.showMessageDialog(null, experimento.toString());
            }
            // en JOptionPane.showMessageDialog(), se abre una ventana con un JTextField para escribir dentro (solo puede ser String)
            // sirve para pedirle info al usuario, enseñándole el mensaje (petición)
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "El nombre del archivo no existe o es incorrecto. (Pruebe añadiendo .txt al final)");
            ex.printStackTrace();
        }
    }

    /**
     * Inicializa un nuevo experimento
     * Le pide al usuario el nombre del experimento y el número de poblaciones
     * Para crear las poblaciones, llama al método createPoblacion de GestionLab
     * Si el número de poblaciones es 0, muestra un mensaje de error
     * Si el número de poblaciones es negativo, muestra un mensaje de error
     * Si el experimento se ha creado correctamente, muestra un mensaje con el nombre del experimento
     * y el contenido del experimento
     * Si el experimento no se ha creado correctamente, muestra un mensaje de error
     * Si el número de poblaciones es mayor que 1, pide como quiere ordenarlas (nombre, fecha, cantidad de bacterias)
     * */
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
                createPoblacion(experimento);
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

    /**
    *  Crea una población de bacterias y la añade al experimento actual
    *  Si el experimento no está abierto, muestra un mensaje de error
    */
    private void creoPoblacion() {
        Poblacion poblacion = null;
        if (experimento != null) {
            try {
                poblacion = createPoblacion(experimento);
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

    /**
     * Visualiza el nombre de cada población de bacterias del experimento actual
     * Si el experimento no está abierto, muestra un mensaje de error
     * */
    private void visualizoNombres() {
        if (experimento != null){
            GestionLab.ordenarPoblaciones(experimento);
            JOptionPane.showMessageDialog(null, experimento.toStringNombres());
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /**
     * Llama al método borrarPoblacion para borrar la población de bacterias seleccionada
     * Si el experimento no está abierto, muestra un mensaje de error
     * Si la población no existe, muestra un mensaje de error
     * */
    private void borroPoblacion() {
        if (experimento != null){
            String nombrePoblacion = JOptionPane.showInputDialog("Escriba el nombre de la población a borrar:");
            try {
                Poblacion poblacionEncontrada = GestionLab.buscarPoblacion(nombrePoblacion, experimento);
                JOptionPane.showMessageDialog(null, poblacionEncontrada.toString());
                GestionLab.borrarPoblacion(nombrePoblacion, experimento);
                JOptionPane.showMessageDialog(null, "Población borrada correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado la población.");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No hay ningún experimento abierto.");
        }
    }

    /**
     * Llama al método buscarPoblacion para encontrar la población de bacterias seleccionada
     * y muestra su información detallada mediante su método toString()
     * Si el experimento no está abierto, muestra un mensaje de error
     * Si la población no existe, muestra un mensaje de error
     * */
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

    /**
     * LLama a la simulación de montecarlo para que se realice sobre la población de bacterias seleccionada
     * Si el experimento no está abierto, muestra un mensaje de error
     * Si la población no existe, muestra un mensaje de error
     * */
    private void simuloMontecarlo() {
        if (experimento != null){
            String nombrePoblacion = JOptionPane.showInputDialog("Escriba el nombre de la población a simular:");
            try {
                Poblacion poblacionSimulada = GestionLab.buscarPoblacion(nombrePoblacion, experimento);
                JOptionPane.showMessageDialog(null, poblacionSimulada.toString());
                Plato platoCultivo = new Plato(poblacionSimulada.getNumInicialBacterias(), poblacionSimulada.getDosisComidaDiaria()[0]); // aqui inicializo el plato
                GestionSimulacion gestionSimulacion = new GestionSimulacion(); //tengo que crearlo pq montecarlo() NO es static
                gestionSimulacion.monteCarlo(poblacionSimulada, platoCultivo);
                JOptionPane.showMessageDialog(null, "Simulación realizada correctamente. \nPuede visualizarla por consola siendo |nºbact, comida| por celda." +
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

    /**
     * Llama al método guardarArchivo para guardar el experimento actual
     * Si el experimento no está abierto, muestra un mensaje de error
     * Si el archivo se ha guardado correctamente, muestra un mensaje de éxito
     * Si el archivo no se ha guardado correctamente, muestra un mensaje de error
     * */
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

    /**
     * Llama al método guardarArchivo para guardar el experimento actual con un nombre específico
     * */
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





