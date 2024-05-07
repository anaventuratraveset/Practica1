package laboratorio;
import medio.ComidaPadre;
import medio.Luminosidad;
import java.time.LocalDate;

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
    private LocalDate fechaInicio, fechaFin;
    private Luminosidad.luminosidad luminosidad;
    private ComidaPadre comida;
    private int numeroPatronComida;
    private final int comidaMax = 300000;



    private int [] dosisComidaDiaria;

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

    public void setNumeroPatronComida(int numeroPatronComida) {
        this.numeroPatronComida = numeroPatronComida;
    }
    public int getNumeroPatronComida() {
        return numeroPatronComida;
    }


    /**
     * Permite modificar el nombre de la Población
     * @param nombrePoblacion
     */
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
     * @param comida
     */
    public void setComida(ComidaPadre comida) {
        this.comida = comida;
        this.dosisComidaDiaria=comida.calcularComida();
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
                + "\nFecha de inicio del experimento: "+ this.fechaInicio
                + "\nFecha de fin del experimento: "+ this.fechaFin
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
                + ";" + comida.toStringToFile();

        return stringToRepresentInfoPobFile;
    }

}


