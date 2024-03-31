package ui;
import dataio.FileManager;
import static dataio.UserInput.readInt;
import static dataio.UserInput.readString;
import gestionLab.GestionLab;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import java.io.FileNotFoundException;


/**
 * @author Ana Ventura-Traveset
 *
 * Esta es la clase principal que contiene el método main.
 * Se utiliza como punto de entrada para la aplicación.
 */
public class Main {
    public static void main(String[] args)  {

        int opcion = 0;
        Experimento experimento=null;

        while (opcion != 9) {
            System.out.println("\nSelecione una opción:" +
                    "\n1. Abrir un archivo que contenga un experimento" +
                    "\n2. Crear un nuevo experimento" +
                    "\n3. Crear una población de bacterias y añadirla al experimento actual" +
                    "\n4. Visualizar los nombres de todas las poblaciones de bacterias del experimento actual" +
                    "\n5. Borrar una población de bacterias del experimento actual" +
                    "\n6. Ver información detallada de una población de bacterias del experimento actual" +
                    "\n7. Guardar" +
                    "\n8. Guardar como" +
                    "\n9. Salir");

            System.out.println("\nIntroduzca las fechas siempre en este formato \"yyyy.MM.dd\"");
            do {
                opcion = readInt("Seleccione una opción: ");
                if (opcion < 1 || opcion > 9) {
                    System.out.println("¡ Opción no valida ! ");
                }
            } while (opcion < 1 || opcion > 9);

            switch (opcion) {
                case 1: //Abrir un archivo que contenga un experimento
                        try {
                            String nombreExperimentoAbrir = readString("Escriba el nombre de su experimento: ");
                            experimento = FileManager.abrirArchivo(nombreExperimentoAbrir);
                        } catch (FileNotFoundException fnf) {
                            System.out.println("No se ha encontrado el archivo. ");
                        } catch (Exception ex) {
                            System.out.println("ERROR.");
                        }
                    break;

                case 2: //Crear un nuevo experimento
                    String nombreNuevoExperimento = readString("Escriba el nombre de su nuevo experimento: ");
                    int numPoblaciones;

                    while (true) {
                        numPoblaciones = readInt("Escriba el número de poblaciones que tiene su nuevo experimento: ");
                        if (numPoblaciones < 0) {
                            System.out.println("El número de poblaciones no puede ser negativo.");
                        } else {
                            break;
                        }
                    }
                    experimento = new Experimento(nombreNuevoExperimento);

                    for (int i = 0; i < numPoblaciones; i++) {
                            System.out.println("Poblacion " + (i+1) + ":\n");
                        try {
                            GestionLab.createPoblacion(experimento);
                        } catch (Exception ex) {
                            System.out.println("Ha ocurrido un error.");
                            ex.printStackTrace();
                        }
                    }

                    System.out.println("\nExperimento " + experimento.getNombreExperimento() + " ha sido creado correctamente.");
                    System.out.println(experimento.toString());
                    break;

                case 3: //Crear una población de bacterias y añadirla al experimento actual
                    if (experimento == null) {
                        System.out.println("No tiene ningún experimento cargado en memoria. Primero debe abrir un archivo.");
                        break;
                    }
                    try {
                        Poblacion recienCreada=GestionLab.createPoblacion(experimento);
                        System.out.println(recienCreada.toString());
                    } catch (Exception ex) {
                        System.out.println("ERROR.");
                    }
                    break;

                case 4: //Mostrar nombre poblaciones
                    if (experimento== null) {
                        System.out.println("No tiene ningún experimento cargado en memoria. Primero debe abrir un archivo.");
                    }
                    else {
                        String nombres = experimento.toStringNombres();
                        System.out.println(nombres);
                    }
                    break;

                case 5: //Borrar una población de bacterias del experimento actual
                    if (experimento== null) {
                        System.out.println("No tiene ningún experimento cargado en memoria. Primero debe abrir un archivo.");
                    }
                    else {
                        String pobDeletear = (readString("Escriba el nombre de la población que desea eliminar: "));
                        GestionLab.deletePoblacion(pobDeletear, experimento);
                        System.out.println("Su población se ha borrado correctamente.");
                    }
                    break;

                case 6: //Ver información detallada de una población de bacterias del experimento actual
                    if (experimento== null) {
                        System.out.println("No tiene ningún experimento cargado en memoria. Primero debe abrir un archivo.");
                    }
                    else {
                        String pobVerInfo = (readString("Escriba el nombre de la población que desea ver la info: "));
                        try {
                            Poblacion poblacionEncontrada = GestionLab.buscarPoblacion(pobVerInfo, experimento);
                            System.out.println(poblacionEncontrada.toString());
                        } catch (Exception e) {
                            System.out.println("La población no existe en este experimento.");
                        }
                    }
                    break;

                case 7: //Guardar (se supone que para usar esta opción previamente hemos abierto un archivo)

                    if (experimento== null) {
                        System.out.println("No tiene ningún experimento cargado en memoria. Primero debe abrir un archivo.");
                    }else{
                        boolean guardado = FileManager.guardarArchivo(experimento.getNombreExperimento(), experimento);
                        if (guardado) {
                            System.out.println("Experimento guardado correctamente.");
                        } else {
                            System.out.println("Fallo al guardar el experimento.");
                        }
                    }
                    break;

                case 8: //Guardar como
                    if (experimento== null) {
                        System.out.println("No tiene ningún experimento cargado en memoria. Primero debe abrir un archivo.");
                    }else {
                        String nombreExperimento = readString("Introduzca el nombre del experimento que desea guardar: ");
                        experimento.setNombreExperimento(nombreExperimento);
                        boolean guardadoComo = FileManager.guardarArchivo(nombreExperimento, experimento);
                        if (guardadoComo) {
                            System.out.println("Experimento guardado correctamente.");
                        } else {
                            System.out.println("Fallo al guardar el experimento.");
                        }
                    }
                    break;

                case 9: //Salir del programa
                    System.out.println("Salió del programa.");
                    System.out.println("Adiós y muchas gracias.");
                    break;
            }

        }
    }
}
