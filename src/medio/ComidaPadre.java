package medio;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Arrays;

/**
 * @author Ana Ventura-Traveset
 */
public abstract class ComidaPadre {

    /**
     * Atributos de Comida Padre
     * <p>
     * cantidadInicial
     * cantidadComida
     * fechaInicial
     * duracion
     */
    protected int cantidadInicial;
    protected int[] cantidadComida;
    protected LocalDate fechaInicial, fechaFinal;
    protected int duracion; // Atributo de instancia


    /**
     * Constructor vacío de Comida
     */
    public ComidaPadre() {

    }

    /**
     * Constructor de Comida
     *
     * @param cantidadInicial
     * @param fechaInicial
     * @param fechaFinal
     */
    public ComidaPadre(int cantidadInicial, LocalDate fechaInicial, LocalDate fechaFinal) {
        this.cantidadInicial = cantidadInicial;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.duracion = (int) DAYS.between(fechaInicial, fechaFinal);
        this.cantidadComida = new int[this.duracion]/*this.calcularComida()*/;
    }


    /**
     * Permite modificar la cantidad inicial de comida
     *
     * @param cantidadInicial
     */
    public void setCantidadInicial(int cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    /**
     * Permite modificar la cantidad diaría de comida
     *
     * @param cantidadComida
     */
    public void setCantidadComida(int[] cantidadComida) {
        this.cantidadComida = cantidadComida;
    }

    public int[] getCantidadComida() {
        return cantidadComida;
    }

    /**
     * Permite modificar la fecha inicial cuando se empieza el experimento
     *
     * @param fechaInicial
     */
    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    /**
     * Permite modificar la fecha final cuando se acaba el experimento
     *
     * @param fechaFinal
     */
    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    /**
     * getter para los test
     */

    public int getCantidadInicial() {
        return cantidadInicial;
    }


    public int getDuracion() {
        return duracion;
    }

//    public void setDuracion(int duracion) {
//        this.duracion = duracion;
//    }

    public void setDuracion(LocalDate fechaInicial, LocalDate fechaFinal) {
        this.duracion = (int) DAYS.between(fechaInicial, fechaFinal);
    }


    /**
     * Calcula la cantidad de comida diaria
     *
     * @return
     */
// tengo
    public abstract int[] calcularComida(); // para hacer metodo abstract tiene que ser una clase abstracta
    // este método lo heredan sus clases hijas y lo tienen que implementar o hacerse abstract tbn, q no nos interesa eso
    // se trata de un caso de polimorfismo ya que el método se va a reutilizar pero con diferentes implementaciones => una sola acción de varias maneras

    /**
     * Muestra la información de la comida, incluida la cantidad de comida de cada día
     *
     * @return stringToRepresentComida
     */

// Con los cambios para Practica2, tengo q ver bien los toString() !!!!!
    @Override
    public String toString() {
        String stringToRepresentComida =
                "La duración es de " + this.duracion + " días."
                + "\nEn la fecha de inicio: " + this.fechaInicial + ", cantidad de comida inicial: " + this.cantidadInicial + " microgramos ."
             /* + "\nCantidad de dosis de comida diaria: " + Arrays.toString(cantidadComida)*/;
        return stringToRepresentComida;
    }

    /**
     * Muestra la información de la comida separada por ";" (modo fichero)
     *
     * @return stringToRepresentDosisComidaToFile
     */
    public String toStringToFile() {
        String stringToRepresentDosisComidaToFile = this.fechaInicial + ";" + this.cantidadInicial;
        return stringToRepresentDosisComidaToFile;
    }
}

