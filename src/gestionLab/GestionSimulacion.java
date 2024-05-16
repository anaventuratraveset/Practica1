package gestionLab;

import excepciones.ComidaCeldaExcepcion;
import excepciones.FechaExcepcion;
import laboratorio.Bacteria;
import laboratorio.Celda;
import laboratorio.Plato;
import laboratorio.Poblacion;

import java.util.Iterator;

import static java.time.temporal.ChronoUnit.DAYS;


public class GestionSimulacion {

    private Celda[][] arrayCeldas;
    private Integer bacteriasRestantes[][][]; // por día, por celda
    private Integer comidaRestante[][][]; // por día, por celda


    /**
     * Este método se encarga de la simulación de Montecarlo, en la que se simula el comportamiento de las bacterias en un plato de cultivo.
     *
     * @param p
     * @param miPlato
     */
    public void monteCarlo(Poblacion p, Plato miPlato) throws FechaExcepcion, ComidaCeldaExcepcion {
        int duracion = (int) DAYS.between(p.getFechaInicio(), p.getFechaFin());
        System.out.println("Se está calculando la duración del experimento entre "+p.getFechaInicio() +" y " + p.getFechaFin() + " que es de " + duracion + " días");

        for (int dia = 0; dia < duracion; dia++) {
            System.out.println("comida día " +dia+ ": "+p.getDosisComidaDiaria()[dia]);
            for (int pasadas = 0; pasadas < 10; pasadas++) {
                for (int x = 0; x < miPlato.getAncho(); x++) {
                    for (int y = 0; y < miPlato.getAltura(); y++) {
                        // añado la comida correspondiente del día a cada celda
                        if (dia != 0 && pasadas == 0) {
                            int comidaXcelda = p.getDosisComidaDiaria()[dia] / 400;
                            miPlato.getCelda(x,y).anadirComida(comidaXcelda);
                        }
                        //miPlato.getPlato().setListBacterias(p.getBacteriasList());
                        Iterator<Bacteria> iteradorBacterias
                                = miPlato.getCelda()[x][y].getListBacterias().iterator();
                        Bacteria cadaBact = null;
                        while (iteradorBacterias.hasNext()) { //mientras haya bacterias => sigue el bucle
                            System.out.println("Aqui nunca entra en el bucle");
                            cadaBact = iteradorBacterias.next();
                            int cantidadAcomer = miPlato.getCelda()[x][y].cantidadAcomer();
                            System.out.println("cantidad a comer: "+cantidadAcomer);
                            int aleatorio = cadaBact.contarComidaIngerida(cantidadAcomer);

                            if (cantidadAcomer == 20) {
                                if (aleatorio < 3) {
                                    iteradorBacterias.remove();
                                    //aqui por ejemplo como hay que desplazar a la bacteria una posición a la derecha
                                    // y una posición hacia abajo, si la bacteria ya está en el extremo inferior derecho
                                    // es decir en la posición (19, 19) => se lo salta y se queda en la celda en la que estaba
                                } else if (aleatorio >= 95 && aleatorio < 100 && x != 19 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 85 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 75 && x != 0 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 65 && aleatorio < 70 && x != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 60 && aleatorio < 65 && x != 0 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 75 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 85 && aleatorio < 90 && x != 19 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 90 && aleatorio < 95 && x != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y].anadirBacteria(cadaBact);
                                }
                            } else if (cantidadAcomer == 10) {
                                if (aleatorio > 6) {
                                    iteradorBacterias.remove();
                                } else if (aleatorio >= 90 && aleatorio < 100 && x != 19 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 60 && aleatorio < 70 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 40 && aleatorio < 50 && x != 0 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 30 && aleatorio < 40 && x != 1) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 20 && aleatorio < 30 && x != 0 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 50 && aleatorio < 60 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 80 && x != 19 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 90 && x != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y].anadirBacteria(cadaBact);
                                }
                            } else if (cantidadAcomer == 0) {
                                if (aleatorio < 20) {
                                    iteradorBacterias.remove();
                                } else if (aleatorio >= 95 && aleatorio < 100 && x != 19 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 80 && aleatorio < 85 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 70 && aleatorio < 75 && x != 0 && y != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y + 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 65 && aleatorio < 70 && x != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 60 && aleatorio < 65 && x != 0 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x - 1][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 75 && aleatorio < 80 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 85 && aleatorio < 90 && x != 19 && y != 0) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y - 1].anadirBacteria(cadaBact);
                                } else if (aleatorio >= 90 && aleatorio < 95 && x != 19) {
                                    iteradorBacterias.remove();
                                    miPlato.getCelda()[x + 1][y].anadirBacteria(cadaBact);
                                }
                            } // cierro else de cantidadAcomer
                        } //bucle de bacterias por celda -> iterador
                    } //columnas del plato
                } //filas del plato
            } //fin de las 10 pasadas

            /**
             * Guardo la info de cada día en la matriz
             * */
            for (int x = 0; x < miPlato.getAncho(); x++) {
                for (int y = 0; y < miPlato.getAltura(); y++) {
                    this.comidaRestante[dia][x][y] = miPlato.getCelda()[x][y].getComida(dia);
                    this.bacteriasRestantes[dia][x][y] = miPlato.getCelda()[x][y].getListBacterias().size();
                }
            }
            /**
             * Visualizo la matriz3D con la info de la simulación
             * */
            System.out.println("Día " + (dia + 1) + ":");
            for (int x = 0; x < miPlato.getAncho(); x++) {
                for (int y = 0; y < miPlato.getAltura(); y++) {
                    System.out.print("[" + this.bacteriasRestantes[dia][x][y] + ", " + this.comidaRestante[dia][x][y] + "] ");
                }
                System.out.println();

                // Reproducción
                // después de las 10 pasadas, si las bacterias siguen vivas =>
                // según lo que hayan comido en todo el dia se reproducen mas o menos
                for ( x = 0; x < 20; x++) {
                    for (int y = 0; y < 20; y++) {
                        int numNuevasBateriasXCelda = 0;
                        Iterator<Bacteria> itBact = miPlato.getCelda()[x][y].getListBacterias().iterator();
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
                            miPlato.getCelda()[x][y].anadirBacteria(bactUnidad);
                        }
                    }
                }
            }//dia
        }
    }

//    public static void main(String[] args) throws ComidaCeldaExcepcion {
//        Poblacion pob = new  Poblacion(10, "Poblacion1", 37.5f, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 5), Luminosidad.luminosidad.ALTA, 1);
//        Plato platoCultivo = new Plato(20, 10);
//        GestionSimulacion g = new GestionSimulacion();
//        g.monteCarlo(pob, platoCultivo);
//
//    }
}