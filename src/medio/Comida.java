package medio;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Arrays;

/**
 * @author Ana Ventura-Traveset
 */
public class Comida {

    /**
     * Atributos de Comida
     *
     * cantidadInicial
     * cantidadComida
     * fechaInicial
     * fechaFinal
     * cantidadPico
     * cantidadFinal
     * fechaPico
     * duracion
     */
    private float cantidadInicial;
    private float [] cantidadComida;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private float cantidadPico;
    private float cantidadFinal;
    private LocalDate fechaPico;
    private final int duracion=30;

    /**
     * Constructor vacío de Comida
     */
    public Comida() {

    }

    /**
     * Constructor de Comida
     * @param cantidadInicial
     * @param fechaInicial
     * @param cantidadPico
     * @param fechaPico
     * @param cantidadFinal
     * @param fechaFinal
     */
    public Comida (float cantidadInicial, LocalDate fechaInicial, float cantidadPico, LocalDate fechaPico, float cantidadFinal, LocalDate fechaFinal){
        this.cantidadInicial=cantidadInicial;
        this.fechaInicial=fechaInicial;
        this.cantidadPico=cantidadPico;
        this.fechaPico=fechaPico;
        this.cantidadFinal=cantidadFinal;
        this.fechaFinal=fechaFinal;
        this.cantidadComida = this.calcularComida();
    }


    /**
     * Permite modificar la cantidad inicial de comida
     * @param cantidadInicial
     */
    public void setCantidadInicial(float cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    /**
     * Permite modificar la cantidad diaría de comida
     * @param cantidadComida
     */
    public void setCantidadComida(float[] cantidadComida){this.cantidadComida=cantidadComida;}


    /**
     * Permite modificar la fecha inicial cuando se empieza el experimento
     * @param fechaInicial
     */
    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }


    /**
     * Permite modificar la fecha final cuando se acaba el experimento
     * @param fechaFinal
     */
    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    /**
     * Permite modificar la cantidad pico de comida
     * @param cantidadPico
     */
    public void setCantidadPico(float cantidadPico) {
        this.cantidadPico = cantidadPico;
    }

    /**
     * Permite modificar la cantidad final de comida
     * @param cantidadFinal
     */
    public void setCantidadFinal(float cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
    }

    /**
     * Permite modificar la fecha en la que se produce el pido de comida
     * @param fechaPico
     */
    public void setFechaPico(LocalDate fechaPico) {
        this.fechaPico = fechaPico;
    }


    /**
     * getter para los test
     */

    public float getCantidadInicial() {
        return cantidadInicial;
    }

    public float getCantidadPico() {
        return cantidadPico;
    }

    /**
     * Calcula la cantidad de comida diaria
     * @return
     */
    public float[] calcularComida(){
        int diasIncremento =(int) DAYS.between(this.fechaInicial, this.fechaPico);
        int diasDecremento = (int) DAYS.between(this.fechaPico, this.fechaFinal);
        float interseccion= cantidadPico-((cantidadPico-this.cantidadFinal)/diasDecremento);
        float CantidadIncremento= this.cantidadPico - this.cantidadInicial;
        float CantidadDecremento= this.cantidadPico - this.cantidadFinal;
        float cantidadComida[]=new float [duracion];

        for (int i=0; i<diasIncremento; i++){
            cantidadComida[i]=((CantidadIncremento)/diasIncremento)*i+this.cantidadInicial;
        }
        for(int j=diasIncremento; j<duracion; j++){
            cantidadComida[j]= ((CantidadDecremento)/diasDecremento)*j+interseccion;
        }   
        return cantidadComida;
    }

    /**
     * Muestra la información de la comida, incluida la cantidad de comida de cada día
     * @return stringToRepresentComida
     */
    @Override
    public String toString(){
        String stringToRepresentComida =
                "En la fecha de inicio: "+this.fechaInicial +", cantidad de comida inicial: " + this.cantidadInicial
                + "\nEn la fecha de pico: "+this.fechaPico +", cantidad de comida pico: " + this.cantidadPico
                + "\nEn la fecha de fin: "+this.fechaFinal +", cantidad de comida final: " + this.cantidadFinal
                + "\nCantidad de dosis de comida diaria: " + Arrays.toString(cantidadComida);
        return stringToRepresentComida;
    }

    /**
     * Muestra la información de la comida separada por ";" (modo fichero)
     * @return stringToRepresentDosisComidaToFile
     */
    public String toStringToFile(){
        String stringToRepresentDosisComidaToFile = this.fechaInicial
        + ";" + this.cantidadInicial
        + ";" + this.fechaPico
        + ";" + this.cantidadPico
        + ";" + this.fechaFinal
        + ";" + this.cantidadFinal;
       return stringToRepresentDosisComidaToFile;
    }
}
