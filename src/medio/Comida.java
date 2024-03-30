package medio;
import java.time.LocalDate;
import java.util.Arrays;

import static java.time.temporal.ChronoUnit.DAYS;

public class Comida {

    String stringToRepresentAtributos;
    float cantidadInicial;
    static float [] cantidadComida;
    LocalDate fechaInicial;
    LocalDate fechaFinal;
    float cantidadPico;
    float cantidadFinal;
    LocalDate fechaPico;

    final int duracion=30;

    public Comida() {

    }
    public Comida (float cantidadInicial, LocalDate fechaInicial, float cantidadPico, LocalDate fechaPico, float cantidadFinal, LocalDate fechaFinal){
        this.cantidadInicial=cantidadInicial;
        this.fechaInicial=fechaInicial;
        this.cantidadPico=cantidadPico;
        this.fechaPico=fechaPico;
        this.cantidadFinal=cantidadFinal;
        this.fechaFinal=fechaFinal;
        this.cantidadComida = this.calcularComida();
    }


    public float getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(float cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public float[] getCantidadComida() {
        return cantidadComida;
    }

    public void setCantidadComida(float[] cantidadComida){this.cantidadComida=cantidadComida;}

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public float getCantidadPico() {
        return cantidadPico;
    }

    public void setCantidadPico(float cantidadPico) {
        this.cantidadPico = cantidadPico;
    }

    public float getCantidadFinal() {
        return cantidadFinal;
    }

    public void setCantidadFinal(float cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
    }

    public LocalDate getFechaPico() {
        return fechaPico;
    }

    public void setFechaPico(LocalDate fechaPico) {
        this.fechaPico = fechaPico;
    }

    public float[] calcularComida(){
        int diasIncremento =(int) DAYS.between(this.fechaInicial, this.fechaPico);
        int diasDecremento = (int) DAYS.between(this.fechaPico, this.fechaFinal);
        float interseccion= cantidadPico-((cantidadPico-this.cantidadFinal)/diasDecremento);//funcion f(x)= a*x+b,
        // en este caso a es negativo pq decremento y b es lo que he llamado intersección
        float CantidadIncremento= this.cantidadPico - this.cantidadInicial;
        float CantidadDecremento= this.cantidadPico - this.cantidadFinal;
        float cantidadComida[]=new float [30];

        for (int i=0; i<diasIncremento; i++){
            cantidadComida[i]=((CantidadIncremento)/diasIncremento)*i+this.cantidadInicial;
        }
        for(int j=diasIncremento+1; j<30; j++){
            cantidadComida[j]= ((CantidadDecremento)/diasDecremento)*j+interseccion;
        }
        return cantidadComida;
    }

    @Override
    public String toString(){
        String stringToRepresentComida =
                "En la fecha de inicio: "+this.fechaInicial +", cantidad de comida inicial: " + this.cantidadInicial
                + "\nEn la fecha de pico: "+this.fechaPico +", cantidad de comida pico: " + this.cantidadPico
                + "\nEn la fecha de fin: "+this.fechaFinal +", cantidad de comida final: " + this.cantidadFinal
                + "\nCantidad de dosis de comida diaria: " + Arrays.toString(cantidadComida);
        return stringToRepresentComida;
    }

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
