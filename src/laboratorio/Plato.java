package laboratorio;

public class Plato {
    /**
     * Esta clase representa el plato de cultivo con las celdas donde se desarrollan las bacterias.
     *
     * @param ancho
     * @param altura
     * @param plato
     * @param medioBajo
     * @param medioAlto
     * @param listBacterias
     */

    private Celda[][] plato;
    private final int ancho = 20;
    private final int altura = 20;
    private final int medioBajo = 8;
    private final int medioAlto = 12;

    /**
     * Inicializa el plato de cultivo con las celdas correspondientes.
     * Se encarga de la distribución del dia 0 en el plato de cultivo, las bacterias
     * iniciales se posicionan en los cuadrantes centrales 4x4 celdas a partes
     * iguales y la comida se distribuye también por todo el plato de cultivo 20x20 celdas.
     */
    public void inicializarPlato(int numBacterias, int comidaInicial) {
        //Math.ceil() lo que hace es redondear hacia arriba
        int bacteriasXcelda = (int)Math.ceil(numBacterias / 16.0); // divido por el número de celdas (4x4 = 16) centrales
        int comidaXCelda = (int)Math.ceil(comidaInicial / 400.0); // divido por el número de celdas (20x20 = 400)

        for (int y = 0; y < ancho; y++) {
            for (int x = 0; x < altura; x++) {
                if (y >= medioBajo && y < medioAlto && x >= medioBajo && x < medioAlto) {
                    this.plato[y][x] = new Celda(comidaXCelda, bacteriasXcelda);
                } else {
                    this.plato[y][x] = new Celda(comidaXCelda, 0);
                }
                System.out.println("Celda inicializada ["+y+ ", " +x+"] con comida: " + this.plato[y][x].getComida() + " y bacterias: " + this.plato[y][x].getBacteriasVivas());
                //Esto funciona e imprime lo que tiene que imprimir
            }
        }
    }

    /**
     * constructor de la clase Plato
     */

    public Plato(int numBacterias, int comidaInicial) {
        this.plato = new Celda[ancho][altura];
        inicializarPlato(numBacterias, comidaInicial);
    }

    /**
     * getters y setters
     * */
    public Celda[][] getCelda() {
        return plato;
    }
    public void setPlato(Celda[][] plato) {
        this.plato = plato;
    }
    public Celda getCelda (int x, int y){
        return this.plato[x][y];
    }
    public int getAncho() {
        return ancho;
    }
    public int getAltura() {
        return altura;
    }

}