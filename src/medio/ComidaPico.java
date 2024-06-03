package medio;

import java.time.LocalDate;
import java.util.Arrays;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Clase ComidaPico.
 * La cantidad de comida incrementa linealmente hasta un valor intermedio "cantidadPico" y decrece linealmente hasta la cantidad final
 * @autor Ana Ventura-Traveset
 */
public class ComidaPico extends ComidaPadre{

    private int cantidadPico, cantidadFinal;
    private LocalDate fechaPico;

    /**
     * Constructor de la clase ComidaPico
     * @param cantidadInicial
     * @param fechaInicial
     * @param cantidadPico
     * @param fechaPico
     * @param cantidadFinal
     * @param fechaFinal
     */
    public ComidaPico(int cantidadInicial, LocalDate fechaInicial,int cantidadPico, LocalDate fechaPico,
                      int cantidadFinal, LocalDate fechaFinal){
        super(cantidadInicial, fechaInicial, fechaFinal);
        this.cantidadPico=cantidadPico;
        this.fechaPico=fechaPico;
        this.cantidadFinal=cantidadFinal;
    }

    /**
     * Patrón incrementar linealmente la cantidad de comida de cada día
     * partiendo de un valor inicial y llegando a un valor intermedio
     * y de ahi decreciendo linealmente hasta un valor y fecha finales
     */
    @Override
        public int[] calcularComida(){
            int diasIncremento =(int) DAYS.between(super.fechaInicial, this.fechaPico);
            int diasDecremento = (int) DAYS.between(this.fechaPico, super.fechaFinal);
            int cantidadIncremento= this.cantidadPico - super.cantidadInicial;
            int cantidadDecremento= this.cantidadPico - this.cantidadFinal;

            for (int i=0; i<diasIncremento; i++){
                cantidadComida[i]=(cantidadIncremento/diasIncremento)*i+super.cantidadInicial;
            }
            for(int j=diasIncremento; j<super.duracion; j++){
                cantidadComida[j]= -(cantidadDecremento/diasDecremento)*(j-diasIncremento)+cantidadPico;
            }
            return cantidadComida;
        }

    /**
     * Muestra la información de la comida, incluida la cantidad de comida de cada día
     * @return stringToRepresentComida
     * */
    @Override
    public String toString(){
        String stringToRepresentComida = "Tipo: Comida Pico\n"
                + super.toString()
                + "\nEn la fecha de pico: "+this.fechaPico +", cantidad de comida pico: " + this.cantidadPico + " microgramos ."
                + "\nEn la fecha de fin: "+super.fechaFinal +", cantidad de comida final: " + this.cantidadFinal + " microgramos ."
                + "\nCantidad de dosis de comida diaria: " + Arrays.toString(cantidadComida);
        return stringToRepresentComida;
    }

    /**
     * Muestra la información de la comida separada por ";" (modo fichero)
     * @return stringToRepresentDosisComidaToFile
     */
    @Override
    public String toStringToFile(){
        String stringToRepresentDosisComidaToFile = super.toStringToFile()
                + ";" + this.fechaPico
                + ";" + this.cantidadPico
                + ";" + super.fechaFinal
                + ";" + this.cantidadFinal;
        return stringToRepresentDosisComidaToFile;
    }

}
