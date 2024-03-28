package laboratorio;
import medio.Comida;
import medio.Luminosidad;
import java.time.LocalDate;

public class Poblacion {

    protected String nombrePoblacion;
    protected int numInicialBacterias;
    protected float temperatura;
    protected LocalDate fechaInicio, fechaFin;
    protected Luminosidad.luminosidad luminosidad; // llamo al enum luminosidad de la clase Luminosidad

    protected Comida comida;
    protected float [] dosisComidaDiaria;
    protected final int comidaMax=300;

    //Constructores
    //Vacío para poder usar los getters y setters y crear new Polbacion()
    public Poblacion(){

    }

    public Poblacion(String nombrePoblacion, int numInicialBacterias, float temperatura, LocalDate fechaInicio,
                     LocalDate fechaFin, Luminosidad.luminosidad luminosidad) {
        this.nombrePoblacion = nombrePoblacion;
        this.numInicialBacterias = numInicialBacterias;
        this.temperatura = temperatura;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.luminosidad = luminosidad;
    }

    //Getters y setters
    public String getNombrePoblacion() {
        return nombrePoblacion;
    }

    public void setNombrePoblacion(String nombrePoblacion) {
        this.nombrePoblacion = nombrePoblacion;
    }

    public int getNumInicialBacterias() {
        return numInicialBacterias;
    }

    public void setNumInicialBacterias(int numInicialBacterias) {
        this.numInicialBacterias = numInicialBacterias;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
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

    public Luminosidad.luminosidad getLuminosidad() {
        return luminosidad;
    }

    public void setLuminosidad(Luminosidad.luminosidad luminosidad) {
        this.luminosidad = luminosidad;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
        this.dosisComidaDiaria=comida.calcularComida(); //Importante este paso (me ha costado)
    }

    public float[] getDosisComidaDiaria() {
        return dosisComidaDiaria;
    }

    public void setDosisComidaDiaria(float[] dosisComidaDiaria) {
        this.dosisComidaDiaria = dosisComidaDiaria;
    }

    public int getComidaMax() {
        return comidaMax;
    }

    //creamos el toString para cuando se seleccione la opción 6, para visualizar la info de la población
    public String toString(){ //aqui no es necesario que le pase el nombre de la bacteria?
        String stringToRepresentPoblacion = "La población "
                + this.nombrePoblacion +":"
                +"\nFecha de inicio: "+this.fechaInicio
                + "\nFecha de fin: "+ this.fechaFin
                + "\nCantidad de bacterias inicialmente: "+ this.numInicialBacterias
                + "\nTemperatura a la cual están sometidas las bacterias: "+ this.temperatura
                + "\nLuminosidad: "+this.luminosidad
                + "\nDosis de comida diaria: "+ comida.toString(); //Importante tener entre los atributos de poblacion un tipo Comida comida, para poder usar su método
        return stringToRepresentPoblacion;
    }

    public String toStringInfoPobFile(String nombrePoblacion) {
        String stringToRepresentInfoPobFile = this.nombrePoblacion
                + "; " + this.fechaInicio
                + "; " + this.fechaFin
                + "; " + this.numInicialBacterias
                + "; " + this.temperatura
                + "; " + this.luminosidad
                + "; " + comida.toString();

        return stringToRepresentInfoPobFile;
    }


}
