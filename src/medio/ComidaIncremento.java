package medio;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Clase ComidaIncremento.
 * La cantidad de comida incrementa linealmente durante la duración del experimento
 * @autor Ana Ventura-Traveset
 */
public class ComidaIncremento extends ComidaPadre{

    private int cantidadFinal;
    /**
     * Constructor de la clase ComidaIncremento
     * @param cantidadInicial
     * @param fechaInicial
     * @param fechaFinal
     * @param cantidadFinal
     */
    public ComidaIncremento(int cantidadInicial, LocalDate fechaInicial, LocalDate fechaFinal,
                            int cantidadFinal){
        super(cantidadInicial, fechaInicial, fechaFinal);
        this.cantidadFinal = cantidadFinal;
    }

    /**
     * Permite calcular la cantidad de comida para cada día.
     * En este caso, la cantidad de comida incrementa linealmente
     * @return cantidadComida
     */
    @Override
    public int[] calcularComida(){
        int cantidadIncremento = cantidadFinal - cantidadInicial;
        for (int i=0; i<super.duracion; i++){
            cantidadComida [i] = (cantidadIncremento / super.duracion) * i + cantidadInicial;
        }
        return cantidadComida;
    }

    /**
     * Muestra la información de la comida, incluida la cantidad de comida de cada día
     * @return stringToRepresentComida
     * */
    @Override
    public String toString(){
        String stringToRepresentComida = "Tipo: Comida Incremento\n"
                + super.toString()
                + "\nEn la fecha de fin: " + super.fechaFinal + ", cantidad de comida final: " + this.cantidadFinal + " microgramos ."
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
                + ";" + super.fechaFinal + ";" + this.cantidadFinal;
        return stringToRepresentDosisComidaToFile;
    }
}
