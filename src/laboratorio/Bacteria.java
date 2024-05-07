package laboratorio;

public class Bacteria {

    private int comidaIngerida;
    private int x, y; //coordenadas de la celda en la que se encuentra

    /**
     *
     * @param comidaIngerida
     */
    /**
     * Constructor por defecto de Bacteria
     * */
    public Bacteria() {
        this.comidaIngerida = 0;
    }

    /**
     * Muestra el valor de la comida ingerida por una bacteria
     *
     * @return int
     */
    public int getComidaIngerida() {
        return comidaIngerida;
    }

    public String toString() {

        String texto = "\nComida Ingerida: " + comidaIngerida;
        return texto;
    }

    /**
     * Cuenta la cantidad de comida ingerida por la bacteria, sumándole el valor
     * que se le pasa como argumento
     *
     * @param comidaAIngerir
     * @return
     */
    public int generadorNumAleatorio (int comidaAIngerir) {
        this.comidaIngerida = this.comidaIngerida + comidaAIngerir;
        int aleatorio = (int) (Math.random() * 101);
        return aleatorio;

    }


}
