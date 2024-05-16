package laboratorio;
import medio.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Ana Ventura-Traveset
 */
public class Poblacion {

    /**
     * Atributos poblacion
     *
     * nombrePoblacion
     * numInicialBacterias
     * temperatura
     * fechaInicio
     * fechaFin
     * luminosidad
     * comida
     * dosisComidaDiaria
     * numeroPatronComida
     * comidaMax
     */
    private String nombrePoblacion;
    private int numInicialBacterias;
    private float temperatura;
    private LocalDate fechaInicio, fechaFin, fechaPico;
    private Luminosidad.luminosidad luminosidad;
    private ComidaPadre comida;
    private int cantidadInicial, cantidadFinal, cantidadPico;
    private int numeroPatronComida;
    private final int comidaMax = 300000;
    private int [] dosisComidaDiaria;
    private ArrayList<Bacteria> bacteriasList = new ArrayList<>();


    /**
     * Constructor vacío de poblacion
     */
    public Poblacion(){
    }

    /**
     * Constructor de poblacion
     * @param numInicialBacterias
     * @param nombrePoblacion
     * @param temperatura
     * @param fechaInicio
     * @param fechaFin
     * @param luminosidad
     * @param numeroPatronComida
     */
    public Poblacion(int numInicialBacterias, String nombrePoblacion, float temperatura, LocalDate fechaInicio, LocalDate fechaFin, Luminosidad.luminosidad luminosidad, int numeroPatronComida) {
        this.numInicialBacterias = numInicialBacterias;
        this.nombrePoblacion = nombrePoblacion;
        this.temperatura = temperatura;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.luminosidad = luminosidad;
        this.numeroPatronComida = numeroPatronComida;
    }

    /**
     * Getters y setters
     * @return
     */

    /**
     * Muestra el nombre de la poblacion
     *
     * @return String
     */
    public String getNombrePoblacion() {
        return nombrePoblacion;
    }

    public LocalDate getFechaPico() {
        return fechaPico;
    }

    public void setFechaPico(LocalDate fechaPico) {
        this.fechaPico = fechaPico;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public int getCantidadFinal() {
        return cantidadFinal;
    }

    public void setCantidadFinal(int cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
    }

    public int getCantidadPico() {
        return cantidadPico;
    }

    public void setCantidadPico(int cantidadPico) {
        this.cantidadPico = cantidadPico;
    }

    /**
     * Creo los getters de los siguientes atributos para poder hacer el test unitario
     */
    public int getNumInicialBacterias() {
        return numInicialBacterias;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public float getComidaMax() {
        return comidaMax;
    }

    public int[] getDosisComidaDiaria() {
        return dosisComidaDiaria;
    }
    public void setDosisComidaDiaria(int[] dosisComidaDiaria) {
        this.dosisComidaDiaria = dosisComidaDiaria;
    }

    public int getDosisComidaXDia(int dia) {
        switch (this.numeroPatronComida){
            case 1:
                ComidaPico comidaPico = new ComidaPico(cantidadInicial, fechaInicio,cantidadPico, fechaPico, cantidadFinal,fechaFin);
                this.comida = comidaPico;
                this.dosisComidaDiaria=comidaPico.calcularComida();
                break;
            case 2:
                ComidaCte comidaCte = new ComidaCte(cantidadInicial, fechaInicio, fechaFin);
                this.comida = comidaCte;
                this.dosisComidaDiaria=comidaCte.calcularComida();
                break;
            case 3:
                ComidaIncremento comidaIncremento = new ComidaIncremento(cantidadInicial, fechaInicio, fechaFin, cantidadFinal);
                this.comida = comidaIncremento;
                this.dosisComidaDiaria=comidaIncremento.calcularComida();
                break;
            case 4:
                ComidaIntermitente comidaIntermitente = new ComidaIntermitente(cantidadInicial, fechaInicio, fechaFin);
                this.comida = comidaIntermitente;
                this.dosisComidaDiaria=comidaIntermitente.calcularComida();
                break;
        }
        return dosisComidaDiaria[dia];
    }

    public void setNumeroPatronComida(int numeroPatronComida) {
        this.numeroPatronComida = numeroPatronComida;
    }
    public int getNumeroPatronComida() {
        return numeroPatronComida;
    }


    /** Permite modificar el nombre de la Población
     * @param nombrePoblacion*/
    public void setNombrePoblacion(String nombrePoblacion) {
        this.nombrePoblacion = nombrePoblacion;
    }

    /**
     * Permite modificar el número inicial de bacterias de la Población
     * @param numInicialBacterias
     */
    public void setNumInicialBacterias(int numInicialBacterias) {
        this.numInicialBacterias = numInicialBacterias;
    }

    /**
     * Permite modificar la temperatura de la Población
     * @param temperatura
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Permite modificar la fecha de inicio del experimento de la Población
     * @param fechaInicio
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Permite modificar la fecha de fin del experimento de la Población
     * @param fechaFin
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Permite modificar la luminosidad de la Población
     * @param luminosidad
     */
    public void setLuminosidad(Luminosidad.luminosidad luminosidad) {
        this.luminosidad = luminosidad;
    }

    /**
     * Permite modificar la comida de la Población
     */

    public void setComida(ComidaPadre comida) {
        this.comida = comida;
    }

    /**
     * Getters para los test
     */

    public Luminosidad.luminosidad getLuminosidad() {
        return luminosidad;
    }

    public ComidaPadre getComida() {
        return comida;
    }


    /**
     * getBacteriasList()
     * @return ArrayList<Bacteria> this.bacteriasList: la lista de Bacterias
     */
    public ArrayList<Bacteria> getBacteriasList() {
        return this.bacteriasList;
    }

    /**
     * setBacteriaNueva
     * Añade la bacteria a la lista de bacterias
     * @param b
     */
    public void setBacteriaNueva (Bacteria b){
        this.bacteriasList.add(b);
    }

    /**
     * get para los unit test
     */
    public Bacteria getBacteriaNueva(){
        return this.bacteriasList.get(this.bacteriasList.size()-1);
    }




    /**
     * toString para cuando se seleccione la opción 6, para visualizar la info de la población
     * @return stringToRepresentPoblacion
     */
    @Override
    public String toString(){
        String opcionPatron = "";
        if (this.numeroPatronComida == 1) {
            opcionPatron = this.numeroPatronComida + ". Patrón de incremento lineal seguido de decremento lineal.";
        } else if (this.numeroPatronComida == 2) {
            opcionPatron = this.numeroPatronComida + ". Patrón de cantidad de comida constante durante toda la duración del experimento.";
        } else if (this.numeroPatronComida == 3) {
            opcionPatron = this.numeroPatronComida + ". Patrón de incremento lineal de la cantidad de comida.";
        } else if (this.numeroPatronComida == 4) {
            opcionPatron = this.numeroPatronComida + ". Patrón con cantidad de comida constante durante todo el experimento un día, y al siguiente no proporcionar comida y así sucesivamente";
        }


        String stringToRepresentPoblacion = "La población "
                + this.nombrePoblacion +":"
                + "\nCantidad de bacterias inicialmente: "+ this.numInicialBacterias
                + "\nTemperatura a la cual están sometidas las bacterias: "+ this.temperatura
                + "\nLuminosidad: "+this.luminosidad
                + "\nPatrón de comida: " + opcionPatron
                + "\nDosis de comida diaria: "+ this.comida.toString();
        return stringToRepresentPoblacion;
    }

    /**
     * Muestra la información de la población separada por ";" (modo fichero)
     * @return stringToRepresentInfoPobFile
     */
    public String toStringInfoPobFile() {
        String stringToRepresentInfoPobFile = this.nombrePoblacion
                + ";" + this.numInicialBacterias
                + ";" + this.temperatura
                + ";" + this.luminosidad
                + ";" + this.numeroPatronComida
                /*+ ";" + comida.toStringToFile()*/; // no sé si debería quitarlo y ponerlo directamente desde comida o dejarlo

        return stringToRepresentInfoPobFile;
    }

}


