package gestionLab;
import excepciones.*;
import laboratorio.Bacteria;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.*;
import ordenacion.OrdenacionAlfabetica;
import ordenacion.OrdenacionCronologica;
import ordenacion.OrdenacionCuantitativa;

import javax.swing.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;

import static dataio.UserInput.*;

/**
 * @author Ana Ventura-Traveset
 */

/**
 * Gestion del laboratorio (crear, añadir, borrar y buscar poblaciones)
 * Para introducir swing, he tenido que sustituir el UserInput.readString() por:
 * OptionPane.showInputDialog() para en vez de imprimir por consola, me salga en la ventana que he creado en mainSwing
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
        String nombreP = readString("Escriba el nombre de su nueva población: ");
        p.setNombrePoblacion(nombreP);

        float temp = readFloat("Escriba la temperatura: "); // Lo parseo a entero
        // antes del Swing era así: float temp = UserInput.readFloat("Escriba la temperatura: ");
        p.setTemperatura(temp);

        Luminosidad.luminosidad lum = readLuminosidad("Escriba el nivel de luminosidad {ALTA, MEDIA, BAJA}: ");
        p.setLuminosidad(lum);

        // comida
        // JOptionPane.showMessageDialog() para verlo en mi ventana
        JOptionPane.showMessageDialog(null, "Patrones de comida: " +
                "\n1. Patrón de incremento lineal seguido de decremento lineal." +
                "\n2. Patrón de cantidad de comida constante durante toda la duración del experimento." +
                "\n3. Patrón de incremento lineal de la cantidad de comida." +
                "\n4. Patrón con cantidad de comida constante durante todo el experimento un día, y al siguiente no proporcionar comida y así sucesivamente");

        int numPatronComida;

        do {
            numPatronComida = readInt("Escriba el número del patrón de comida que desea: ");
            if (numPatronComida < 1 || numPatronComida > 4) {
                JOptionPane.showMessageDialog(null,"El patrón de comida seleccionado no es correcto.");
            }
        } while (numPatronComida < 1 || numPatronComida > 4);
        p.setNumeroPatronComida(numPatronComida);

        //Pido todos los cosos de la clase ComidaPadre
        int cantidadInicial;
        LocalDate fechaInicial, fechaFinal;

        JOptionPane.showMessageDialog(null,"La cantidad de comida ha de ser entre 400-300000 microgramos).");
        // lo de que 400 sea el mínimo de comida permitida, lo establezco yo pq así en caso de hacer una simulación de Montecarlo,
        // siempre va a haber como mínimo una cantidad de 1 microgramo por celda a la hora de inicializar el plato de cultivo
        while (true) {
                try {
                    cantidadInicial = readInt("Introduzca la cantidad de comida inicial: ");
                    if (cantidadInicial < 400) {
                        throw new ComidaMinExcepcion("La cantidad de comida introducida no puede ser menos de 400 microgramos.");
                    } else if (cantidadInicial > p.getComidaMax()) {
                        throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido (300000 microgramos.)");
                    } else {
                        break;
                    }
                } catch (ComidaMinExcepcion | ComidaMaxExcepcion ignored) {
                }
        }

        //Para controlar que fecha de inicio no sea después que la de fin

        while (true) {
            try {
                fechaInicial = readDate("Introduzca la fecha donde empieza su experimento (yyyy.MM.dd): ");
                fechaFinal = readDate("Introduzca la fecha donde termina su experimento (yyyy.MM.dd): ");
                if (fechaFinal.isBefore(fechaInicial)) {
                    throw new FechaExcepcion("La fecha introducida no es correcta. " +
                            "\nNo puede ser la fecha final antes de la fecha inicial del experimento.");
                } else {
                    break;
                }
            } catch (FechaExcepcion ignored) {
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
                    try {
                        cantidadFinal = readInt("Introduzca la cantidad de comida final: ");
                        if (cantidadFinal < 400) {
                            throw new ComidaMinExcepcion("La cantidad de comida introducida no puede ser menos de 400 microgramos.");
                        } else if (cantidadFinal > p.getComidaMax()) {
                            throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido (300000 microgramos.)");
                        } else {
                            break;
                        }
                    } catch (ComidaMinExcepcion | ComidaMaxExcepcion ignored) {
                    }
                }
                while (true) {
                    try {
                        cantidadPico = readInt("Introduzca la cantidad de comida pico: ");
                        if (cantidadPico < 400) {
                            throw new ComidaMinExcepcion("La cantidad de comida introducida no puede ser menos de 400 microgramos.");
                        } else if (cantidadPico > p.getComidaMax()) {
                            throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido (300000 microgramos.)");
                        } else if (cantidadPico <= cantidadFinal || cantidadPico <= cantidadInicial) {
                            throw new ComidaRangoExcepcion("La cantidad introducida debe ser el pico.");
                        } else {
                            break;
                        }
                    } catch (ComidaMinExcepcion | ComidaMaxExcepcion | ComidaRangoExcepcion ignored) {
                    }
                }

                while (true) {
                    try {
                        fechaPico = readDate("Introduzca la fecha donde hay el pico en su experimento (yyyy.MM.dd): ");
                        if (fechaFinal.isBefore(fechaPico) || fechaPico.isBefore(fechaInicial)) {
                            throw new FechaExcepcion("La fecha introducida no es correcta. " +
                                    "\nLa fecha pico debe encontrarse entre la fecha de inicio y la de fin.");
                        } else {
                            break;
                        }
                    } catch (FechaExcepcion ignored) {
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
                    try {
                        cantidadFinal = readInt("Introduzca la cantidad de comida final: ");
                        if (cantidadFinal < 400) {
                            throw new ComidaMinExcepcion("La cantidad de comida introducida no puede ser menos de 400 microgramos.");
                        } else if (cantidadFinal > p.getComidaMax()) {
                            throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido (300000 microgramos.)");
                        } else if (cantidadFinal <= cantidadInicial) {
                            throw new ComidaRangoExcepcion("La cantidad final debe ser superior a la cantidad inicial.");

                        }else {
                            break;
                        }
                    } catch (ComidaMinExcepcion | ComidaMaxExcepcion | ComidaRangoExcepcion ignored) {
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
            try{
                numIniBact = readInt("Escriba el número inicial de bacterias: ");
                if (numIniBact < 16) {
                    throw new NInicialBacterias("El número inicial de bacterias no puede ser menor de 16.");
                } else {
                    p.setNumInicialBacterias(numIniBact);
                    break;
                }
            } catch (NInicialBacterias ignored) {
            }
        }

        for (int i = 0; i < numIniBact; i++) {
            Bacteria bacteria = new Bacteria();
            p.setBacteriaNueva(bacteria);
        }

        JOptionPane.showMessageDialog(null,"Creada población: "+nombreP);

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
        JOptionPane.showMessageDialog(null,"\nMétodos de ordenación:" +
                        "\n1 - Ordenar por nombre." +
                        "\n2 - Ordenar por fecha de inicio." +
                        "\n3 - Ordenar por número de bacterias." +
                        "\n4 - Sin ordenar.");
        do {
            opcion = readInt("\nSeleccione una opción: ");
            if (opcion < 1 || opcion > 4) {
                JOptionPane.showMessageDialog(null,"¡ Opción no valida ! ");
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
    public static void borrarPoblacion(String nombrePoblacion, Experimento e) {

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
