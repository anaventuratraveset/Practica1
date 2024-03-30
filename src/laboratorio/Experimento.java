package laboratorio;
import java.util.ArrayList;


public class Experimento {

    private String nombreExperimento;
    private final int dias = 30; // duración del experimento
    private int numPoblaciones; //antes estaba protected
    private ArrayList<Poblacion> poblacionesList; //Uso una list, pq igual de repente le sumo poblaciones al experimento y no cabrían en un array


    //constructor
   /* public Experimento(){

    }*/

    public Experimento(String nombreNuevoExperimento){
        this.nombreExperimento=nombreNuevoExperimento;
        this.poblacionesList = new ArrayList<Poblacion>();
    }

    //Getters y setters
    public String getNombreExperimento() {
        return nombreExperimento;
    }

    public void setNombreExperimento(String nombreExperimento) {
        this.nombreExperimento = nombreExperimento;
    }

    public int getDias() {
        return dias;
    }

    public int getNumPoblaciones() {
        return numPoblaciones;
    }

    public void setNumPoblaciones(int numPoblaciones) {
        this.numPoblaciones = numPoblaciones;
    }

    public ArrayList<Poblacion> getPoblacionesList() {
        return this.poblacionesList;
    }

    public void setPoblacionNueva (Poblacion p){
      this.poblacionesList.add(p);
    }

    // Métodos para que al meter esta clase en un System.out.println() salga
    // algo legible
   @Override //Este nos enseña TODA la info del experimento
    public String toString() {
        String stringToRepresentInfoPoblacionesExperimento = "Nombre Experimento: " + this.nombreExperimento
                + "\nNumero de días: " + this.dias
                + "\n\nInformación de las poblaciones:\n";
        if(poblacionesList!=null) {
            for (int i = 0; i < poblacionesList.size(); i++) {
                stringToRepresentInfoPoblacionesExperimento += "\nPOBLACIÓN " + (i + 1) + ": " + poblacionesList.get(i).toString()+"\n";
            }
        }
        return stringToRepresentInfoPoblacionesExperimento;
    }

    //Este nos enseña todos los nombres de las poblaciones del experimento
    public String toStringNombres() {
        String stringToRepresentNombrePoblacionesExperimento = "Nombre Experimento: " + this.nombreExperimento
                + "\nNombre de poblaciones:\n";
        for (int i=0; i<this.poblacionesList.size() ; i++) {
            stringToRepresentNombrePoblacionesExperimento += "Nombre de la poblacion " + (i + 1) + ": " + poblacionesList.get(i).getNombrePoblacion()+"\n";
        }
        return stringToRepresentNombrePoblacionesExperimento;
    }

    public String toStringInfoExperimentoToFile(){
        String stringToRepresentInExperimentobFile = this.nombreExperimento;
        return stringToRepresentInExperimentobFile;
    }
}
