package gestionLab;
import dataio.UserInput;
import excepciones.ComidaMaxExcepcion;
import excepciones.FechaExcepcion;
import laboratorio.Bacteria;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.*;
import ordenacion.OrdenacionAlfabetica;
import ordenacion.OrdenacionCronologica;
import ordenacion.OrdenacionCuantitativa;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static dataio.UserInput.readFloat;
import static dataio.UserInput.readInt;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Ana Ventura-Traveset
 */

/**
 * Gestion del laboratorio (crear, añadir, borrar y buscar poblaciones)
 */
public class GestionLab {

    /**
     *
     * CREAR población
     * @param e
     * @return
     * @throws Exception
     * @throws ParseException
     */
    public static Poblacion createPoblacion(Experimento e) throws Exception, ParseException {
        Poblacion p= new Poblacion();
        //int dias=30;

        String nombreP = UserInput.readString("Escriba el nombre de su nueva población: ");
        p.setNombrePoblacion(nombreP);

        float temp = UserInput.readInt("Escriba la temperatura: ");
        p.setTemperatura(temp);

        Luminosidad.luminosidad lum = UserInput.readLuminosidad("Escriba el nivel de luminosidad {ALTA, MEDIA, BAJA}: ");
        p.setLuminosidad(lum);

        /*
        * Comida
        * */
        System.out.println("Patrones de comida: " +
                "\n1. Patrón de incremento lineal seguido de decremento lineal." +
                "\n2. Patrón de cantidad de comida constante durante toda la duración del experimento." +
                "\n3. Patrón de incremento lineal de la cantidad de comida." +
                "\n4. Patrón con cantidad de comida constante durante todo el experimento un día, y al siguiente no proporcionar comida y así sucesivamente" );

        int numPatronComida;

        do {
            numPatronComida = readInt("Escriba el número del patrón de comida que desea: ");
            if (numPatronComida < 1 || numPatronComida > 4) {
                System.out.println("El patrón de comida seleccionado no es correcto.");
            }
        } while (numPatronComida < 1 || numPatronComida > 4);
        p.setNumeroPatronComida(numPatronComida);

        //Pido todos los cosos de la clase ComidaPadre
        int cantidadInicial;
        LocalDate fechaInicial, fechaFinal;

        System.out.println("La cantidad de comida ha de ser entre 0-300000 microgramos).");
        while (true) {
            cantidadInicial = readInt("Introduzca la cantidad de comida inicial: "); //he importado la clase y su método para poder usarlo pq es static el método
            if (cantidadInicial < 400) {
                System.out.println("La cantidad de comida no puede ser menos de 400 microgramos.");
            } else if (cantidadInicial > p.getComidaMax()) {
                try {
                    throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido (300000 microgramos.");
                } catch (ComidaMaxExcepcion ex) {
                    //sirve para avisar de que ha ido mal la excepción, en caso de que asi sea
                    ex.printStackTrace();
                }
            } else {
                break;
            }
        }

        //Para controlar que fecha de inicio no sea después que la de fin
        fechaInicial= UserInput.readDate("Introduzca la fecha donde empieza su experimento (yyyy.MM.dd): ");
        fechaFinal = UserInput.readDate("Introduzca la fecha donde termina su experimento (yyyy.MM.dd): ");
        while (true) {
            if (fechaFinal.isBefore(fechaInicial)) {
                try {
                    throw new FechaExcepcion("La fecha introducida no es correcta. " +
                            "\nNo puede ser la fecha final antes de la fecha inicial del experimento.");
                } catch (FechaExcepcion ex) {
                    ex.printStackTrace();
                }
            }
            else {
                break;
            }
        }
        p.setFechaInicio(fechaInicial);
        p.setFechaFin(fechaFinal);

        switch (numPatronComida) {
            case 1:
                p.setNumeroPatronComida(1);
                int cantidadPico;
                int cantidadFinal;
                LocalDate fechaPico;
                // hay que declararlos fuera del bucle pq sino no puedo usarlos fuera de este
                while (true) {
                    cantidadFinal = readInt("Introduzca la cantidad de comida final: ");
                    if (cantidadFinal < 400) {
                        System.out.println("La cantidad de comida no puede ser menos de 400 microgramos.");
                    } else if (cantidadFinal > p.getComidaMax()) {
                        try {
                            throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido (300000 microgramos.");
                        } catch (ComidaMaxExcepcion ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
                while (true) {
                    cantidadPico = readInt("Introduzca la cantidad de comida más alta: ");
                    if (cantidadPico < 400) {
                        System.out.println("La cantidad de comida no puede ser menos de 400 microgramos.");
                    } else if (cantidadPico <= cantidadFinal || cantidadPico <= cantidadInicial) {
                        System.out.println("La comida media debe ser el pico. Por favor vuelva a intentarlo.");
                    } else if (cantidadPico > p.getComidaMax()) {
                        try {
                            throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido (300000 microgramos.");
                        } catch (ComidaMaxExcepcion ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
                while (true) {
                    fechaPico = UserInput.readDate("Introduzca la fecha donde hay el pico en su experimento (yyyy.MM.dd): ");
                    if (fechaFinal.isBefore(fechaPico) || fechaPico.isBefore(fechaInicial)) {
                        try {
                            throw new FechaExcepcion("La fecha introducida no es correcta. " +
                                    "\nLa fecha pico debe encontrarse entre la fecha de inicio y la de fin.");
                        } catch (FechaExcepcion ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        break;
                    }
                }

                ComidaPico comidaPico = new ComidaPico(cantidadInicial, fechaInicial, cantidadPico, fechaPico, cantidadFinal, fechaFinal);
                comidaPico.calcularComida();
                p.setComida(comidaPico);
                p.setDosisComidaDiaria(comidaPico.getCantidadComida());
                break;
            case 2:
                p.setNumeroPatronComida(2);
                ComidaCte comidaCte = new ComidaCte(cantidadInicial, fechaInicial, fechaFinal);
                comidaCte.calcularComida();
                p.setComida(comidaCte);
                p.setDosisComidaDiaria(comidaCte.getCantidadComida());
                break;
            case 3:
                p.setNumeroPatronComida(3);
                while (true) {
                    cantidadFinal = readInt("Introduzca la cantidad de comida final: ");
                    if (cantidadFinal < 0) {
                        System.out.println("La cantidad de comida no puede ser negativa.");
                    } else if (cantidadFinal <= cantidadInicial) {
                        System.out.println("La comida final debe ser superior a la cantidad inicial. Por favor vuelva a intentarlo.");
                    } else if (cantidadFinal > p.getComidaMax()) {
                        try {
                            throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido (300000 microgramos.");
                        } catch (ComidaMaxExcepcion ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
                ComidaIncremento comidaIncremento = new ComidaIncremento(cantidadInicial, fechaInicial, fechaFinal, cantidadFinal);
                comidaIncremento.calcularComida();
                p.setComida(comidaIncremento);
                p.setDosisComidaDiaria(comidaIncremento.getCantidadComida());
                break;
            case 4:
                p.setNumeroPatronComida(4);
                ComidaIntermitente comidaIntermitente = new ComidaIntermitente(cantidadInicial, fechaInicial, fechaFinal);
                comidaIntermitente.calcularComida();
                p.setComida(comidaIntermitente);
                p.setDosisComidaDiaria(comidaIntermitente.getCantidadComida());
                break;
        }

        int numIniBact;
        while (true) {
            numIniBact = UserInput.readInt("Escriba el número inicial de bacterias: ");
            if (numIniBact < 16) {
                System.out.println("El número inicial de bacterias no puede ser menor de 16.");
            } else {
                p.setNumInicialBacterias(numIniBact);
                break;
            }
        }

        for (int i = 0; i < numIniBact; i++) {
            Bacteria bacteria = new Bacteria();
            p.setBacteriaNueva(bacteria);
        }

        System.out.println("Creada población: "+nombreP);

        // añado la poblacion al experimento
        addPoblacion(p, e);
        return p;
    }

    /**
     *
     * AÑADIR poblacion al experimento
     * @param pob
     * @param exp
     */
    public static void addPoblacion (Poblacion pob, Experimento exp){
        exp.getPoblacionesList().add(pob);
        exp.setNumPoblaciones(exp.getPoblacionesList().size());
    }

    /**
     * ORDENAR poblaciones del experimento
     * */
    public static void ordenarPoblaciones(Experimento experimento) {
        int opcion = 0;
        System.out.println("\nMétodos de ordenación:");
        System.out.println("1 - Ordenar por nombre.");
        System.out.println("2 - Ordenar por fecha de inicio.");
        System.out.println("3 - Ordenar por número de bacterias.");
        System.out.println("4 - Sin ordenar.");
        do {
            opcion = readInt("\nSeleccione una opción: ");
            if (opcion < 1 || opcion > 4) {
                System.out.println("¡ Opción no valida ! ");
            }
        } while (opcion < 1 || opcion > 4);

        // TENGO QUE ESTUDIARME MEJOR ESTO
        switch (opcion) {
            case 1:
                try {
                    OrdenacionAlfabetica ordenaNombre = new OrdenacionAlfabetica();
                    Collections.sort(experimento.getPoblacionesList(), ordenaNombre); // en vez de usar Arrays.sort() que es para arrays,
                    // uso Collections.sort() que es para listas, arrayList, etc
                } catch (NullPointerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    OrdenacionCronologica ordenaFecha = new OrdenacionCronologica();
                    Collections.sort(experimento.getPoblacionesList(), ordenaFecha);
                } catch (NullPointerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                try {
                    OrdenacionCuantitativa ordenaCantidad = new OrdenacionCuantitativa();
                    experimento.getPoblacionesList().sort(ordenaCantidad);
                } catch (NullPointerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                break;
        }
    }

    /**
     *
     * ELIMINAR población del experimento
     * @param nombrePoblacion
     * @param e
     */
    public static void deletePoblacion(String nombrePoblacion, Experimento e) {

        for (int i = 0; i < e.getPoblacionesList().size(); i++) {
            if (nombrePoblacion.equals(e.getPoblacionesList().get(i).getNombrePoblacion())) {
                e.getPoblacionesList().remove(i); // Si lo igualo a algo, me va a mandar la Poblacion que he quitado
                // si no hago nada más, simplemente le arranca esa poblacion de mi lista
            }
        }
        e.setNumPoblaciones(e.getPoblacionesList().size());
    }


    // para hacer esto lo mejor es el tema
    public static Poblacion buscarPoblacion(String nombrePoblacion, Experimento e) {
        Poblacion miPoblacion=null;
        for (int i = 0; i < e.getPoblacionesList().size(); i++) {
            if (nombrePoblacion.equalsIgnoreCase(e.getPoblacionesList().get(i).getNombrePoblacion())) {
                miPoblacion=e.getPoblacionesList().get(i);
                break;
            }
        }
        if(miPoblacion!=null) {
            return miPoblacion;
        }
        else {
            throw new RuntimeException("Población no encontrada.");
        }
    }

}
