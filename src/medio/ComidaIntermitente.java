package medio;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Clase ComidaIntermitente.
 * La cantidad de comida proporcionada inicialmente es
 * constante durante todo el experimento un día, y al siguiente no proporcionar comida, al
 * siguiente se le proporciona, y al siguiente no se le proporciona, y así sucesivamente
 * @autor Ana Ventura-Traveset
 */
public class ComidaIntermitente extends ComidaPadre{

    /**
     * Constructor de la clase ComidaIntermitente
     * @param cantidadInicial
     * @param fechaInicial
     * @param fechaFinal
     */
    public ComidaIntermitente(int cantidadInicial, LocalDate fechaInicial, LocalDate fechaFinal){
        super(cantidadInicial, fechaInicial, fechaFinal);
    }

    /**
     * Permite calcular la cantidad de comida para cada día.
     * En este caso, la cantidad de comida es constante pero de manera intermitente
     * => un día sí y un día no se proporciona esa cantidad. Para el resto de días, no se proporciona comida.
     * @return cantidadComida
     */
    @Override
    public int[] calcularComida() {
        for (int i = 0; i < cantidadComida.length; i++) {
            if (i % 2 == 0) {
                cantidadComida[i] = super.cantidadInicial;
            } else {
                cantidadComida[i] = 0;
            }
        }
        return cantidadComida;
    }

    /**
     * Muestra la información de la comida, incluida la cantidad de comida de cada día
     * @return stringToRepresentComida
     * */
    @Override
    public String toString(){
        String stringToRepresentComida = "Tipo: Comida Intermitente\n"
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
