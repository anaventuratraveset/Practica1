package tests.dataio;
import dataio.FileManager;
import laboratorio.Experimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FileManagerTest {

    @Mock
    private BufferedReader bufferedReader;
    private FileManager fileManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        fileManager = new FileManager();
    }
    @Test
    void testAbrirArchivo() throws IOException {
        String input = "eSwing\n" +
                "pSwing;56;1.0;BAJA;1;2000-09-09;4009;2000-09-13;6000;2000-09-24;5000\n" +
                "p2Swing;200000;88.0;ALTA;1;2000-09-08;40000;2000-09-19;80000;2000-10-10;60000";
        when(bufferedReader.readLine()).thenReturn(input, null);
        Experimento experimento = null;
        try {
            experimento = fileManager.abrirArchivo("eSwing");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("eSwing", experimento.getNombreExperimento());
    }

    @Test
    void testGuardarArchivo() {
        Experimento experimento = new Experimento("eSwing");

        boolean result = FileManager.guardarArchivo("eSwing", experimento);
        assertTrue(result, "El archivo debería haberse guardado correctamente");

    }
}
