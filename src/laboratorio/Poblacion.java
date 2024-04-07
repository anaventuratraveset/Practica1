package laboratorio;
import medio.Comida;
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
     */
    private String nombrePoblacion;
    private int numInicialBacterias;
    private float temperatura;
    private LocalDate fechaInicio, fechaFin;
    private Luminosidad.luminosidad luminosidad;
    private Comida comida;
    private float [] dosisComidaDiaria;

    /**
     * Constructor vacío de poblacion
     */
    public Poblacion(){
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
    public void setComida(Comida comida) {
        this.comida = comida;
        this.dosisComidaDiaria=comida.calcularComida();
    }

    /**
     * Getters para los test
     */

    public Luminosidad.luminosidad getLuminosidad() {
        return luminosidad;
    }

    public Comida getComida() {
        return comida;
    }



    /**
     * toString para cuando se seleccione la opción 6, para visualizar la info de la población
     * @return stringToRepresentPoblacion
     */
    @Override
    public String toString(){
        String stringToRepresentPoblacion = "La población "
                + this.nombrePoblacion +":"
                + "\nCantidad de bacterias inicialmente: "+ this.numInicialBacterias
                + "\nTemperatura a la cual están sometidas las bacterias: "+ this.temperatura
                + "\nLuminosidad: "+this.luminosidad
                + "\nDosis de comida diaria: "+ this.comida.toString();
        return stringToRepresentPoblacion;
    }

    /**
     * Muestra la información de la población separada por ";" (modo fichero)
     * @return stringToRepresentInfoPobFile
     */
    public String toStringInfoPobFile() {
        String stringToRepresentInfoPobFile = this.nombrePoblacion
                + ";" + Integer.toString(this.numInicialBacterias)
                + ";" + this.temperatura
                + ";" + this.luminosidad
                + ";" + comida.toStringToFile();

        return stringToRepresentInfoPobFile;
    }

}


