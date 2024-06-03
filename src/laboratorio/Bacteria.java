package laboratorio;

/**
 * Clase Bacteria
 * Esta clase representa a una bacteria,
 * la cual tiene un atributo privado que es la cantidad de comida ingerida
 * @author Ana Ventura-Traveset
 * */
public class Bacteria {
    /**
     *
     * @param comidaIngerida
     */
    private int comidaIngerida;

    /**
     * Constructor por defecto de Bacteria
     * */
    public Bacteria() {
    }

    /**
     * Pone la cantidad de comida ingerida por una bacteria
     * @param comidaIngerida
     * @return void
     * */
    public void setComidaIngerida(int comidaIngerida) {
        this.comidaIngerida = comidaIngerida;
    }

    /**
     * Muestra el valor de la comida ingerida por una bacteria
     * Esta información se usa en la clase GestionSimulacion
     * para saber según la cantidad que haya INGERIDO, cuantas veces se reproduce
     * @return int
     */
    public int getComidaIngerida() {
        return comidaIngerida;
    }

    /**
     * Cuenta la cantidad de comida ingerida por la bacteria, sumándole el valor
     * que se le pasa como argumento.
     * Se va acumulando la cantidad de comida ingerida por la bacteria a lo largo de las pasadas,
     * para que al final del día,
     * según la cantidad ingerida se sepa cuanto ha de reproducirse.
     *
     * Además, este método, además de sumar la cantidad de comida ingerida, devuelve un número
     * aleatorio que se utiliza en montecarlo para saber cuál será su comportamiento
     * (morir, quedarse, desplazarse)
     * @param cantidaComida
     * @return
     */
    public int contarComidaIngerida(int cantidaComida) {
        this.comidaIngerida = this.comidaIngerida + cantidaComida;
        int aleatorio = (int) (Math.random() * 101);
        return aleatorio;
    }

}
