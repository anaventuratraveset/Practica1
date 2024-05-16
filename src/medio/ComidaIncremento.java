package medio;

import java.time.LocalDate;
import java.util.Arrays;

public class ComidaIncremento extends ComidaPadre{

    private int cantidadFinal;
    public ComidaIncremento(int cantidadInicial, LocalDate fechaInicial, LocalDate fechaFinal,
                            int cantidadFinal){
        super(cantidadInicial, fechaInicial, fechaFinal);
        this.cantidadFinal = cantidadFinal;
    }

    public void setCantidadFinal(int cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
    }

    public int getCantidadFinal() {
        return cantidadFinal;
    }

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
