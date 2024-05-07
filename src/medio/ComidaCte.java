package medio;

import java.time.LocalDate;
import java.util.Arrays;

public class ComidaCte extends ComidaPadre {
    // Class 'ComidaCte' must either be declared abstract
    // or implement abstract method 'calcularComida()' in 'ComidaPadre'
    // bc class 'ComidaPadre' is abstract
    // the same for all the 'ComidaS'

    public ComidaCte(int cantidadInicial, LocalDate fechaInicial, LocalDate fechaFinal) {
        super(cantidadInicial,fechaInicial, fechaFinal);
    }

    //Patrón de cantidad de comida constante durante toda la duración del experimento
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
