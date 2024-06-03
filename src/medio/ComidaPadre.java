package medio;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Clase abstracta ComidaPadre
 * Contiene los atributos y métodos comunes a todas las clases de comida
 * De esta clase heredan las clases ComidaCte, ComidaIntermitente, ComidaPico y ComidaIncremento
 * @author Ana Ventura-Traveset
 */
public abstract class ComidaPadre {
    // hago esta clase abstract pq no tiene sentido instanciarla, no tiene sentido tener un objeto de tipo ComidaPadre
    // lo que tiene es clases hijas, que heredan de esta y son las que se instancian

    /**
     * Atributos de Comida Padre
     * cantidadInicial
     * cantidadComida
     * fechaInicial
     * fechaFinal
     * duracion
     */
    protected int cantidadInicial;
    protected int[] cantidadComida;
    protected LocalDate fechaInicial, fechaFinal;
    protected int duracion;

    /**
     * Constructor de ComidaPadre
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
        this.cantidadComida = new int[this.duracion];
    }

    /**
     * Permite obtener la cantidad diaría de comida
     * @return cantidadComida
     */
    public int[] getCantidadComida() {
        return cantidadComida;
    }

    /**
     * Este método servirá para calcular la cantidad de comida para cada día según el patrón de comida seguido
     * Al ser un método abstract lo heredan sus clases hijas y lo tienen que implementar o hacerse abstract tbn, q no nos interesa eso
     * Se trata de un caso de polimorfismo de sobrescritura ya que el método se va a reutilizar pero con diferentes implementaciones
     * => una sola acción de varias maneras
     *
     * @return
     */
    public abstract int[] calcularComida();

    /**
     * Muestra la información de la comida, incluida la cantidad de comida de cada día
     *
     * @return stringToRepresentComida
     */
    @Override
    public String toString() {
        String stringToRepresentComida =
                "La duración es de " + this.duracion + " días."
                + "\nEn la fecha de inicio: " + this.fechaInicial + ", cantidad de comida inicial: " + this.cantidadInicial + " microgramos .";
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

