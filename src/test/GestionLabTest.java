package test;
import gestionLab.GestionLab;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class GestionLabTest {

    void provideInputString(String data) {
        ByteArrayInputStream testIn;
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void user_creates_new_pob() throws Exception {
        Experimento exp = new Experimento("MiExp");
        provideInputString("30\n30\n30\n30\n30");
        Poblacion miPob = GestionLab.createPoblacion(exp);
        assertEquals(miPob, exp.getPoblacionesList().get(0));

        // no consigo hacer el unitTest de createPoblacion() de GestionLab
        // porque en el método le pido por pantalla varias variables y para ello se hace por ejemplo int.nextInt()
        // entonces digamos que en la primera pedida por pantalla me se "come" todo el provided input, a pesar de
        // haberlo intentado separar por saltos de línea

    }

}
