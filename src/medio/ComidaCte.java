package medio;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Clase ComidaCte.
 * La cantidad de comida es constante durante toda la duración del experimento
 * @autor Ana Ventura-Traveset
 */
public class ComidaCte extends ComidaPadre {

    /**
     * Constructor de la clase ComidaCte
     * Patrón de cantidad de comida constante durante toda la duración del experimento
     * @param cantidadInicial
     * @param fechaInicial
     * @param fechaFinal
     */
    public ComidaCte(int cantidadInicial, LocalDate fechaInicial, LocalDate fechaFinal) {
        super(cantidadInicial,fechaInicial, fechaFinal);
    }

    /**
     * Asigna la cantidad de comida para cada día del experimento.
     * Esta cantidad es constante para este caso.
     * @return cantidadComida
     */
    @Override
    public int[] calcularComida() {
        for (int i = 0; i <super.duracion; i++) {
            cantidadComida[i] = super.cantidadInicial;
        }
        return cantidadComida;
    }

    /**
     * Muestra la información de la comida, incluida la cantidad de comida de cada día
     * @return stringToRepresentComida
     * */
    @Override
    public String toString(){
        String stringToRepresentComida = "Tipo: Comida Constante\n"
                + super.toString()
                + "\nTiene como fecha final: " + super.fechaFinal
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
                + ";" + super.fechaFinal;
        return stringToRepresentDosisComidaToFile;
    }



}
