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

    }

}
