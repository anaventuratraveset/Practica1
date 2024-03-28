package ui;
import dataio.FileManager;
import dataio.*;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import java.lang.NullPointerException;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static dataio.UserInput.readInt;
import static dataio.UserInput.readString;
import static gestionLab.GestionLab.*;

public class Main {

    public static void main(String[] args) {

        //el main nunca lanza excepciones pq es el último

        int opcion = 0;
        FileManager utilidadesFile = new FileManager();
        Experimento experimento = new Experimento();
        System.out.println(experimento.toString());


        //FileManager utilidadesFile = new FileManager();
        while (opcion != 9) {
            System.out.println("Selecione una opción:" +
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
                    while (true) {
                        try {
                            String nombreExperimentoAbrir = readString("Escriba el nombre de su experimento: ");
                            experimento = utilidadesFile.abrirArchivo(nombreExperimentoAbrir);
                            break;
                        } catch (FileNotFoundException fnf) {
                            System.out.println("No se ha encontrado el archivo. ");
                        } catch (Exception ex) {
                            System.out.println("ERROR.");
                        }
                    }
                    break;

                case 2: //Crear un nuevo experimento
                    String nombreNuevoExperimento = readString("Escriba el nombre de su nuevo experimento: ");
                    int numPoblaciones;

                    while (true) {
                        numPoblaciones = readInt("Escriba el número de poblaciones que tiene su nuevo experimento: ");
                        if (numPoblaciones < 0) {
                            System.out.println("El número de días no puede ser negativo.");
                        } else {
                            break;
                        }
                    }
                    experimento = new Experimento(nombreNuevoExperimento, numPoblaciones);

                    for (int i = 0; i < numPoblaciones; i++) {
                        try {
                            int numPoblacion = i + 1;
                            System.out.println("Poblacion " + numPoblacion + ":\n");
                            createPoblacion(experimento);
                        } catch (Exception ex) {
                            System.out.println("Ha ocurrido un error.");
                        }
                    }

                    System.out.println(experimento.toString());
                    System.out.println("Experimento " + experimento.getNombreExperimento() + " ha sido creado correctamente.");

                    break;

                case 3: //Crear una población de bacterias y añadirla al experimento actual
                    if (experimento.getNombreExperimento() == null) {
                        System.out.println("No tiene ningún experimento cargado en memoria.");
                        break;
                    }
                    try {
                        Poblacion recienCreada=createPoblacion(experimento);
                        addPoblacion(recienCreada,experimento);
                        System.out.println(experimento.toString());
                    } catch (Exception ex) {
                        System.out.println("ERROR.");
                    }
                    break;

                case 4: //Mostrar nombre poblaciones
                    boolean hecho = false;
                    do {
                        if (experimento.getNombreExperimento() == null) {
                            System.out.println("No tiene ningún experimento cargado en memoria.");
                            break;
                        } else {
                            hecho = true;
                        }
                    } while (hecho == false);
                    System.out.println(experimento.toStringNombres());
                    break;

                case 5: //Borrar una población de bacterias del experimento actual
                    if (experimento.getNombreExperimento() == null) {
                        System.out.println("No tiene ningún experimento cargado en memoria.");
                        break;
                    }
                    String pobDeletear = (readString("Escriba el nombre de la población que desea eliminar: "));
                    deletePoblacion(pobDeletear,experimento);
                    break;

                case 6: //Ver información detallada de una población de bacterias del experimento actual
                    if (experimento.getNombreExperimento() == null) {
                        System.out.println("No tiene ningún experimento cargado en memoria");
                        break;
                    }
                    String pobVerInfo = (readString("Escriba el nombre de la población que desea ver la info: "));

                    try {
                        Poblacion poblacionEncontrada = buscarPoblacion(pobVerInfo, experimento);
                        System.out.println(poblacionEncontrada.toString());
                    } catch (Exception e) {
                        System.out.println("La población no existe en este experimento.");
                    }

                    break;

                case 7: //Guardar (se supone que para usar esta opción previamente hemos abierto un archivo)

                    boolean guardado = utilidadesFile.guardarArchivo(experimento.getNombreExperimento(), experimento);

                    if (guardado) {
                        System.out.println("Experimento guardado correctamente.");
                    } else {
                        System.out.println("Fallo al guardar el experimento.");
                    }

                    break;

                case 8: //Guardar como
                    String nombreExp = readString("Introduzca el nombre del experimento que desea guardar: ");
                    boolean guardadoComo = utilidadesFile.guardarArchivo(nombreExp, experimento);
                    //experimento.setNombre(nombreExperimento);
                    if (guardadoComo) {
                        System.out.println("Experimento guardado correctamente.");
                    } else {
                        System.out.println("Fallo al guardar el experimento.");
                    }
                    break;

                case 9:
                    System.out.println("Salió del programa.");
                    System.out.println("Adiós y muchas gracias.");
                    break;
            }

        }


    }
}
