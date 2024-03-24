package ui;
import dataio.FileManager.UserInput.*;
import java.io.*;
import java.util.*; //para el readInt

public class main {

    public static void main(String[] args) {

        //el main nunca lanza excepciones pq es el último

        int opcion = 0;

        //FileManager utilidadesFile = new FileManager();
        while (opcion != 9) {
            System.out.println("Selecione una opción:" +
                    "\n1. Abrir un archivo que contenga un experimento" +
                    "\n2. Crear un nuevo experimento" +
                    "\n3. Crear una población de bacterias y añadirla al experimento actual" +
                    "\n4. Visualizar los nombres de todas las poblaciones de bacterias del experimento actual" +
                    "\n5. Borrar una población de bacterias del experimento actual" +
                    "\n6. Ver información detallada de una población de bacterias del experimento actual" +
                    "\n7. Guardar" +
                    "\n8. Guardar como" +
                    "\n9. Salir");

            System.out.println("\nIntroduzca las fechas siempre en este formato \"yyyy.MM.dd\"");
            do {
                //Lo del readInt lo voy ahacer en la clase UserInput, por eso aú da fallo
                //opcion = readInt("Seleccione una opción: ");
                if (opcion < 1 || opcion > 9) {
                    System.out.println("¡ Opción no valida ! ");
                }
            } while (opcion < 1 || opcion > 9);

            //Aqui irá un switch y tal...
        }

        }

    }
}
