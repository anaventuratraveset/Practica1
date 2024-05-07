package medio;

import java.time.LocalDate;
import java.util.Arrays;

public class ComidaIntermitente extends ComidaPadre{

    public ComidaIntermitente(int cantidadInicial, LocalDate fechaInicial, LocalDate fechaFinal){
        super(cantidadInicial, fechaInicial, fechaFinal);
    }

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
