package laboratorio;

import excepciones.ComidaCeldaExcepcion;
import excepciones.FechaExcepcion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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
    private List<Bacteria> listBacterias;

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

        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < altura; j++) {
                if (i >= medioBajo && i < medioAlto && j >= medioBajo && j < medioAlto) {
                    this.plato[i][j] = new Celda(comidaXCelda, bacteriasXcelda);
                } else {
                    this.plato[i][j] = new Celda(comidaXCelda, 0);
                }
                System.out.println("Celda inicializada ["+i+ ", " +j+"] con comida: " + this.plato[i][j].getComida() + " y bacterias: " + this.plato[i][j].getBacteriasVivas());
                //Esto funciona e imprime lo que tiene que imprimir
            }
        }
    }

    /**
     * constructores de la clase Plato
     */
    public Plato() {
    }

    public Plato(int numBacterias, int comidaInicial) {
        this.plato = new Celda[ancho][altura];
        inicializarPlato(numBacterias, comidaInicial);

        listBacterias = new LinkedList<Bacteria>();
        for (int i = 0; i < numBacterias; i++) {
            listBacterias.add(new Bacteria());
        }
    }

    /**
     * getters y setters
     * */
    public Celda[][] getPlato() {
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
    public List<Bacteria> getListBacterias() {
        return listBacterias;
    }

    public void setListBacterias(List<Bacteria> listBacterias) {
        this.listBacterias = listBacterias;
    }
}