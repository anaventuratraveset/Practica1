package laboratorio;

import excepciones.ComidaCeldaExcepcion;

import java.util.LinkedList;
import java.util.List;

public class Celda {
    /**
     * @param comida
     * @param poblacionesList
     * Representa una celda individual en el plato de cultivo, conteniendo información sobre las bacterias presentes y la comida disponible.
     * */
    private int comida;
    private final Celda[][] arrayCeldas = new Celda[20][20];

    private List<Poblacion> poblacionesList;


    /**
     *
     * @param comida
     * @param numeroBacterias
     */
    public Celda(int comida, int numeroBacterias) {

        this.comida = comida;

        poblacionesList = new LinkedList<Poblacion>();
        //puedo hacer LinkedList porque List es una interfaz y LinkedList es una clase que implementa la interfaz List
        for (int i = 0; i < numeroBacterias; i++) {
            poblacionesList.add(new Poblacion());

        }
    }

    public Celda[][] getArrayCeldas() {
        return arrayCeldas;
    }



    /**
     * Devuelve la cantidad de comida de la celda
     *
     * @return int
     */

    public int getComida() {
        return comida;
    }

    /**
     * Establece la cantidad de comida de la celda
     *
     * @param comida
     */
    public void setComida(int comida) {
        this.comida = comida;
    }

    /**
     * Devuelve la lista de poblaciones
     *
     * @return LinkedList
     */
    public List<Poblacion> getPoblacionesList() {
        return poblacionesList;
    }

    /**
     * Añade la poblacion que se le pasa como argumento a la lista
     *
     * @param p
     */
    public void anadirPoblacion(Poblacion p) {
        this.poblacionesList.add(p);
    }

    /**
     * Añade la cantidad de comida que se le pasa como argumento a la cantidad
     * total
     *
     * @param cantidad
     * @return int
     */
    public int anadirComida(int cantidad) {
        int comidaTotalXcelda = this.comida + cantidad;
        return comidaTotalXcelda;
    }

    /**
     * Elimina la bacteria que se le pasa como argumento
     *
     * @param poblacion
     */
    public void eliminarPoblacion(Poblacion poblacion) {

        this.poblacionesList.remove(poblacion);

    }

    /**
     * Elimina la cantidad de comida de la celda que se le pasa como argumento
     *
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
     * Este método se establecen los pasos a seguir para la simulación de Montecarlo.
     * En función de la cantidad de comida en la celda, se ingerirá x comida y esta cantidad será devuelta.
     * @return int
     */
    public int cantidadAcomer() throws ComidaCeldaExcepcion /*tengo que añdir el throws porque estoy llamando a un método que lanza una excepción y no la estoy capturando*/{

        if (this.comida >= 100) {

            int cantidadAingerir = 20;
            eliminarComida(cantidadAingerir);
            return cantidadAingerir;

        } else if (this.comida < 100 && this.comida > 9) {

            int cantidadAingerir = 10;
            eliminarComida(cantidadAingerir);
            return cantidadAingerir;

        } else {
            int cantidadAingerir = 0;
            eliminarComida(cantidadAingerir);
            return cantidadAingerir;
        }
    }

    /**
     * Se encarga de la distribución del dia 0 en el plato de cultivo, las bacterias
     * iniciales se posicionan en los cuadrantes centrales 4x4 celdas a partes
     * iguales y la comida se distribuye también por todo el plato de cultivo 20x20 celdas.
     */
    public void distribucionInicial(Poblacion p) {

        int bacteriasXcelda = p.getNumInicialBacterias();
        //System.out.println("Bacterias x celda" + bacteriasXcelda);

        int comidaXCelda = p.getComida().getCantidadInicial();
        //System.out.println("Comida x celda" + comidaXCelda);

        bacteriasXcelda = bacteriasXcelda / 16;
        comidaXCelda = comidaXCelda / 400;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                if (i >= 8 && i < 12 && j >= 8 && j < 12) {

                    this.arrayCeldas[i][j] = new Celda(comidaXCelda, bacteriasXcelda);

                } else {

                    this.arrayCeldas[i][j] = new Celda(comidaXCelda, 0);
                }
            }
        }
    }


    @Override
    public String toString() {

        String infoCelda = "\nComida: " + comida + "\nLista poblaciones: " + poblacionesList;
        return infoCelda;
    }

}
