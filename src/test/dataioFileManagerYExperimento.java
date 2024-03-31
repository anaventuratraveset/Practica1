package test;
import dataio.FileManager;
import laboratorio.Experimento;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class dataioFileManagerYExperimento {

    public dataioFileManagerYExperimento() throws Exception {
    }

    void provideInputString(String data) throws IOException {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    @Test
    public void openFile() throws Exception {
        FileManager.abrirArchivo("fakeArchivo"); //should throw controlled
        miExp = FileManager.abrirArchivo("testexp");
    }

    Experimento miExp = FileManager.abrirArchivo("testexp");

    @Test
    public void loadedFile_toString() throws Exception {
        String shouldEqual = "Nombre Experimento: Experimento\n" + "Numero de días: 30\n" + "\n" + "Información de las poblaciones:\n" + "\n" + "POBLACIÓN 1: La población Poblacion1:\n" + "Cantidad de bacterias inicialmente: 2\n" + "Temperatura a la cual están sometidas las bacterias: 23.0\n" + "Luminosidad: BAJA\n" + "Dosis de comida diaria: En la fecha de inicio: 2000-09-09, cantidad de comida inicial: 2.0\n" + "En la fecha de pico: 2000-09-12, cantidad de comida pico: 9.0\n" + "En la fecha de fin: 2000-10-09, cantidad de comida final: 3.0\n" + "Cantidad de dosis de comida diaria: [2.0, 4.333333, 6.6666665, 9.444445, 9.666667, 9.888889, 10.111111, 10.333333, 10.555555, 10.777778, 11.0, 11.222222, 11.444445, 11.666666, 11.888889, 12.111111, 12.333333, 12.555555, 12.777778, 13.0, 13.222222, 13.444445, 13.666666, 13.888889, 14.111111, 14.333334, 14.555555, 14.777778, 15.0, 15.222222]\n" + "\n" + "POBLACIÓN 2: La población Poblacion2:\n" + "Cantidad de bacterias inicialmente: 9\n" + "Temperatura a la cual están sometidas las bacterias: 45.0\n" + "Luminosidad: ALTA\n" + "Dosis de comida diaria: En la fecha de inicio: 2023-09-08, cantidad de comida inicial: 7.0\n" + "En la fecha de pico: 2023-09-17, cantidad de comida pico: 19.0\n" + "En la fecha de fin: 2023-10-08, cantidad de comida final: 3.0\n" + "Cantidad de dosis de comida diaria: [7.0, 8.333333, 9.666667, 11.0, 12.333334, 13.666667, 15.0, 16.333334, 17.666668, 25.095238, 25.857141, 26.619047, 27.38095, 28.142857, 28.904762, 29.666666, 30.42857, 31.190475, 31.952381, 32.714287, 33.47619, 34.238094, 35.0, 35.7619, 36.52381, 37.285713, 38.04762, 38.809525, 39.571426, 40.333332]\n";
        assertEquals(miExp.toString(), shouldEqual);
    }
    @Test
    public void loadedFile_toStringNombres() throws Exception {
        String shouldEqual = "Nombre Experimento: Experimento\n" + "Nombre de poblaciones:\n" + "Nombre de la poblacion 1: Poblacion1\n" + "Nombre de la poblacion 2: Poblacion2\n";
        assertEquals(miExp.toStringNombres(), shouldEqual);
    }
    @Test
    public void loadedFile_toStringInfoExperimentoToFile() throws Exception {
        String shouldEqual = "Experimento";
        assertEquals(miExp.toStringInfoExperimentoToFile(), shouldEqual);
    }
    @Test
    public void loadedFile_save() throws Exception {
        boolean isSaved = FileManager.guardarArchivo("testexp", miExp);
        assertTrue(isSaved);
    }

}
