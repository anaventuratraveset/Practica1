package ui;

import excepciones.ComidaCeldaExcepcion;
import gestionLab.GestionSimulacion;
import laboratorio.Celda;
import laboratorio.Plato;
import laboratorio.Poblacion;
import medio.Luminosidad;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

// Lo ponemos en el paquete ui pq Simulacion forma parte de la capa de presentación del software, interactuando directamente con el usuario.
// Ventana que muestra el resultado de las simulaciones diarias, posiblemente con una representación gráfica del plato de cultivo.
public class VistaSimulacion {
    public static int [][][] infoSimulacion (Poblacion p, Plato plato) {
        int numDias = (int) DAYS.between(p.getFechaInicio(), p.getFechaFin());
        int altura = plato.getAltura();
        int ancho = plato.getAncho();
        int[][][] infoSimulacion = new int[numDias][altura][ancho];

        for (int indexDia = 0; indexDia < numDias; indexDia++) {
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < ancho; j++) {
                    infoSimulacion[indexDia][i][j] = plato.getCelda(i,j).getComida();
                    //aqui tengo la comida que queda en la celda
                    infoSimulacion[indexDia][i][j] = plato.getCelda(i,j).getListBacterias().size();
                    //aqui tengo el numero de bacterias que hay en la celda
                    System.out.println(infoSimulacion[indexDia][i][j]);
                    // pero cómo hago para ponerle ambos valores en una misma posición (num bact y comida)?
                }
            }
        }
        System.out.println("Visualización de la simulación");
        // Visualizo la matriz tridimensional de la simulación
        for (int dia = 0; dia < numDias; dia++) {
            System.out.println("Día " + (dia + 1) + ":");
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < ancho; j++) {
                    System.out.print("[" + infoSimulacion[dia][i][j] + "] ");
                }
                System.out.println();
            }
            System.out.println();
        }
        return infoSimulacion;
    }

//    public static void main(String[] args) throws ComidaCeldaExcepcion {
//        Poblacion pob = new  Poblacion(10, "Poblacion1", 37.5f, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 5), Luminosidad.luminosidad.ALTA, 1);
//        Plato platoCultivo = new Plato(20, 10);
//        GestionSimulacion.monteCarlo(pob, platoCultivo);
//        infoSimulacion(pob, platoCultivo);
//    }


}
