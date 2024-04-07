package test.dataioTest;

import dataio.FileManager;
import laboratorio.Experimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FileManagerTest {

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
        String input = "newE;30\np1;3;3.0;BAJA;2023-09-09;2.0;2023-09-12;6.0;2023-10-09;4.0\n";
        when(bufferedReader.readLine()).thenReturn(input, null);

        Experimento experimento = null;
        try {
            experimento = fileManager.abrirArchivo("testFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals("newE", experimento.getNombreExperimento());
        assertEquals(30, experimento.getDias());
    }

    @Test
    void testGuardarArchivo() {
        Experimento experimento = new Experimento("newE");
        boolean result = FileManager.guardarArchivo("testFile", experimento);

        assertEquals(true, result);
    }
}