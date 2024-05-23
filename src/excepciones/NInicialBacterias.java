package excepciones;

public class NInicialBacterias extends Exception{
    /**
     * Esta execepción se lanzará si el número de bacterias iniciales es menor que 16
     * Asi lo he establecido ya, para que en caso de haber una simulación de Montecarlo,
     * al inicializar el plato de cultivo haya como mínimo 1 bacteria por celda central
     * @param mensaje
     * */
    public NInicialBacterias(String mensaje) {
        super(mensaje);
    }
}
