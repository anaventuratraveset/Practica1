package Laboratorio;
import Medio.Luminosidad;
import Medio.Comida;
import java.time.LocalDate;
import Laboratorio.*;

public class Experimento {

    private String nombre;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected int numBacterias;
    protected double temperatura;
    protected Luminosidad.luminosidad luminosidad;
    protected Comida dosisComida;
    private final int dias = 30; // duración del experimento
    protected int numPoblaciones;
    private Poblacion[] poblacionesBacterias = new Poblacion[numPoblaciones];


    //constructor
    public Experimento(){

    }
    public Experimento(String nombre, LocalDate fechaInicio, LocalDate fechaFin, int numBacterias,
                       double temperatura, Luminosidad.luminosidad luminosidad, Comida dosisComida,
                       int numPoblaciones, Poblacion[] poblacionesBacterias) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numBacterias = numBacterias;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
        this.dosisComida = dosisComida;
        this.numPoblaciones = numPoblaciones;
        this.poblacionesBacterias = poblacionesBacterias;
    }


    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Poblacion[] getPoblacionesBacterias() {
        return poblacionesBacterias;
    }

    public void setPoblacionesBacterias(Poblacion[] poblacionesBacterias) {
        this.poblacionesBacterias = poblacionesBacterias;
    }

}
