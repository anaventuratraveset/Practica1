package laboratorio;
import medio.Comida;
import medio.Luminosidad;
import java.time.LocalDate;

public class Poblacion {

    private String nombrePoblacion;
    private int numInicialBacterias;
    private float temperatura;
    private LocalDate fechaInicio, fechaFin;
    private Luminosidad.luminosidad luminosidad;
    private Comida comida;
    private float [] dosisComidaDiaria;

    //Constructores
    //Vacío para poder usar los getters y setters y crear new Polbacion()
    public Poblacion(){

    }

   /* public Poblacion(String nombrePoblacion, int numInicialBacterias, float temperatura, LocalDate fechaInicio,
                     Luminosidad.luminosidad luminosidad) {
        this.nombrePoblacion = nombrePoblacion;
        this.numInicialBacterias = numInicialBacterias;
        this.temperatura = temperatura;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusDays(30);
        this.luminosidad = luminosidad;
    }*/

    //Getters y setters
    public String getNombrePoblacion() {
        return nombrePoblacion;
    }

    public void setNombrePoblacion(String nombrePoblacion) {
        this.nombrePoblacion = nombrePoblacion;
    }

   /* public int getNumInicialBacterias() {
        return numInicialBacterias;
    }*/

    public void setNumInicialBacterias(int numInicialBacterias) {
        this.numInicialBacterias = numInicialBacterias;
    }

   /* public float getTemperatura() {
        return temperatura;
    }*/

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

   /* public LocalDate getFechaInicio() {
        return fechaInicio;
    }
*/
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /*public LocalDate getFechaFin() {
        return fechaFin;
    }*/

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /*public Luminosidad.luminosidad getLuminosidad() {
        return luminosidad;
    }*/

    public void setLuminosidad(Luminosidad.luminosidad luminosidad) {
        this.luminosidad = luminosidad;
    }

    /*public Comida getComida() {
        return comida;
    }*/

    public void setComida(Comida comida) {
        this.comida = comida;
        this.dosisComidaDiaria=comida.calcularComida(); //Importante este paso (me ha costado)
    }

   /* public float[] getDosisComidaDiaria() {
        return dosisComidaDiaria;
    }

    public void setDosisComidaDiaria(float[] dosisComidaDiaria) {
        this.dosisComidaDiaria = dosisComidaDiaria;
    }
*/

    //creamos el toString para cuando se seleccione la opción 6, para visualizar la info de la población
    @Override
    public String toString(){ //aqui no es necesario que le pase el nombre de la bacteria?
        String stringToRepresentPoblacion = "La población "
                + this.nombrePoblacion +":"
                + "\nCantidad de bacterias inicialmente: "+ this.numInicialBacterias
                + "\nTemperatura a la cual están sometidas las bacterias: "+ this.temperatura
                + "\nLuminosidad: "+this.luminosidad
                + "\nDosis de comida diaria: "+ this.comida.toString(); //Importante tener entre los atributos de poblacion un tipo Comida comida, para poder usar su método
        return stringToRepresentPoblacion;
    }

    public String toStringInfoPobFile() {
        String stringToRepresentInfoPobFile = this.nombrePoblacion
                + ";" + Integer.toString(this.numInicialBacterias)
                + ";" + this.temperatura
                + ";" + this.luminosidad
                + ";" + comida.toStringToFile();

        return stringToRepresentInfoPobFile;
    }

}


