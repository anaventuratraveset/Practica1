package gestionLab;
import dataio.UserInput;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static dataio.UserInput.*;

public class GestionLab {

    // Gestiono el lab
    // Gestiono los distintos experimentos
    // createPoblacion(), AddPoblacion(), deletePoblacion(), etc

    //Primero de todo gestion de experimentos:

    //CREAR población
    public static Poblacion createPoblacion(Experimento e) throws Exception, ParseException {
        Poblacion p= new Poblacion();
        int dias=30;
        float comidaInicial;
        float comidaPico;
        float comidaFinal;
        Comida comida=new Comida();

        String nombreP = UserInput.readString("Escriba el nombre de su nueva población: ");
        p.setNombrePoblacion(nombreP);

        float temp = UserInput.readInt("Escriba la temperatura: ");
        p.setTemperatura(temp);


        Luminosidad.luminosidad lum = UserInput.readLuminosidad("Escriba el nivel de luminosidad {ALTA, MEDIA, BAJA}: ");
        p.setLuminosidad(lum);


        while (true) {
            comidaInicial  = UserInput.readFloat("Introduzca la cantidad de comida inicial: ");
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
            comidaPico  = UserInput.readFloat("Introduzca la cantidad de comida más alta: ");
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
            comidaFinal = UserInput.readFloat("Introduzca la cantidad de comida final: ");
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
        while (true) {
            fechaInicial= UserInput.readDate("Introduzca la fecha dónde empieza su experimento: ");
            fechaMedia = UserInput.readDate("Introduzca la fecha dónde hay el pico en su experimento: ");
            fechaFinal=fechaInicial.plusDays(30);
            if (fechaMedia.isBefore(fechaInicial) || fechaMedia.isAfter(fechaFinal)) {
                System.out.println("La fecha introducida no es correcta. Por favor vuelva a intentarlo.");
            } else {
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

        addPoblacion(p,e);
        return p;
    }

    //AÑADIR población
    public static void addPoblacion(Poblacion p, Experimento e)throws Exception{
        e.getPoblacionesList().add(p);
    }

    //ELIMINAR población
    public static void deletePoblacion(String nombrePoblacion, Experimento e) {
        ArrayList<Poblacion> copiaPoblacionesList=null;
        int indice = 0;

        for (int i = 0; i < e.getPoblacionesList().size(); i++) {
            if (!nombrePoblacion.equals(e.getPoblacionesList().get(indice).getNombrePoblacion())) {
                copiaPoblacionesList.add(e.getPoblacionesList().get(indice));
                indice++;
            }
        }
        e.setPoblacionesList(copiaPoblacionesList);
        e.setNumPoblaciones(e.getPoblacionesList().size());
    }

    public static Poblacion buscarPoblacion(String nombrePoblacion, Experimento e) {
        boolean hecho = false;
        int i;
        do {for (i = 0; i < e.getPoblacionesList().size(); i++) {
                if (nombrePoblacion.equals(e.getPoblacionesList().get(i).getNombrePoblacion())) {
                    hecho = true;
                    e.getPoblacionesList().get(i);
                    i++;
                } else {
                    hecho = false;
                    System.out.println("No se ha encontrado la población.");
                }
            }
        } while (hecho == false);
        return e.getPoblacionesList().get(i);
    }




}