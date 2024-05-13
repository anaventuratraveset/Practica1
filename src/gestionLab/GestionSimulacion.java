package gestionLab;

import excepciones.ComidaCeldaExcepcion;
import excepciones.FechaExcepcion;
import laboratorio.Bacteria;
import laboratorio.Plato;
import laboratorio.Poblacion;
import java.time.LocalDate;
import java.util.Iterator;

import static java.time.temporal.ChronoUnit.DAYS;


public class GestionSimulacion {

    /**
     * Este método se encarga de la simulación de Montecarlo, en la que se simula el comportamiento de las bacterias en un plato de cultivo.
     * @param p
     * @param miPlato
     * */
    public static void monteCarlo(Poblacion p, Plato miPlato) throws FechaExcepcion, ComidaCeldaExcepcion {
        int duracion = (int) DAYS.between(p.getFechaInicio(), p.getFechaFin());

        for (int dia = 0; dia < duracion; dia++) {
            if (dia == 0) {
                miPlato.inicializarPlato(p.getNumInicialBacterias(), p.getDosisComidaXDia(0));
            }
            for (int pasadas = 0; pasadas < 10; pasadas++) {
                for (int i = 0; i < miPlato.getAltura(); i++) {
                    for (int j = 0; j < miPlato.getAncho(); j++) {
                        // añado la comida correspondiente del día a cada celda
                        if (dia != 0 && pasadas == 0) {
                            int comidaXcelda = p.getDosisComidaXDia(dia) / 400;
                            miPlato.getPlato()[i][j].anadirComida(comidaXcelda);
                        }
                        Iterator<Bacteria> iteradorBacterias
                                = miPlato.getPlato()[i][j].getListBacterias().iterator();
                        Bacteria cadaBact = null;
                        while (iteradorBacterias.hasNext()) { //mientras haya bacterias => sigue el bucle
                            cadaBact = iteradorBacterias.next();
                            int cantidadAcomer = miPlato.getPlato()[i][j].cantidadAcomer();
                            int aleatorio = cadaBact.contarComidaIngerida(cantidadAcomer);

                            if (cantidadAcomer == 20) {
                                if (aleatorio < 3) {
                                    iteradorBacterias.remove();
                                //aqui por ejemplo como hay que desplazar a la bacteria una posición a la derecha
                                // y una posición hacia abajo, si la bacteria ya está en el extremo inferior derecho
                                // es decir en la posición (19, 19) => se lo salta y se queda en la celda en la que estaba
                                } else if (aleatorio >= 95 && aleatorio < 100 && i != 19 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 85 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 75 && i != 0 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 65 && aleatorio < 70 && i != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 60 && aleatorio < 65 && i != 0 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 75 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 85 && aleatorio < 90 && i != 19 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 90 && aleatorio < 95 && i != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j].anadirBacteria(cadaBact);
                                }
                            } else if (cantidadAcomer == 10) {
                                if (aleatorio > 6) {
                                    iteradorBacterias.remove();
                                } else if (aleatorio >= 90 && aleatorio < 100 && i != 19 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 60 && aleatorio < 70 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 40 && aleatorio < 50 && i != 0 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 30 && aleatorio < 40 && i != 1) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 20 && aleatorio < 30 && i != 0 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 50 && aleatorio < 60 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 80 && i != 19 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 90 && i != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j].anadirBacteria(cadaBact);
                                }
                            } else if (cantidadAcomer == 0) {
                                if (aleatorio < 20) {
                                    iteradorBacterias.remove();
                                } else if (aleatorio >= 95 && aleatorio < 100 && i != 19 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 85 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 75 && i != 0 && j != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 65 && aleatorio < 70 && i != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 60 && aleatorio < 65 && i != 0 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i - 1][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 75 && aleatorio < 80 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 85 && aleatorio < 90 && i != 19 && j != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 90 && aleatorio < 95 && i != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getPlato()[i + 1][j].anadirBacteria(cadaBact);
                                }
                            } // cierro else de cantidadAcomer
                        } //bucle de bacterias por celda -> iterador
                    } //columnas del plato
                } //filas del plato
            } //fin de las 10 pasadas


            // Reproducción
            // después de las 10 pasadas, si las bacterias siguen vivas =>
            // según lo que hayan comido en todo el dia se reproducen mas o menos
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    int numNuevasBateriasXCelda = 0;
                    Iterator<Bacteria> itBact = miPlato.getPlato()[i][j].getListBacterias().iterator();
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
                        miPlato.getPlato()[i][j].anadirBacteria(bactUnidad);
                    }
                }
            }
        }//dia
    }

}