package gestion;

import laboratorio.Bacteria;
import laboratorio.Plato;
import laboratorio.Poblacion;

import java.util.ListIterator;

import static java.time.temporal.ChronoUnit.DAYS;


public class GestionSimulacion {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";

    public static final String WHITE = "\u001B[37m";
    private int[][][][] simulacion = null; // matriz 4D que guarda la simulación de Montecarlo
    // la primera dimensión es el día, la segunda y tercera dimensión son las filas y columnas del plato
    // y la última dimensión es un array de dos elementos que guarda la cantidad de bacterias y comida en cada celda,
    // siendo la posición del array bidimensional 0, para el num de bacterias y 1 para la cantidad de comida


    /**
     * Este método se encarga de la simulación de Montecarlo, en la que se simula el comportamiento de las bacterias en un plato de cultivo.
     * A este método se le pasan como parámetros la población de bacterias y el plato de cultivo.
     * La población de bacterias contiene la información sobre la cantidad de bacterias iniciales, la duración del experimento y el patrón de comida.
     * El plato de cultivo contiene la información sobre la cantidad de comida inicial y la cantidad de bacterias. El plato ya está inicializado =>
     * la comida y las bacterias están correctamente distribuidas en las celdas como se pide en el enunciado
     *
     * @param p
     * @param miPlato
     */
    public void monteCarlo(Poblacion p, Plato miPlato) throws Exception {
        int duracion = (int) DAYS.between(p.getFechaInicio(), p.getFechaFin());
        System.out.println("Se está calculando la duración del experimento entre " + p.getFechaInicio() + " y " + p.getFechaFin() + " que es de " + duracion + " días");
        simulacion = new int[duracion][miPlato.getDimension()][miPlato.getDimension()][2];

        // Entramos en el bucle de los días
        System.out.println("\n\nMatriz 3D representando la simulación de Montecarlo por días: ");
        for (int dia = 0; dia < duracion; dia++) {
            System.out.println("\nDía " + (dia + 1) + " del experimento");
            System.out.println("Comida establecida para este día según el patrón: " + p.getDosisComidaDiaria()[dia]);
            for (int pasadas = 0; pasadas < 10; pasadas++) {
                for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                    for (int columna = 0; columna < miPlato.getDimension(); columna++) {

                        if (dia != 0 && pasadas == 0) { // al principio de cada día (=> pasada 0), añado la comida correspondiente del día a cada celda
                            int comidaXcelda = p.getDosisComidaDiaria()[dia] / 400;
                            miPlato.getCelda(fila, columna).anadirComida(comidaXcelda); // añado la dosis correspondiente del día (según el patrón al que esté asociada la poblacion de bacterias) para cada celda (por eso divido por 400)
                        }
                        //miPlato.getPlato().setListBacterias(p.getBacteriasList());
                        ListIterator<Bacteria> iteradorBacterias
                                = miPlato.getCelda()[fila][columna].getListBacterias().listIterator(); // aqui están todas las bacterias que haya en esa celda
                        Bacteria cadaBact = null;
                        while (iteradorBacterias.hasNext()) { //mientras haya bacterias => sigue el bucle
                            cadaBact = iteradorBacterias.next(); // me coge el primero, luego el seg, etc
                            int cantidadAcomer = miPlato.getCelda()[fila][columna].cantidadAcomer(); // dependiendo de la cantidad de comida que haya en la celda => la bacteria come más o menos
                            // el método cantidadAComer() se encarga de quitar de la comida que hay en esa celda la cantidad que la bacteria se va a comer
                            int aleatorio = cadaBact.contarComidaIngerida(cantidadAcomer); // genera número aleatorio para saber si la bacteria se muere, se queda en su celda o se desplaza (y a cuál)

                            if (cantidadAcomer == 20) {
                                if (aleatorio < 3) {
                                    iteradorBacterias.remove(); // me quita el último seleccionado
                                } else if (aleatorio >= 60 && aleatorio < 65 && fila != 0 && columna != 0) {
                                    //aqui por ejemplo como hay que desplazar a la bacteria una posición a la izquierda
                                    // y una posición hacia arriba, si la bacteria ya está en el extremo superior izquierdo
                                    // es decir en la posición (o, 9) => se lo salta y se queda en la celda en la que estaba
                                    // ya que no puede salirse del plato
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 65 && aleatorio < 70 && fila != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 75 && fila != 0 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 75 && aleatorio < 80 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 85 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila][columna + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 85 && aleatorio < 90 && fila != 19 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 90 && aleatorio < 95 && fila != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 95 && aleatorio < 100 && fila != 19 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna + 1].anadirBacteria(cadaBact);
                                }
                            } else if (cantidadAcomer == 10) {
                                if (aleatorio < 6) {
                                    iteradorBacterias.remove();
                                } else if (aleatorio >= 20 && aleatorio < 30 && fila != 0 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 30 && aleatorio < 40 && fila != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 40 && aleatorio < 50 && fila != 0 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 50 && aleatorio < 60 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 60 && aleatorio < 70 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila][columna + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 80 && fila != 19 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 90 && fila != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 90 && aleatorio < 100 && fila != 19 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna + 1].anadirBacteria(cadaBact);
                                }
                            } else if (cantidadAcomer == 0) {
                                if (aleatorio < 20) {
                                    iteradorBacterias.remove();
                                } else if (aleatorio >= 60 && aleatorio < 65 && fila != 0 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 65 && aleatorio < 70 && fila != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 75 && fila != 0 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 75 && aleatorio < 80 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 85 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila][columna + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 85 && aleatorio < 90 && fila != 19 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 90 && aleatorio < 95 && fila != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 95 && aleatorio < 100 && fila != 19 && columna != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila + 1][columna + 1].anadirBacteria(cadaBact);
                                }
                            } // cierro else de cantidadAcomer
                        } //fin del bucle while de bacterias por celda -> iterador
                    } //columnas del plato
                } //filas del plato
            } //fin de las 10 pasadas

            // Guardo la info de cada día en la matriz (antes de la reproducción)
            // y la visualizo para cada día
            for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                String infoNumBacteria = "";
                int infoNumBacterias;
                int infoComida;
                for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                    String color = RED;
                    infoNumBacterias = miPlato.getCelda()[fila][columna].getListBacterias().size();
                    infoComida = miPlato.getCelda()[fila][columna].getComida();
                    simulacion[dia][fila][columna][0] = infoNumBacterias;
                    simulacion[dia][fila][columna][1] = infoComida;
                    try {
                        if (simulacion[dia][fila][columna][0] == 0){
                            color = WHITE;
                        } else if (simulacion[dia][fila][columna][0] >= 20){
                            color = RED;
                        } else if(simulacion[dia][fila][columna][0] >= 15){
                            color = PURPLE;
                        } else if(simulacion[dia][fila][columna][0] >= 10){
                            color = BLUE;
                        } else if(simulacion[dia][fila][columna][0] >= 5){
                            color = GREEN;
                        } else if(simulacion[dia][fila][columna][0] >= 1){
                            color = YELLOW;
                        }
                        infoNumBacteria = color +String.format("%03d",simulacion[dia][fila][columna][0]) + "," + String.format("%03d",simulacion[dia][fila][columna][1])+"|" + RESET;
                        System.out.print(infoNumBacteria);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception("Error en la matriz 3D");
                    }
                }
                System.out.println();
            }

            // Reproducción
            // después de las 10 pasadas, si las bacterias siguen vivas =>
            // según lo que hayan comido en todo el dia se reproducen mas o menos
            for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                    int numNuevasBateriasXCelda = 0; // creo e inicializo en número de nuevas bacterias a 0
                    ListIterator<Bacteria> itBact = miPlato.getCelda()[fila][columna].getListBacterias().listIterator();
                    Bacteria bactUnidad = null; // creo e inicializo a null un nuevo objeto Bacteria
                    while (itBact.hasNext()) { // mientras mi iterador tenga bacterias, entra en el bucle. Va cogiendo una a una y analizando su caso
                        bactUnidad = itBact.next();
                        if (bactUnidad.getComidaIngerida() >= 150) {
                            numNuevasBateriasXCelda += 3;
                        } else if (bactUnidad.getComidaIngerida() >= 100 && bactUnidad.getComidaIngerida() < 150) {
                            numNuevasBateriasXCelda += 2;
                        } else if (bactUnidad.getComidaIngerida() >= 50 && bactUnidad.getComidaIngerida() < 100) {
                            numNuevasBateriasXCelda += 1;
                        }
                    }
                    // crea y añade las bacterias hijas a sus respectivas celdas
                    for (int contador = 0; contador < numNuevasBateriasXCelda; contador++) {
                        miPlato.getCelda()[fila][columna].anadirBacteria(bactUnidad);
                    }
                }
            }
        }//dia
    }
}
