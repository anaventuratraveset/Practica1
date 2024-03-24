import java.io.File;
import java.io.IOException;

public class CreateFile {

    try{
        File miArchivo = new File("ExperimentoPorDefecto.txt");
        if (miArchivo.createNewFile()){
            System.out.println("Archivo creado: " + miArchivo.getName());
        }
        else {
            System.out.println("File already exists.");
          }
        } catch (IOException e) {
          System.out.println("ERROR.");
        }

}
