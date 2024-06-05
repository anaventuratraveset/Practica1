package tests.medio;

import medio.ComidaIncremento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.time.LocalDate;

public class ComidaIncrementoTest {

    ComidaIncremento comidaIncremento;
        @Test
        void calcularComidaTest() {
            comidaIncremento = Mockito.mock(ComidaIncremento.class);
            Mockito.when(comidaIncremento.calcularComida()).thenReturn(new int[]{100, 200, 300, 400, 500});
            int[] expected = new int[]{100, 200, 300, 400, 500};
            assertArrayEquals(expected, comidaIncremento.calcularComida());
        }


        @Test
        void toStringTest() {
            ComidaIncremento comidaIncremento = new ComidaIncremento(100, LocalDate.now(), LocalDate.now().plusDays(5), 100);
            String expected = "Tipo: Comida Incremento" +
                    "\nLa duración es de 5 días." +
                    "\nEn la fecha de inicio: " + LocalDate.now() + ", cantidad de comida inicial: 100 microgramos ." +
                    "\nEn la fecha de fin: " + LocalDate.now().plusDays(5) + ", cantidad de comida final: 100 microgramos" +
                    "\nCantidad de dosis de comida diaria: [100, 200, 300, 400, 500]";
            assertEquals(expected, comidaIncremento.toString());
        }

        @Test
        void toStringToFileTest() {
            ComidaIncremento comidaIncremento = new ComidaIncremento(100, LocalDate.now(), LocalDate.now().plusDays(5), 100);
            String expected = LocalDate.now() + ";100;" + LocalDate.now().plusDays(5) + ";100";
            assertEquals(expected, comidaIncremento.toStringToFile());
        }
}
