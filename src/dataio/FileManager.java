package dataio;
//Importo todo aqui, pq es dónde estoy creando los experimentos
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.Comida;
import medio.Luminosidad;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static gestionLab.GestionLab.addPoblacion;


// Crear archivo en el que meto experimento
// Abrir un archivo que contenga un experimento
// Guardar (se supone que para usar esta opción previamente hemos abierto unarchivo)
// Guardar como

public class FileManager {

    // su constructor, para poder usarlo en el main y crear y tal archivos
    public FileManager() {

    }


    // ABRIR archivo, que es para leerlo
    public Experimento abrirArchivo(String nombreExperimento) throws Exception, FileNotFoundException { //es correcto
        File file;
        Experimento experimento = null;

        // Para flujo de entrada (leer)
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            file = new File("C:\\Users\\Ana Ventura-Traveset\\Desktop\\JAVA\\Java 2023-24\\Practica1\\src\\Laboratorio\\Experimento.java\\" + nombreExperimento + ".txt"); // Especifico el nombre del archivo,
            // lo cual no es una ruta absoluta, sino una ruta de acceso relativa
            // en realidad el nombreArchivo tendría este path: "C:\\Users\\AnaVentura\\nombreExperimento.txt"
            // debería poner en lo de new File, el path o lo otro?
            // Se podría primero verificar si existe el fichero, pero no voy a hacerlo asi

            // lo leo
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            while (objectInputStream.readObject() != null) { //mientras haya experimentos, adelante
                System.out.println(objectInputStream.readObject());
            }
        } catch (IOException ioe) {
            System.out.println("ERROR");
            ioe.printStackTrace();
        } catch (Exception e) { //esto es para el objectInputStream
            System.out.println("Se ha producido un error antes de terminar las escrituras");
            e.printStackTrace();
        }
        return experimento;
    }


    // GUARDAR/GUARDAR COMO archivo
    // en este caso voy a usar el mismo método para guardar y guardar como
    // simplemente en el segundo caso, se le pasará el String de como se quiera guardar
    public boolean guardarArchivo(String nombreExperimento, Experimento experimento) {
        File file = new File("C:\\Users\\Ana Ventura-Traveset\\Desktop\\Java 2021-22\\JavaPractica2022\\" + nombreExperimento + ".txt");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file);
            String experimentoInfoFile = experimento.getNombreExperimento() + ';' + experimento.getDias() + ';' + experimento.getNumPoblaciones();
            printWriter.println(experimentoInfoFile);//escribe en el fichero
            String infoPoblacionesFile = "";
            for (int i = 0; i < experimento.getPoblacionesList().size(); i++) {
                infoPoblacionesFile += experimento.getPoblacionesList().get(i).toString();
                printWriter.println(infoPoblacionesFile); //lo escribimos en el fichero
            }
            printWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (printWriter != null) {
                printWriter.close();

            }
        }

    }



}