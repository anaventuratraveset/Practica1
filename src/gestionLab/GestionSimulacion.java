package gestionLab;

import excepciones.ComidaCeldaExcepcion;
import excepciones.FechaExcepcion;
import laboratorio.Bacteria;
import laboratorio.Celda;
import laboratorio.Plato;
import laboratorio.Poblacion;

import java.util.Iterator;
import java.util.ListIterator;

import static java.time.temporal.ChronoUnit.DAYS;


public class GestionSimulacion {

    private Celda[][] arrayCeldas;
    private Integer bacteriasRestantes[][][] = null; // por día, por celda
    private Integer comidaRestante[][][] = null; // por día, por celda
    /**
     * Este método se encarga de la simulación de Montecarlo, en la que se simula el comportamiento de las bacterias en un plato de cultivo.
     *
     * @param p
     * @param miPlato
     */
    public void monteCarlo(Poblacion p, Plato miPlato) throws FechaExcepcion, ComidaCeldaExcepcion {
        int duracion = (int) DAYS.between(p.getFechaInicio(), p.getFechaFin());
        System.out.println("Se está calculando la duración del experimento entre "+p.getFechaInicio() +" y " + p.getFechaFin() + " que es de " + duracion + " días");

        /**
         * Entramos en el bucle de los días
         * */
        for (int dia = 0; dia < duracion; dia++) {
            System.out.println("comida establecida por día, día " +dia+1+ ": "+p.getDosisComidaDiaria()[dia]);
            for (int pasadas = 0; pasadas < 10; pasadas++) {
                for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                    for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                        // añado la comida correspondiente del día a cada celda
                        if (dia != 0 && pasadas == 0) {
                            int comidaXcelda = p.getDosisComidaDiaria()[dia] / 400;
                            miPlato.getCelda(fila,columna).anadirComida(comidaXcelda); // añado la dosis correspondiente del día para cada celda
                        }
                        System.out.println("Número de bacterias inicial en la celda ["+fila+", "+columna+"]: "+miPlato.getCelda()[fila][columna].getListBacterias().size()); // esto siempre me da 0 pero no entiendp pq pq lo estoy inicializando en inicialar plato
                        //miPlato.getPlato().setListBacterias(p.getBacteriasList());
                        ListIterator<Bacteria> iteradorBacterias
                                = miPlato.getCelda()[fila][columna].getListBacterias().listIterator(); // aqui están todas las bacterias que haya en esa celda
                        Bacteria cadaBact = null;
                        while (iteradorBacterias.hasNext()) { //mientras haya bacterias => sigue el bucle
                            System.out.println("Aqui nunca entra en el bucle");
                            cadaBact = iteradorBacterias.next(); // me coge el primero, luego el seg, etc
                            int cantidadAcomer = miPlato.getCelda()[fila][columna].cantidadAcomer();
                            System.out.println("cantidad a comer: "+cantidadAcomer);
                            int aleatorio = cadaBact.contarComidaIngerida(cantidadAcomer);

                            if (cantidadAcomer == 20) {
                                if (aleatorio < 3) {
                                    iteradorBacterias.remove(); // me quita el último seleccionado
                                    //aqui por ejemplo como hay que desplazar a la bacteria una posición a la derecha
                                    // y una posición hacia abajo, si la bacteria ya está en el extremo inferior derecho
                                    // es decir en la posición (19, 19) => se lo salta y se queda en la celda en la que estaba

                                } else if (aleatorio >= 60 && aleatorio < 65 && fila != 0 && columna != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila - 1][columna - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 65 && aleatorio < 70 && fila != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[fila -1 ][columna].anadirBacteria(cadaBact);
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
                        } //bucle de bacterias por celda -> iterador
                    } //columnas del plato
                } //filas del plato
            } //fin de las 10 pasadas

            /**
             * Guardo la info de cada día en la matriz
             * */
            for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                    System.out.println("A ver si me da la comida: "+miPlato.getCelda()[fila][columna].getComida());
                    this.comidaRestante[dia][fila][columna] = miPlato.getCelda()[fila][columna].getComida();
                    this.bacteriasRestantes[dia][fila][columna] = miPlato.getCelda()[fila][columna].getListBacterias().size();
                }
            }
            /**
             * Visualizo la matriz3D con la info de la simulación
             * */
            System.out.println("Día " + (dia + 1) + ":");
            for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                    System.out.print("[" + this.bacteriasRestantes[dia][fila][columna] + ", " + this.comidaRestante[dia][fila][columna] + "] ");
                }
                System.out.println();

                // Reproducción
                // después de las 10 pasadas, si las bacterias siguen vivas =>
                // según lo que hayan comido en todo el dia se reproducen mas o menos
                for ( fila = 0; fila < 20; fila++) {
                    for (int columna = 0; columna < 20; columna++) {
                        int numNuevasBateriasXCelda = 0;
                        Iterator<Bacteria> itBact = miPlato.getCelda()[fila][columna].getListBacterias().iterator();
                        Bacteria bactUnidad = null;
                        while (itBact.hasNext()) {
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
}