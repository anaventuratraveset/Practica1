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
    private final int dimension = 20;
    private final int medioBajo = 8;
    private final int medioAlto = 12;

    /**
     * Constructor de la clase Plato
     * @param numBacterias
     * @param comidaInicial
     */
    public Plato(int numBacterias, int comidaInicial) {
        this.plato = new Celda[dimension][dimension];
        inicializarPlato(numBacterias, comidaInicial);
    }


    /**
     * Inicializa el plato de cultivo con las celdas correspondientes.
     * Se encarga de la distribución del dia 0 en el plato de cultivo, las bacterias
     * iniciales se posicionan en los cuadrantes centrales 4x4 celdas a partes
     * iguales y la comida se distribuye también por todo el plato de cultivo 20x20 celdas.
     */
    public void inicializarPlato(int numBacterias, int comidaInicial) {
        //Math.ceil() lo que hace es redondear hacia arriba
        System.out.println("Inicializo el plato de cultivo con " + numBacterias + " bacterias y " + comidaInicial + " microgramos de comida.");
        int bacteriasXcelda = (int)Math.ceil(numBacterias / 16.0); // divido por el número de celdas (4x4 = 16) centrales
        System.out.println("En las celdas centrales debería haber: " + bacteriasXcelda + " bacterias.");
        int comidaXCelda = (int)Math.ceil(comidaInicial / 400.0); // divido por el número de celdas (20x20 = 400)
        System.out.println("Y en todas las celdas debería haber: " + comidaXCelda + " microgramos de comida.\n");

        for (int fila = 0; fila < dimension; fila++) {
            String infoComida = "";
            String infoNumBacterias = "";
            for (int columna = 0; columna < dimension; columna++) {
                if (fila >= medioBajo && fila < medioAlto && columna >= medioBajo && columna < medioAlto) {
                    this.plato[fila][columna] = new Celda(comidaXCelda, bacteriasXcelda); // me inicializa la cantidad de comida y las bacterias vivas en la celda
                    for (int i = 0; i < bacteriasXcelda; i++) {
                        this.plato[fila][columna].anadirBacteria(new Bacteria()); // añado cada bacteria a la lista de bacterias de la celda
                    }
                } else {
                    this.plato[fila][columna] = new Celda(comidaXCelda, 0);
                }
                infoComida += " " + this.plato[fila][columna].getComida();
                infoNumBacterias += " " + this.plato[fila][columna].getListBacterias().size();
                //Esto funciona e imprime lo que tiene que imprimir
            }
            System.out.println("Info comida del dia 1, recién inicializado"  + ", fila " + fila + ": " + infoComida);
            System.out.println("Info numbacterias del dia 1, recién inicializado"  + ", fila " + fila + ": " + infoNumBacterias);
            System.out.println();
        }
    }

    /**
     * constructor de la clase Plato
     */


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
    public int getDimension() {
        return dimension;
    }

}