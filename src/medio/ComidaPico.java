package medio;

import java.time.LocalDate;
import java.util.Arrays;

import static java.time.temporal.ChronoUnit.DAYS;

public class ComidaPico extends ComidaPadre{

    /**
     * fechaFinal
     * cantidadPico
     * cantidadFinal
     * fechaPico
     */

    private int cantidadPico, cantidadFinal;
    private LocalDate fechaPico;

    public ComidaPico(int cantidadInicial, LocalDate fechaInicial,int cantidadPico, LocalDate fechaPico,
                      int cantidadFinal, LocalDate fechaFinal){
        super(cantidadInicial, fechaInicial, fechaFinal);
        this.cantidadPico=cantidadPico;
        this.fechaPico=fechaPico;
        this.cantidadFinal=cantidadFinal;
    }


    /**
     * Permite modificar la cantidad pico de comida
     * @param cantidadPico
     */
    public void setCantidadPico(int cantidadPico) {
        this.cantidadPico = cantidadPico;
    }

    public int getCantidadPico() {
        return cantidadPico;
    }
    /**
     * Permite modificar la cantidad final de comida
     * @param cantidadFinal
     */
    public void setCantidadFinal(int cantidadFinal) {
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
     * Patrón incrementar linealmente la cantidad de comida de cada día
     * partiendo de un valor inicial y llegando a un valor intermedio
     * y de ahi decreciendo linealmente hasta un valor y fecha finales
     */
    @Override
        public int[] calcularComida(){
//            System.out.println("Calculando comida pico");
//            System.out.println("fecha inicial: "+super.fechaInicial);
//            System.out.println("fecha final: "+super.fechaFinal);
//            System.out.println("fecha pico: "+this.fechaPico);
            int diasIncremento =(int) DAYS.between(super.fechaInicial, this.fechaPico);
            int diasDecremento = (int) DAYS.between(this.fechaPico, super.fechaFinal);
            int interseccion= cantidadPico-((cantidadPico-this.cantidadFinal)/diasDecremento);
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
        /* Esto equivale a Arrays.toString(cantidadComida) ????
        for (int i=0; i<super.duracion; i++){
            stringToRepresentComida+= "\nEn el día "+i+" la cantidad de comida es: "+this.cantidadComida[i];
        }*/
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
