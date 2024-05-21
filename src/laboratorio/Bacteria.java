package laboratorio;

public class Bacteria {
    /**
     *
     * @param comidaIngerida
     */
    private int comidaIngerida;
    private int x, y; //coordenadas de la celda en la que se encuentra

    /**
     * Constructor por defecto de Bacteria
     * */
    public Bacteria() {
    }
    public Bacteria(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getters y setters de los atributos privados de la clase Bacteria
     * comida ingerida, x e y
     * */
    public void setComidaIngerida(int comidaIngerida) {
        this.comidaIngerida = comidaIngerida;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
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
     * Se va acumulando la cantidad de comida ingerida por la bacteria a lo largo de las pasadas, para que al final del día,
     * según la cantidad ingerida se sepa cuanto ha de reproducirse.
     *
     * Además este método, además de sumar la cantidad de comida ingerida, devuelve un número aleatorio que se utiliza en montecarlo para saber cual será su comportamiento (morir, quedarse, desplazarse)
     * @param cantidaComida
     * @return
     */
    public int contarComidaIngerida(int cantidaComida) {
        this.comidaIngerida = this.comidaIngerida + cantidaComida;
        int aleatorio = (int) (Math.random() * 101);
        return aleatorio;
    }

    public String toString() {
        String texto = "\nComida Ingerida: " + comidaIngerida;
        return texto;
    }
}
