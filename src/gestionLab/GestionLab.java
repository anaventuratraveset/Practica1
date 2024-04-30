package gestionLab;
import dataio.UserInput;
import excepciones.ComidaMaxExcepcion;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.*;
import java.text.ParseException;
import java.time.LocalDate;

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
        float cantidadInicial;
        LocalDate fechaInicial, fechaFinal;

        while (true) {
            cantidadInicial = readFloat("Introduzca la cantidad de comida inicial: "); //he importado la clase y su método para poder usarlo pq es static el método
            if (cantidadInicial < 0) {
                System.out.println("La cantidad de comida no puede ser negativa.");
            } else if (cantidadInicial > p.getComidaMax()) {
                try {
                    throw new ComidaMaxExcepcion("La comida introducida supera el máximo permitido.");
                } catch (ComidaMaxExcepcion ex) {
                    //sirve para avisar de que ha ido mal la excepción, en caso de que asi sea
                    ex.printStackTrace();
                }
            } else {
                break;
            }
        }

        //Para controlar que fecha de inicio no sea después que la de fin
        fechaInicial= UserInput.readDate("Introduzca la fecha dónde empieza su experimento: ");
        fechaFinal = UserInput.readDate("Introduzca la fecha dónde termina su experimento: ");
        while (true) {
            if (fechaFinal.isBefore(fechaInicial)) {
                System.out.println("La fecha introducida no es correcta. " +
                        "\nNo puede ser la fecha final antes de la fecha inicial del experimento." );
            }
            else {
                break;
            }
        }

        switch (numPatronComida) {
            case 1:
                p.setNumeroPatronComida(1);

                float cantidadPico = readFloat("Introduzca la cantidad de comida más alta: ");
                LocalDate fechaPico = UserInput.readDate("Introduzca la fecha dónde hay el pico en su experimento: ");
                float cantidadFinal = readFloat("Introduzca la cantidad de comida final: ");
                ComidaPico comidaPico = new ComidaPico(cantidadInicial, fechaInicial, cantidadPico, fechaPico, cantidadFinal, fechaFinal);
                comidaPico.calcularComida();
                p.setComida(comidaPico);
                p.setDosisComidaDiaria(comidaPico.getCantidadComida());
                break;
            case 2:
                p.setNumeroPatronComida(2);
                cantidadInicial = readFloat("Introduzca la cantidad de comida inicial: ");
                fechaInicial = UserInput.readDate("Introduzca la fecha dónde empieza su experimento: ");
                fechaFinal = UserInput.readDate("Introduzca la fecha dónde termina su experimento: ");
                ComidaCte comidaCte = new ComidaCte(cantidadInicial, fechaInicial, fechaFinal);
                comidaCte.calcularComida();
                p.setComida(comidaCte);
                p.setDosisComidaDiaria(comidaCte.getCantidadComida());
                break;
            case 3:
                p.setNumeroPatronComida(3);
                cantidadInicial = readFloat("Introduzca la cantidad de comida inicial: ");
                fechaInicial = UserInput.readDate("Introduzca la fecha dónde empieza su experimento: ");
                cantidadFinal = readFloat("Introduzca la cantidad de comida final: ");
                fechaFinal = UserInput.readDate("Introduzca la fecha dónde termina su experimento: ");
                ComidaIncremento comidaIncremento = new ComidaIncremento(cantidadInicial, fechaInicial, fechaFinal, cantidadFinal);
                comidaIncremento.calcularComida();
                p.setComida(comidaIncremento);
                p.setDosisComidaDiaria(comidaIncremento.getCantidadComida());
                break;
            case 4:
                p.setNumeroPatronComida(4);
                cantidadInicial = readFloat("Introduzca la cantidad de comida inicial: ");
                fechaInicial = UserInput.readDate("Introduzca la fecha dónde empieza su experimento: ");
                fechaFinal = UserInput.readDate("Introduzca la fecha dónde termina su experimento: ");
                ComidaIntermitente comidaIntermitente = new ComidaIntermitente(cantidadInicial, fechaInicial, fechaFinal);
                comidaIntermitente.calcularComida();
                p.setComida(comidaIntermitente);
                p.setDosisComidaDiaria(comidaIntermitente.getCantidadComida());
                break;
        }



        //ComidaPadre comida = new ComidaPadre();
        p.setNombrePoblacion(nombreP);
        float comidaInicial;
        float comidaPico;
        float comidaFinal;
        ComidaPadre comida=new ComidaPadre();
        while (true) {
            comidaInicial  = readFloat("Introduzca la cantidad de comida inicial: ");
            if (comidaInicial < 0) {
                System.out.println("La cantidad de comida no puede ser negativa.");
            } else if (comidaInicial > 300) {
                System.out.println("La cantidad de comida no puede ser superior a 300.");
            } else {
                comida.setCantidadInicial(comidaInicial);
                break;
            }
        }
        while (true) {
            comidaPico  = readFloat("Introduzca la cantidad de comida más alta: ");
            if (comidaPico < 0) {
                System.out.println("La cantidad de comida no puede ser negativa.");
            } else if (comidaPico > 300) {
                System.out.println("La cantidad de comida no puede ser superior a 300.");
            } else {
                comida.setCantidadPico(comidaPico);
                break;
            }
        }
        while (true) {
            comidaFinal = readFloat("Introduzca la cantidad de comida final: ");
            if (comidaFinal < 0) {
                System.out.println("La cantidad de comida no puede ser negativa.");
            } else if (comidaFinal > 300) {
                System.out.println("La cantidad de comida no puede ser superior a 300.");
            } else {
                comida.setCantidadFinal(comidaFinal);
                break;
            }
        }
        //pido el resto de cosas para calcular la comida, que son las fechas
        LocalDate fechaInicial;
        LocalDate fechaMedia;
        LocalDate fechaFinal;

        //Para controlar que fecha media no sea antes que la de inicio ni después que la de fin
        fechaInicial= UserInput.readDate("Introduzca la fecha dónde empieza su experimento: ");
        while (true) {
            fechaMedia = UserInput.readDate("Introduzca la fecha dónde hay el pico en su experimento: ");
            fechaFinal=fechaInicial.plusDays(dias);
            int diasEntreInicioYPico= (int) DAYS.between(fechaInicial, fechaMedia);
            if (fechaMedia.isBefore(fechaInicial)) {
                System.out.println("La fecha introducida no es correcta. " +
                        "\nNo puede ser la fecha pico antes de la fecha inicial del experimento." );
            }
            else if(fechaMedia.isAfter(fechaFinal)){
                System.out.println("La fecha introducida no es correcta. " +
                        "\nLa fecha pico no puede ser después de la fecha final del experimento que dura 30 días." );
            }
            else if((diasEntreInicioYPico>29)){
                System.out.println("La fecha introducida no es correcta. " +
                        "\nLa fecha pico no puede ser después de la fecha final del experimento que dura 30 días." );
            }
            else {
                comida.setFechaInicial(fechaInicial);
                p.setFechaInicio(fechaInicial);
                comida.setFechaPico(fechaMedia);
                comida.setFechaFinal(fechaFinal);
                p.setFechaFin(fechaFinal);

                comida.setCantidadComida(comida.calcularComida());
                p.setComida(comida);
                break;
            }
        }

        int numIniBact;
        while (true) {
            numIniBact = UserInput.readInt("Escriba el número inicial de bacterias: ");
            if (numIniBact < 0) {
                System.out.println("El número inicial de bacterias no puede ser negativo.");
            } else {
                p.setNumInicialBacterias(numIniBact);
                break;
            }
        }
        System.out.println("Creada población: "+nombreP);

        //añado la poblacion al experimento
        e.setNumPoblaciones(e.getNumPoblaciones()+1);
        e.setPoblacionNueva(p);
        return p;
    }

    /**
     *
     * AÑADIR poblacion al experimento
     * @param pob
     * @param exp
     */
    public static void addPoblacion (Poblacion pob, Experimento exp){
        exp.setNumPoblaciones(exp.getNumPoblaciones()+1);
        exp.setPoblacionNueva(pob);
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
            if (nombrePoblacion.equals(e.getPoblacionesList().get(i).getNombrePoblacion())) {
                miPoblacion=e.getPoblacionesList().get(i);
                break;
            }
        }
        if(miPoblacion!=null) {
            return miPoblacion;
        }
        else {
            throw new RuntimeException();
        }
    }
}