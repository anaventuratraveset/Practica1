package dataio;
import gestionLab.GestionLab;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.ComidaPadre;
import medio.Luminosidad;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Gestion de archivos
 * @author Ana Ventura-Traveset
 */
public class FileManager {

    /**
     * Abre archivo y lo carga en memoria
     * @param nombreExperimento
     * @return experimento
     * @throws FileNotFoundException
     */
    public static Experimento abrirArchivo(String nombreExperimento) throws FileNotFoundException {
        File file = new File("./" + nombreExperimento + ".txt");
        Experimento experimento =null;

        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String stringInfoTotal="";

        // hacer esto en varios métodos
        try {
            // lo leo
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String [] todosArgs = bufferedReader.readLine().split("\n");
            String [] infoExperimento = todosArgs[0].split(";");
            String nombreExpFromFile = infoExperimento[0];
            int diasExpFromFile = Integer.parseInt(infoExperimento[1]);

            experimento = new Experimento(nombreExpFromFile);

            System.out.println(todosArgs.length+ "mi length");
            stringInfoTotal+=experimento.toStringInfoExperimentoToFile()+"\n";

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] infoPoblacion = line.split(";");

                Poblacion poblacion = new Poblacion();

                // tengo q conseguir hacerlo de otra manera para q recorra el array

                //Empiezo con la info de poblaciones
                String nombrePoblacionFromFile = infoPoblacion[0];
                poblacion.setNombrePoblacion(nombrePoblacionFromFile);

                int numBacteriasFromFile = Integer.parseInt(infoPoblacion[1]);
                poblacion.setNumInicialBacterias(numBacteriasFromFile);

                float temperaturaFromFile = Float.parseFloat(infoPoblacion[2]);
                poblacion.setTemperatura(temperaturaFromFile);

                Luminosidad.luminosidad luminosidadFromFile = Luminosidad.luminosidad.valueOf(infoPoblacion[3]);
                poblacion.setLuminosidad(luminosidadFromFile);

                // Empiezo con la info de comida
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicioFromFile = LocalDate.parse(infoPoblacion[4], dtf);
                poblacion.setFechaInicio(fechaInicioFromFile);
                float cantidadInicialFromFile = Float.parseFloat(infoPoblacion[5]);
                LocalDate fechaPicoFromFile = LocalDate.parse(infoPoblacion[6], dtf);
                float cantidadPicoFromFile = Float.parseFloat(infoPoblacion[7]);
                LocalDate fechaFinFromFile = LocalDate.parse(infoPoblacion[8], dtf);
                poblacion.setFechaFin(fechaFinFromFile);
                float cantidadFinalFromFile = Float.parseFloat(infoPoblacion[9]);

                ComidaPadre comida = new ComidaPadre(cantidadInicialFromFile, fechaInicioFromFile, cantidadPicoFromFile, fechaPicoFromFile, cantidadFinalFromFile, fechaFinFromFile);
                poblacion.setComida(comida);
                GestionLab.addPoblacion(poblacion,experimento);
                stringInfoTotal+=poblacion.toStringInfoPobFile()+"\n";
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
                    bufferedReader.close(); // cuando deja de leer
                }catch(IOException ioe){
                    System.out.println(ioe.getMessage());
                }
            }
            if(inputStreamReader != null){
                try{
                    inputStreamReader.close(); // cuando dejan de entrar datos
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
        }
        return experimento;
    }


    /**
     * Guarda/guarda como experimento en archivo
     * @param nombreExperimento
     * @param experimento
     * @return comprobacion
     */
    public static boolean guardarArchivo(String nombreExperimento, Experimento experimento) {
        File file1 = new File( "./"+nombreExperimento + ".txt");
        PrintWriter printWriter = null;
        boolean comprobacion=false;
        try {
            printWriter = new PrintWriter(file1);
            String experimentoInfoFile = experimento.getNombreExperimento() + ';' + experimento.getDias();
            printWriter.println(experimentoInfoFile);//escribe en el fichero primero la info del experimento
            for (int i = 0; i < experimento.getPoblacionesList().size(); i++) {
                String infoPoblacionesFile = "";
                infoPoblacionesFile += experimento.getPoblacionesList().get(i).toStringInfoPobFile();
                printWriter.print(infoPoblacionesFile); //escribe en el fichero ahora la info de cada población
                printWriter.println();
            }
            printWriter.close();
            comprobacion=true;
        } catch (IOException e) { // Cacheo esta excepción porque el constructor de  PrintWriter lanza esa excepción
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