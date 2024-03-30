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
    public static Experimento abrirArchivo(String nombreExperimento) throws Exception {
        File file = new File("./" + nombreExperimento + ".txt"); //abrimos flujo de datos
        Experimento experimento =null;

        // Para flujo de entrada (leer)
        FileInputStream fileInputStream = null; //sirve para leer flujo de datos en bruto
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String stringInfoTotal="";
        try {
            // lo leo
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String [] todosArgs = bufferedReader.readLine().split("\n");
            String [] infoExperimento = todosArgs[0].split(";");
            String nombreExpFromFile = infoExperimento[0];

            //leo info del experimento
            int diasExpFromFile = Integer.parseInt(infoExperimento[1]);
            int numPoblacionesFromFile = Integer.parseInt(infoExperimento[2]);

            //creo el experimento
            experimento = new Experimento(nombreExpFromFile, numPoblacionesFromFile);
            //fin leer info experimento

            System.out.println(todosArgs.length+ "mi length");
            stringInfoTotal+=experimento.toStringInfoExperimentoToFile()+"\n";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] infoPoblacion = line.split(";");

                //Empiezo a leer info de poblaciones
                Poblacion poblacion = new Poblacion();

                String nombrePoblacionFromFile = infoPoblacion[0];
                poblacion.setNombrePoblacion(nombrePoblacionFromFile);

                int numBacteriasFromFile = Integer.parseInt(infoPoblacion[1]);
                poblacion.setNumInicialBacterias(numBacteriasFromFile);

                float temperaturaFromFile = Float.parseFloat(infoPoblacion[2]);
                poblacion.setTemperatura(temperaturaFromFile);

                Luminosidad.luminosidad luminosidadFromFile = Luminosidad.luminosidad.valueOf(infoPoblacion[3]);
                poblacion.setLuminosidad(luminosidadFromFile);

                // leo el toStringToFile de comida
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicioFromFile = LocalDate.parse(infoPoblacion[4], dtf);
                poblacion.setFechaInicio(fechaInicioFromFile);
                float cantidadInicialFromFile = Float.parseFloat(infoPoblacion[5]);
                LocalDate fechaPicoFromFile = LocalDate.parse(infoPoblacion[6], dtf);
                float cantidadPicoFromFile = Float.parseFloat(infoPoblacion[7]);
                LocalDate fechaFinFromFile = LocalDate.parse(infoPoblacion[8], dtf);
                poblacion.setFechaFin(fechaFinFromFile);
                float cantidadFinalFromFile = Float.parseFloat(infoPoblacion[9]);

                Comida comida = new Comida(cantidadInicialFromFile, fechaInicioFromFile, cantidadPicoFromFile, fechaPicoFromFile, cantidadFinalFromFile, fechaFinFromFile);
                poblacion.setComida(comida);
                GestionLab.addPoblacion(poblacion,experimento);
                stringInfoTotal+=poblacion.toStringInfoPobFile()+"\n";
                //System.out.println(experimento.toStringInfoExperimentoToFile()+"\n"+poblacion.toStringInfoPobFile());
            }
            System.out.println("\nFICHERO CARGADO EN MEMORIA\n");
            System.out.println(stringInfoTotal);
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("ERROR FileManager leyendo archivo. Puede que no exista ningún archivo con ese nombre");
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
            String experimentoInfoFile = experimento.getNombreExperimento() + ';' + experimento.getDias() + ';' + experimento.getNumPoblaciones();
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