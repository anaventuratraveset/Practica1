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

    //Constructor
    //Vacío para poder usar los getters y setters y crear new Polbacion()
    public Poblacion(){

    }

    //Getters y setters
    public String getNombrePoblacion() {
        return nombrePoblacion;
    }

    public void setNombrePoblacion(String nombrePoblacion) {
        this.nombrePoblacion = nombrePoblacion;
    }


    public void setNumInicialBacterias(int numInicialBacterias) {
        this.numInicialBacterias = numInicialBacterias;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setLuminosidad(Luminosidad.luminosidad luminosidad) {
        this.luminosidad = luminosidad;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
        this.dosisComidaDiaria=comida.calcularComida(); //Importante este paso (me ha costado)
    }


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


