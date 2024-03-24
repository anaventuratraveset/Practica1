package dataio;
//Importo todo aqui, pq es dónde estoy creando los experimentos
import Laboratorio.Experimento;
import java.io.*;
import java.util.*;


// Crear archivo en el que meto experimento
// Abrir un archivo que contenga un experimento
// Guardar (se supone que para usar esta opción previamente hemos abierto unarchivo)
// Guardar como

public class FileManager {

    // su constructor, para poder usarlo en el main y crear y tal archivos
    public FileManager(){

    }

    // ABRIR archivo, que es para leerlo
    public Experimento abrirArchivoYCrearExperimento(String nombreExperimento) { //es correcto
        File miArchivo = new File(nombreExperimento+".txt"); // Especifico el nombre del archivo,
        // lo cual no es una ruta absoluta, sino una ruta de acceso relativa
        // en realidad el nombreArchivo tendría este path: "C:\\Users\\AnaVentura\\nombreExperimento.txt"
        // debería poner en lo de new File, el path o lo otro?
        Experimento experimento=new Experimento();
        // Para fñujo de salida (escribir)
        FileOutputStream fileOutputStream=null;
        ObjectOutputStream objectOutputStream=null;
        // Para flujo de entrada (leer)
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            //primero veo si el archivo ya existe y sino, lo creo antes de leerlo
            if (miArchivo.createNewFile()) {
                System.out.println("Archivo creado: " + miArchivo.getName()); //eso o el nombre del Experimento en cuestión
                //Escribimos dentro de éste, para crearle un experimento
                fileOutputStream= new FileOutputStream(miArchivo);
                objectOutputStream=new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(experimento); //flujo de SALIDA = ESCRIBIR
            } else {
                System.out.println("El archivo ya existe.");
                return experimento;
            }

            //Una vez he visto que existe o lo he creado => lo leo
            fileInputStream = new FileInputStream(miArchivo);
            objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.println(objectInputStream.readObject());

        } catch (IOException ioe) {
            System.out.println("ERROR");
            ioe.printStackTrace();
        } catch (Exception e) { //esto es para el objectInputStream
            System.out.println("Se ha producido un error antes de terminar las escrituras");
                    e.printStackTrace();
        } finally { //IMPORTANTE cerrar el flujo de datos al acabar!!
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }
    }

    // ESCRIBIR en un archivo (meterle experimento)
    public void escribirEnArchivo(String nombreExperimento){
        try {
            FileWriter myWriter = new FileWriter(nombreExperimento+".txt");
            myWriter.write("AQUI LE TENGO QUE METER TODA LA CHICHA DEL EXPERIMENTO");
            myWriter.close(); //importante cerrar el flujo de datos de entrada
            System.out.println("Se ha escrito correctament en el archivo "+nombreExperimento+".");
        } catch (IOException e) {
            System.out.println("ERROR.");
        }
    }

    // LEER archivo (que entiendo que es lo mismo que abrirlo
    public void leerArchivo(String nombreExperimento){
        try {
            File miArchivo = new File(nombreExperimento+".txt");
            Scanner myReader = new Scanner(miArchivo);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR.");
        }
    }

   // GUARDAR archivo
    public void guardarArchivo(String nombreExperimento){

    }
}