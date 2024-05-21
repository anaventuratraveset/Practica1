package dataio;
import medio.Luminosidad;
import ui.MainSwing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;


/**
 * Clase para leer de teclado
 * @author Ana Ventura-Traveset
 */
public class UserInput {

    // lo he hecho de manera iterativa, no recursiva

    /**
     * Para leer por teclado Strings
     * @param peticion
     * @return miString
     */
    public static String readString(String peticion) {
System.out.println(peticion);
        boolean hecho = false;
        String miString = "";
        do {
            try {
                miString = JOptionPane.showInputDialog(peticion);
//                Scanner sc = new Scanner(System.in); //creo el scanner Object
//                miString = sc.nextLine(); //leo el input del usuario
                hecho = true;
            } catch (Exception e) {
                System.out.println("ERROR al introducir por teclado.");
                e.printStackTrace();
            }
        } while (hecho == false);
        return miString;
    }

    /**
     * Para leer por teclado enteros, excluyendo los negativos que no se usan en esta práctica
     * @param peticion
     * @return miInt
     */
    public static int readInt(String peticion) {
        boolean hecho = false;
        int miInt = 0;
        do {
            try {
                miInt = Integer.parseInt(JOptionPane.showInputDialog(peticion));
//                System.out.println(peticion);
//                Scanner sc = new Scanner(System.in);
//                miInt = sc.nextInt();
                if (miInt < 0) {
                    JOptionPane.showMessageDialog(null,"ERROR. El número introducido es negativo."); // esto cambios son para swing
                } else {
                    hecho = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"ERROR al introducir por teclado. Debe introducir un número entero.");
            }
        } while (hecho == false);
        return miInt;
    }

    /**
     * Para leer por teclado reales
     * @param peticion
     * @return miFloat
     */
    public static float readFloat(String peticion)  {
        boolean hecho = false;
        float miFloat = 0;
        do {
            try {
                miFloat = Float.parseFloat(JOptionPane.showInputDialog(peticion));
//                System.out.println(peticion);
//                Scanner sc = new Scanner(System.in);
//                miFloat = sc.nextFloat();
                hecho=true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"ERROR al introducir por teclado. Debe introducir un número real.");
            }
        } while (hecho == false);
        return miFloat;
    }

    /**
     * Para leer por teclado un String y asociarlo con un elemento del enum luminosidad
     * @param peticion
     * @return lum
     */
    public static Luminosidad.luminosidad readLuminosidad(String peticion) {
        Luminosidad.luminosidad luminosidad = null;
        String lum;
        boolean hecho = false;
        do{
            try {
                lum = JOptionPane.showInputDialog(peticion);
//                System.out.println(peticion);
//                Scanner sc = new Scanner(System.in);
//                lum = sc.nextLine();
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
                    JOptionPane.showMessageDialog(null,"ERROR. Por favor introduzca una luminosidad correcta {ALTA, MEDIA, BAJA}: ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"ERROR al introducir por teclado.");
            }
        }while (hecho == false) ;
        return luminosidad;
    }

    /**
     * Para leer por teclado fechas
     *
     * SimpleDateFormat tiene un método para convertir String
     * en el formato fecha que le hayamos dicho, y ese es el parse
     * se utiliza el método toInstant de Date().atZone().toLocalDate() para pasar de Date a LocalDate
     *
     * @param peticion
     * @return fechaADevolver
     */
    public static LocalDate readDate(String peticion){
        String fechaString;
        Date fecha;
        LocalDate fechaADevolver=null;
        boolean hecho = false;

        do{
            try{
                fechaString = JOptionPane.showInputDialog(peticion);
//                System.out.println(peticion);
//                Scanner sc = new Scanner(System.in);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy.MM.dd");
                fecha = formato.parse(fechaString); // le paso el String que ha introducido el usuario mediante la interfaz en vez de mediante el clásico scanner.nextLine()
                fechaADevolver = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                hecho=true;
            }catch(ParseException pe){
                JOptionPane.showMessageDialog(null,"ERROR. La fecha introducida no es correcta o no se ha parseado correctamente.");
            }
        }while(hecho==false);
        return fechaADevolver;
    }
}