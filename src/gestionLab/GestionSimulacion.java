package gestionLab;

import excepciones.ComidaCeldaExcepcion;
import excepciones.FechaExcepcion;
import laboratorio.Bacteria;
import laboratorio.Celda;
import laboratorio.Plato;
import laboratorio.Poblacion;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.ListIterator;

import static java.time.temporal.ChronoUnit.DAYS;


public class GestionSimulacion {

    // tengo que guardar en un archivo la matriz 3D
    private Celda[][] arrayCeldas;
    private int [][][] bacteriasRestantes = null; // por día, por celda
    private int [][][] comidaRestante = null; // por día, por celda
    /**
     * Este método se encarga de la simulación de Montecarlo, en la que se simula el comportamiento de las bacterias en un plato de cultivo.
     * A este método se el pasan como argumentos la población de bacterias y el plato de cultivo.
     * La población de bacterias contiene la información sobre la cantidad de bacterias iniciales, la duración del experimento y el patrón de comida.
     * El plato de cultivo contiene la información sobre la cantidad de comida inicial y la cantidad de bacterias. El plato ya está inicializado =>
     * la comida y las bacterias están correctamente distribuidas en las celdas como se pide en el enunciado
     *
     * .
     *
     * @param p
     * @param miPlato
     */
    public String monteCarlo(Poblacion p, Plato miPlato) throws FechaExcepcion, ComidaCeldaExcepcion {
        int duracion = (int) DAYS.between(p.getFechaInicio(), p.getFechaFin());
        System.out.println("Se está calculando la duración del experimento entre "+p.getFechaInicio() +" y " + p.getFechaFin() + " que es de " + duracion + " días");
        bacteriasRestantes = new int[duracion][miPlato.getDimension()][miPlato.getDimension()];
        comidaRestante = new int[duracion][miPlato.getDimension()][miPlato.getDimension()];
        /**
         * Entramos en el bucle de los días
         * */
        for (int dia = 0; dia < duracion; dia++) {
            System.out.println("Día " + (dia + 1) + " del experimento");
            System.out.println("Comida establecida para este día según el patrón: "+p.getDosisComidaDiaria()[dia]);
            for (int pasadas = 0; pasadas < 10; pasadas++) {
                System.out.println("\n\n=== Pasada " + (pasadas + 1) + " del día " + (dia + 1) + " ===");
                for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                    for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                        // añado la comida correspondiente del día a cada celda
                        if (dia != 0 && pasadas == 0) {
                            int comidaXcelda = p.getDosisComidaDiaria()[dia] / 400;
                            miPlato.getCelda(fila,columna).anadirComida(comidaXcelda); // añado la dosis correspondiente del día (según el patrón al que esté asociada la poblacion de bacterias) para cada celda (por eso divido por 400)
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
                        } //fin del bucle while de bacterias por celda -> iterador
                    } //columnas del plato
                } //filas del plato
            } //fin de las 10 pasadas


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
            // Guardo la info de cada día en la matriz
            // lo he hecho en tipo String para que se vea mejor esta parte
            for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                String infoComida = "";
                String infoNummBacterias = "";
                for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                    infoComida += " " + miPlato.getCelda()[fila][columna].getComida();
                    infoNummBacterias += " " + miPlato.getCelda()[fila][columna].getListBacterias().size();
                    this.comidaRestante[dia][fila][columna] = miPlato.getCelda()[fila][columna].getComida();
                    this.bacteriasRestantes[dia][fila][columna] = miPlato.getCelda()[fila][columna].getListBacterias().size();
                }
                System.out.println("Info comida del dia " + (dia + 1) + " en la fila " + fila + ": " + infoComida);
                System.out.println("Info num bacterias del dia " + (dia + 1) + " en la fila " + fila + ": " + infoNummBacterias);
                System.out.println();
            }
            //antes estaba aqui la visualización de la matriz 3D
        }//dia

        // visualización de la matriz 3D de Montecarlo
        System.out.println("\n\nMatriz 3D representando la simulación de Montecarlo por días: ");
        for (int dia = 0; dia < duracion; dia++) {
            System.out.println("\nDía " + (dia + 1));
            for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                    System.out.print("[" + this.bacteriasRestantes[dia][fila][columna] + ", " + this.comidaRestante[dia][fila][columna] + "] ");
                }
                System.out.println();
            }
        }

        // visualización de la matriz 3D que representa la cantidad de bacterias que quedan en cada celda el último día del experimento
        System.out.println("\n\nMatriz representando la cantidad de bacterias que quedan en cada celda el último día del experimento: ");
        JPanel panel = new JPanel(new GridLayout(miPlato.getDimension(), miPlato.getDimension()));
        String stringDevolver = "";
        for (int fila = 0; fila < miPlato.getDimension(); fila++) {
                for (int columna = 0; columna < miPlato.getDimension(); columna++) {
                    System.out.print("[" + this.bacteriasRestantes[duracion-1][fila][columna] + "] ");
                    stringDevolver += " " + String.format("%02d",  this.bacteriasRestantes[duracion-1][fila][columna] );
                    // el %o2d es para que se vea mejor la matriz, pq obliga a que todos los números tengan 2 dígitos
                    JLabel label = new JLabel(String.valueOf(this.bacteriasRestantes[duracion-1][fila][columna]));
                    panel.add(label);
                }
                System.out.println();
            stringDevolver += "\n";
            }
        return stringDevolver;
    }
}
