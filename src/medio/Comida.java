package medio;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Arrays;


public class Comida {

    private float cantidadInicial;
    private float [] cantidadComida;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private float cantidadPico;
    private float cantidadFinal;
    private LocalDate fechaPico;
    private final int duracion=30;

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


    public void setCantidadInicial(float cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }


    public void setCantidadComida(float[] cantidadComida){this.cantidadComida=cantidadComida;}


    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }


    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    public void setCantidadPico(float cantidadPico) {
        this.cantidadPico = cantidadPico;
    }

    public void setCantidadFinal(float cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
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
        float cantidadComida[]=new float [duracion];

        for (int i=0; i<diasIncremento; i++){
            cantidadComida[i]=((CantidadIncremento)/diasIncremento)*i+this.cantidadInicial;
        }
        for(int j=diasIncremento; j<duracion; j++){
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
