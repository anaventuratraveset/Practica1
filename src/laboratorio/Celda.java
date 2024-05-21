package laboratorio;

import excepciones.ComidaCeldaExcepcion;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Celda {
    /**
     * @param comida
     * @param numBacterias
     * Representa una celda individual en el plato de cultivo, conteniendo información sobre las bacterias presentes y la comida disponible.
     * Además, gestiona la simulación de Montecarlo para las bacterias en una única celda.
     */
    private int comida;
    private List<Bacteria> listBacterias = new LinkedList<Bacteria>();
    private int bacteriasVivas;
    private Poblacion poblacion;

    public Celda (int comida, int bacteriasVivas){
        this.comida = comida;
        this.bacteriasVivas = bacteriasVivas;
    }
    /**
     * Método que´cuando es un día nuevo, añade a la comida actual (del dia anterior - lo que se haya comido) de la celda la comida que corresponda para ese nuevo día
     * esta cantidad de comida varía según la población, el patrón de comida y el día
     * */
    public void anadirComida(int cantidad) {
        this.comida += cantidad;
    }

    /**
     * Devuelve la lista de bacterias
     *
     * @return LinkedList
     */
    public List<Bacteria> getListBacterias() {
        return listBacterias;
    }
    /**
     * Método que añade una bacteria a la lista de bacterias
     * */
    public void anadirBacteria(Bacteria bacteria) {
        listBacterias.add(bacteria);
    }
    /**
     * No creo un método que elimina una bacteria de la lista de bacterias
     * porque en gestionSimulacion se elimina la bacteria de la lista de bacterias mediante iteradorBacterias.remove();
     * */

    /**
     * Devuelve la cantidad de comida de la celda
     * @return int
     */
    public int getComida() {
        return comida;
    }
    public int getBacteriasVivas (){
        return this.bacteriasVivas;
    }

    /**
     * Establece la cantidad de comida de la celda
     * @param comida
     */
    public void setComida(int comida){
        this.comida = comida;
    }

    /**
     * Elimina la cantidad de comida de la celda que se le pasa como argumento
     * @param cantidadEliminar
     * @return
     * @throws ComidaCeldaExcepcion
     */
    public int eliminarComida(int cantidadEliminar) throws ComidaCeldaExcepcion {
        if (this.comida - cantidadEliminar < 0) {
            throw new ComidaCeldaExcepcion("ERROR. La cantidad de comida a eliminar es mayor a la contenida en la celda");
        } else {
            this.comida = this.comida - cantidadEliminar;
        }
        return this.comida;
    }

    /**
     * Este método en función de la cantidad de comida que haya en la celda, se ingerirá x comida
     * y esta cantidad se eliminará de la cantidad de comida total de la celda (se actualiza)
     * y la cantidad ingerida será devuelta ya que se utiliza en el método montecarlo()
     * de la clase Plato.
     * @return int
     */
    public int cantidadAcomer()throws ComidaCeldaExcepcion {
        /*tengo que añdir el throws porque estoy llamando a un método que lanza una excepción y no la estoy capturando*/
        int cantidadAIngerir;
    if (this.comida >= 100) {
            cantidadAIngerir = 20;
            eliminarComida(cantidadAIngerir);
            return cantidadAIngerir;
        } else if (this.comida < 100 && this.comida > 9) {
             cantidadAIngerir = 10;
            eliminarComida(cantidadAIngerir);
            return cantidadAIngerir;
        } else {
            cantidadAIngerir = 0;
            return cantidadAIngerir;
        }
    }

    @Override
    public String toString () {
        return "Celda{" +
                "comida:" + comida +" ,numBacterias:" + bacteriasVivas +
                '}';
    }

}


