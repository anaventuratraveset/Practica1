package dataio;
//Importo todo aqui, pq es dónde estoy creando los experimentos
import laboratorio.Experimento;

import java.io.*;

import static gestionLab.GestionLab.createPoblacion;


// Crear archivo en el que meto experimento
// Abrir un archivo que contenga un experimento
// Guardar (se supone que para usar esta opción previamente hemos abierto unarchivo)
// Guardar como

public class FileManager {

    // su constructor, para poder usarlo en el main y crear y tal archivos
    public FileManager() {

    }


    // ABRIR archivo, que es para leerlo
    public static Experimento abrirArchivo(String nombreExperimento) throws Exception, FileNotFoundException { //es correcto
        File file = new File("./" + nombreExperimento + ".txt"); //abrimos flujo de datos
        Experimento experimento = null;

        // Para flujo de entrada (leer)
        FileInputStream fileInputStream = null; //sirve para leer flujo de datos en bruto
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            // lo leo
            experimento = new Experimento();
            fileInputStream = new FileInputStream(file); //crea una conexion a un archivo "file"
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String texto=bufferedReader.readLine();


            //CREO experimento
            int numPoblaciones;
            while (true) {
                numPoblaciones = UserInput.readInt("Escriba el número de poblaciones que tiene su nuevo experimento: ");
                if (numPoblaciones < 0) {
                    System.out.println("El número de días no puede ser negativo.");
                } else {
                    break;
                }
            }
            experimento = new Experimento(nombreExperimento, numPoblaciones);
            for (int i = 0; i < numPoblaciones; i++) {
                try {
                    int numPoblacion = i + 1;
                    System.out.println("Poblacion " + numPoblacion + ":\n");
                    createPoblacion(experimento);
                    texto+=experimento.getPoblacionesList().get(i).toStringInfoPobFile();
                } catch (IOException ioe) {
                    System.out.println("ERROR");
                    ioe.printStackTrace();
                } catch (Exception e) { //esto es para el InputStreamReader
                    System.out.println("Se ha producido un error antes de terminar las escrituras");
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            System.out.println("ERROR.");
        }
        return experimento;
    }


    // GUARDAR/GUARDAR COMO archivo
    // en este caso voy a usar el mismo método para guardar y guardar como
    // simplemente en el segundo caso, se le pasará el String de como se quiera guardar
    public static boolean guardarArchivo(String nombreExperimento, Experimento experimento) {
        File file1 = new File( "./"+nombreExperimento + ".txt");
        PrintWriter printWriter = null;
        boolean comprobacion=false;
        try {
            printWriter = new PrintWriter(file1);
            String experimentoInfoFile = experimento.getNombreExperimento() + ';' + experimento.getDias() + ';' + experimento.getNumPoblaciones();
            printWriter.println(experimentoInfoFile);//escribe en el fichero
            String infoPoblacionesFile = "";
            for (int i = 0; i < experimento.getPoblacionesList().size(); i++) {
                infoPoblacionesFile += experimento.getPoblacionesList().get(i).toStringInfoPobFile();
                printWriter.print(infoPoblacionesFile); //lo escribimos en el fichero
                printWriter.println();
            }
            printWriter.close();
            comprobacion=true;
        } catch (IOException e) {
            e.printStackTrace();
            comprobacion=false;
        } finally {
            if (printWriter != null) {
                printWriter.close();

            }
        }
    return comprobacion;
    }



}