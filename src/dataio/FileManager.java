package dataio;
//Importo todo aqui, pq es dónde estoy creando los experimentos
import gestionLab.GestionLab;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.Comida;
import medio.Luminosidad;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public static Experimento abrirArchivo(String nombreExperimento) throws Exception { //es correcto
        File file = new File("./" + nombreExperimento + ".txt"); //abrimos flujo de datos
        Experimento experimento = null;

        // Para flujo de entrada (leer)
        FileInputStream fileInputStream = null; //sirve para leer flujo de datos en bruto
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            // lo leo
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String texto = bufferedReader.readLine();
            String [] misArgs = texto.split(";");

            //leo info del experimento
            String nombreExpFromFile = misArgs[0];
            int diasExpFromFile = Integer.parseInt(misArgs[1]);
            int numPoblacionesFromFile = Integer.parseInt(misArgs[2]);

            //creo el experimento
            new Experimento(nombreExpFromFile, numPoblacionesFromFile);
            //fin leer info experimento

            //Empiezo a leer info de poblaciones
            for (int i=3; i<numPoblacionesFromFile;i++) {
                Poblacion poblacion = new Poblacion();

                String nombrePoblacionFromFile = misArgs[3];
                poblacion.setNombrePoblacion(nombrePoblacionFromFile);

                int numBacteriasFromFile = Integer.parseInt(misArgs[4]);
                poblacion.setNumInicialBacterias(numBacteriasFromFile);

                float temperaturaFromFile = Float.parseFloat(misArgs[5]);
                poblacion.setTemperatura(temperaturaFromFile);

                Luminosidad.luminosidad luminosidadFromFile = Luminosidad.luminosidad.valueOf(misArgs[6]);
                poblacion.setLuminosidad(luminosidadFromFile);

                // leo el toStringToFile de comida
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicioFromFile = LocalDate.parse(misArgs[7], dtf);
                poblacion.setFechaInicio(fechaInicioFromFile);
                int cantidadInicialFromFile = Integer.parseInt(misArgs[8]);
                LocalDate fechaPicoFromFile = LocalDate.parse(misArgs[9], dtf);
                int cantidadPicoFromFile = Integer.parseInt(misArgs[10]);
                LocalDate fechaFinFromFile = LocalDate.parse(misArgs[11], dtf);
                poblacion.setFechaFin(fechaFinFromFile);
                int cantidadFinalFromFile = Integer.parseInt(misArgs[12]);

                Comida comida = new Comida(cantidadInicialFromFile, fechaInicioFromFile, cantidadPicoFromFile, fechaPicoFromFile, cantidadFinalFromFile, fechaFinFromFile);
                poblacion.setComida(comida);
                System.out.println("\nINFORMACION DEL FICHERO CARGADO EN MEMORIA\n");
                System.out.println("Estoy en abrir archivo");
                System.out.println(experimento.toString());

                bufferedReader.close();
                GestionLab.addPoblacion(poblacion,experimento);
            }
        }catch (Exception e){
            System.out.println("ERROR FileManager leyendo archivo. Puede que no exista nigún archivo con ese nombre");
            e.printStackTrace();
        }
        finally {
            if (bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch(IOException ioe){
                    System.out.println(ioe.getMessage());
                }
            }
        }

        if(inputStreamReader != null){
            try{
                inputStreamReader.close();
            }
            catch(IOException ioException){
                System.out.println(ioException.getMessage());
            }
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
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
            String experimentoInfoFile = experimento.getNombreExperimento() + ';' + experimento.getDias() + ';' + experimento.getNumPoblaciones()+";";
            printWriter.println(experimentoInfoFile);//escribe en el fichero
            for (int i = 0; i < experimento.getPoblacionesList().size(); i++) {
                String infoPoblacionesFile = "";
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