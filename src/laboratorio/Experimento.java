package laboratorio;
import medio.Luminosidad;
import medio.Comida;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Experimento {

    private String nombreExperimento;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected int numBacterias;
    protected double temperatura;
    protected Luminosidad.luminosidad luminosidad;
    protected Comida dosisComida;
    private final int dias = 30; // duración del experimento
    protected int numPoblaciones;
    private List<Poblacion> poblacionesList; //Uso una list, pq igual de repente le sumo poblaciones al experimento y no cabrían en un array


    //constructor
    public Experimento(){

    }

    public Experimento(String nombreNuevoExperimento, int numPoblaciones){
        this.nombreExperimento=nombreNuevoExperimento;
        this.numPoblaciones=numPoblaciones;
    }
    public Experimento(String nombreExperimento, LocalDate fechaInicio, LocalDate fechaFin, int numBacterias,
                       double temperatura, Luminosidad.luminosidad luminosidad, Comida dosisComida,
                       int numPoblaciones) {
        this.nombreExperimento = nombreExperimento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numBacterias = numBacterias;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
        this.dosisComida = dosisComida;
        this.numPoblaciones = numPoblaciones;
        this.poblacionesList = new LinkedList<Poblacion>();
    }


    //Getters y setters
    public String getNombreExperimento() {
        return nombreExperimento;
    }

    public void setNombreExperimento(String nombreExperimento) {
        this.nombreExperimento = nombreExperimento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumBacterias() {
        return numBacterias;
    }

    public void setNumBacterias(int numBacterias) {
        this.numBacterias = numBacterias;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public Luminosidad.luminosidad getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(Luminosidad.luminosidad luminosidad) {
        this.luminosidad = luminosidad;
    }

    public Comida getDosisComida() {
        return dosisComida;
    }

    public void setDosisComida(Comida dosisComida) {
        this.dosisComida = dosisComida;
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

    public List<Poblacion> getPoblacionesList() {
        return this.poblacionesList;
    }
    public void setPoblacionesList(List<Poblacion> lista){
        this.poblacionesList=lista;
    }

    // Métodos para que al meter esta clase en un System.out.println() salga
    // algo legible
    @Override //Este nos enseña TODA la info del experimento
    public String toString() {
        String stringToRepresentInfoPoblacionesExperimento = "Nombre Experimento: " + this.nombreExperimento
                + "\nNumero de días: " + dias
                + "\n\nInformación de las poblaciones:\n";
        Iterator<Poblacion> iterador = poblacionesList.iterator();
        int i=0;
        while (iterador.hasNext()) {
            stringToRepresentInfoPoblacionesExperimento += "\nPOBLACIÓN " + (i + 1) + ": " + iterador.next().toString();
            i++;
        }
        return stringToRepresentInfoPoblacionesExperimento;
    }

    //Este nos enseña todos los nombres de las poblaciones del experimento
    public String toStringNombres() {
        String stringToRepresentNombrePoblacionesExperimento = "Nombre Experimento: " + this.nombreExperimento
                + "\nNombre de poblaciones:\n";
        Iterator<Poblacion> iterador = poblacionesList.iterator();
        int i=0;
        while (iterador.hasNext()) {
            stringToRepresentNombrePoblacionesExperimento += "Nombre de la poblacion " + (i + 1) + ": " + iterador.next().getNombrePoblacion();
            i++;
        }
        return stringToRepresentNombrePoblacionesExperimento;
    }


}
