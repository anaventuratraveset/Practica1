package dataio;
import medio.Luminosidad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class UserInput {

    //Para leer de teclado ya sea String, int, float, Luminosidad o LocalDate
    // en los catch, lo de printStackTrace(), para que me imprima en rojo el tipo de error, la línea, etc
    public static String readString(String peticion) {
        System.out.println(peticion);
        boolean hecho = false;
        String miString = ""; //tengo q inicializarlo, pq sino al estar dentro del try catch no puedo returnarlo
        do {
            try {
                //Uso scanner bc bufferReader tengo entendido q es mas para leer de fichero
                Scanner sc = new Scanner(System.in); //creo el scanner Object
                miString = sc.nextLine(); //leo el input del usuario
                hecho = true;
            } catch (Exception e) {
                System.out.println("ERROR al introducir por teclado.");
                e.printStackTrace();
                hecho = false;
            }
        } while (hecho == false);
        return miString;

    }

    public static int readInt(String peticion) {
        boolean hecho = false;
        int miInt = 0;
        do {
            try {
                System.out.println(peticion);
                Scanner sc = new Scanner(System.in);
                miInt = sc.nextInt();

                if (miInt < 0) {
                    System.out.println("ERROR. El número introducido es negativo.");
                } else {
                    hecho = true;
                }
            } catch (Exception e) {
                System.out.println("ERROR al introducir por teclado. Debe introducir un número entero.");
                hecho = false;
            }
        } while (hecho == false);
        return miInt;
    }

    public static float readFloat(String peticion)  {
        boolean hecho = false;
        float miFloat = 0;
        do {
            try {
                System.out.println(peticion);
                Scanner sc = new Scanner(System.in);
                miFloat = sc.nextFloat();
                hecho=true;
            } catch (Exception e) {
                System.out.println("ERROR al introducir por teclado. Debe introducir un número real.");
                hecho = false;
            }
        } while (hecho == false);
        return miFloat;
    }

    public static Luminosidad.luminosidad readLuminosidad(String peticion) {
        Luminosidad.luminosidad luminosidad = null;
        String lum;
        boolean hecho=false;
        do{
            try {
                System.out.println(peticion);
                Scanner sc = new Scanner(System.in);
                lum = sc.nextLine();
                if (lum.equalsIgnoreCase("ALTA")) {
                    luminosidad = Luminosidad.luminosidad.ALTA;
                    hecho=true;
                } else if (lum.equalsIgnoreCase("MEDIA")) {
                    luminosidad = Luminosidad.luminosidad.MEDIA;
                    hecho=true;
                } else if (lum.equalsIgnoreCase("BAJA")) {
                    luminosidad = Luminosidad.luminosidad.BAJA;
                    hecho=true;
                } else {
                    System.out.println("ERROR. Por favor introduzca una luminosidad correcta {ALTA, MEDIA, BAJA}: ");
                    hecho=false;
                }
            } catch (Exception e) {
                System.out.println("ERROR al introducir por teclado.");
                hecho=false;
            }
        }while (hecho == false) ;
        return luminosidad;
    }

    public static LocalDate readDate(String peticion){
        Date fecha;
        LocalDate fechaADevolver=null;
        boolean hecho=false;

        do{
            try{
                System.out.println(peticion);
                System.out.println("Introducir las fechas en este formato: yyyy.MM.dd");
                Scanner sc = new Scanner(System.in);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd");
                fecha = formato.parse(sc.nextLine());
                //SimpleDateFormat tiene un método para convertir String
                //en el formato fecha que le hayamos dicho, y ese es el parse
                fechaADevolver = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                //ese paso es para convertir a LocalDate con el método toInstant() de date
                hecho=true;
            }catch(ParseException pe){
                hecho = false;
                System.out.println("ERROR. La fecha introducida no es correcta o no se ha parseado correctamente.");
            }
        }while(hecho==false);
        return fechaADevolver;
    }

}