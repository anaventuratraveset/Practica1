package laboratorio;
import java.util.ArrayList;

/**
 * Es la clase que representa un experimento en la aplicación.
 * @author Ana Ventura-Traveset
 */
public class Experimento {
    /**
     * Atributos experimento
     *
     * nombreExperimento
     * numPoblaciones
     * poblacionesList
     */
    private String nombreExperimento;
    private int numPoblaciones;
    private ArrayList<Poblacion> poblacionesList;

    /**
     * Constructor de experimento
     */
    public Experimento(){
    }

    /**
     * Constructor de experimento
     * @param nombreNuevoExperimento
     */
    public Experimento(String nombreNuevoExperimento){
        this.nombreExperimento=nombreNuevoExperimento;
        this.poblacionesList = new ArrayList<Poblacion>();
    }

    /**
     * Getters y setters de los atributos privados de Experimento
     *
     */
    /**
     * getNombreExperimento
     * @return String nombreExperiment0
     */
    public String getNombreExperimento() {
        return nombreExperimento;
    }
    /**
     * setNombreExperimento
     * @param nombreExperimento
     */
    public void setNombreExperimento(String nombreExperimento) {
        this.nombreExperimento = nombreExperimento;
    }
    /**
     * getNumPoblaciones
     * @return int numPoblaciones
     */
    public int getNumPoblaciones() {
        return numPoblaciones;
    }
    /**
     * setNumPoblaciones
     * @param numPoblaciones
     */
    public void setNumPoblaciones(int numPoblaciones) {
        this.numPoblaciones = numPoblaciones;
    }
    /**
     * getPoblacionesList()
     * @return ArrayList<Poblacion> this.poblacionesList: la lista de Poblaciones
     */
    public ArrayList<Poblacion> getPoblacionesList() {
        return this.poblacionesList;
    }

    public void setPoblacionesList(ArrayList<Poblacion> poblacionesList) {
        this.poblacionesList = poblacionesList;
    }

    /**
     * toString
     * Este nos enseña TODA la info del experimento
     * @return
     */
    @Override
    public String toString() {
        String stringToRepresentInfoPoblacionesExperimento = "Nombre Experimento: " + this.nombreExperimento
                + "\n\nInformación de las poblaciones:\n";
        if(poblacionesList!=null) {
            for (int i = 0; i < poblacionesList.size(); i++) {
                stringToRepresentInfoPoblacionesExperimento += "\nPOBLACIÓN " + (i + 1) + ": " + poblacionesList.get(i).toString()+"\n";
            }
        }
        return stringToRepresentInfoPoblacionesExperimento;
    }
    /**
     * Muestra el nombre de todas las poblaciones del experimento
     * @return toStringNombres(
     */
    public String toStringNombres() {
        String stringToRepresentNombrePoblacionesExperimento = "Experimento: " + this.nombreExperimento
                + "\n\n";
        for (int i=0; i<this.poblacionesList.size() ; i++) {
            stringToRepresentNombrePoblacionesExperimento += "Poblacion " + (i + 1) + ": " + poblacionesList.get(i).getNombrePoblacion()+"\n";
        }
        return stringToRepresentNombrePoblacionesExperimento;
    }
    /**
     * Muestra la información del experimento separada por ";" (modo fichero)
     * @return
     */
    public String toStringInfoExperimentoToFile(){
        String stringToRepresentInExperimentobFile = this.nombreExperimento;
        return stringToRepresentInExperimentobFile;
    }
}
