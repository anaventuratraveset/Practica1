package dataio;
import gestionLab.GestionLab;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.*;

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
//            String [] infoExperimento = todosArgs[0].split(";");
//            String nombreExpFromFile = infoExperimento[0];
            //lo hago así ahora porque ya la duracion ya no depende de cada experimento sino que cada poblaicon tiene la suya propia
            String nombreExpFromFile = todosArgs[0];

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

                int numPatronComida = Integer.parseInt(infoPoblacion[4]);
                poblacion.setNumeroPatronComida(numPatronComida);

                // Empiezo con la info de comida
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicioFromFile = LocalDate.parse(infoPoblacion[5], dtf);
                poblacion.setFechaInicio(fechaInicioFromFile);
                int cantidadInicialFromFile = Integer.parseInt(infoPoblacion[6]);
                poblacion.setCantidadFinal(cantidadInicialFromFile);
                //Tengo que ver lo de arriba
                LocalDate fechaFinFromFile;

                switch (numPatronComida){
                    case 1:
                        LocalDate fechaPicoFromFile = LocalDate.parse(infoPoblacion[7], dtf);
                        poblacion.setFechaPico(fechaPicoFromFile);
                        int cantidadPicoFromFile = Integer.parseInt(infoPoblacion[8]);
                        poblacion.setCantidadPico(cantidadPicoFromFile);
                        fechaFinFromFile = LocalDate.parse(infoPoblacion[9], dtf);
                        poblacion.setFechaFin(fechaFinFromFile);
                        int cantidadFinalFromFile = Integer.parseInt(infoPoblacion[10]);
                        poblacion.setCantidadFinal(cantidadFinalFromFile);
                        ComidaPico comidaPico = new ComidaPico(cantidadInicialFromFile, fechaInicioFromFile, cantidadPicoFromFile, fechaPicoFromFile, cantidadFinalFromFile, fechaFinFromFile);
                        poblacion.setDosisComidaDiaria(comidaPico.calcularComida());
                        poblacion.setComida(comidaPico);
                        break;

                    case 2:
                        fechaFinFromFile = LocalDate.parse(infoPoblacion[7], dtf);
                        poblacion.setFechaFin(fechaFinFromFile);
                        ComidaCte comidaCte = new ComidaCte(cantidadInicialFromFile, fechaInicioFromFile, fechaFinFromFile);
                        poblacion.setDosisComidaDiaria(comidaCte.calcularComida());
                        poblacion.setComida(comidaCte);
                        break;

                    case 3:
                        fechaFinFromFile = LocalDate.parse(infoPoblacion[7], dtf);
                        poblacion.setFechaFin(fechaFinFromFile);
                        cantidadFinalFromFile = Integer.parseInt(infoPoblacion[8]);
                        poblacion.setCantidadFinal(cantidadFinalFromFile);
                        ComidaIncremento comidaIncremento = new ComidaIncremento(cantidadInicialFromFile, fechaInicioFromFile, fechaFinFromFile, cantidadFinalFromFile);
                        poblacion.setDosisComidaDiaria(comidaIncremento.calcularComida());
                        poblacion.setComida(comidaIncremento);
                        break;

                    case 4:
                        fechaFinFromFile = LocalDate.parse(infoPoblacion[7], dtf);
                        poblacion.setFechaFin(fechaFinFromFile);
                        ComidaIntermitente comidaIntermitente = new ComidaIntermitente(cantidadInicialFromFile, fechaInicioFromFile, fechaFinFromFile);
                        poblacion.setDosisComidaDiaria(comidaIntermitente.calcularComida());
                        poblacion.setComida(comidaIntermitente);
                        break;
                }
                GestionLab.addPoblacion(poblacion,experimento);
                stringInfoTotal+=poblacion.toStringInfoPobFile()+"\n";
            }
            System.out.println("\nFICHERO CARGADO EN MEMORIA\n");
            System.out.println(stringInfoTotal);
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("\nERROR FileManager leyendo archivo. \nPuede que no exista ningún archivo con ese nombre");
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
            String experimentoInfoFile = experimento.getNombreExperimento() ;
            printWriter.println(experimentoInfoFile);//escribe en el fichero primero la info del experimento
            for (int i = 0; i < experimento.getPoblacionesList().size(); i++) {
                String infoPoblacionesFile = "";
                infoPoblacionesFile += experimento.getPoblacionesList().get(i).toStringInfoPobFile();
                infoPoblacionesFile += ";"+experimento.getPoblacionesList().get(i).getComida().toStringToFile();
                printWriter.print(infoPoblacionesFile); //escribe en el fichero ahora la info de cada población
                printWriter.println();
            }
            printWriter.close();
            comprobacion=true;
        } catch (IOException e) { // Cacheo esta excepción porque el constructor de PrintWriter lanza esa excepción
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    return comprobacion;
    }
}