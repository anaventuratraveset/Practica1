package ui;

import laboratorio.Plato;

import javax.swing.*;
import java.awt.*;


public class VistaSimulacion extends JPanel {
    /**
     * Es una clase de interfaz de usuario que se utiliza para visualizar una simulación de una población
     * de bacterias en un experimento. Esta clase extiende JPanel y se utiliza para dibujar una cuadrícula
     * de celdas => un plato de cultivo para cada día.
     * */
    int [][][][] simulacion;
    JScrollPane scrollPane;

    /**
     * Este es el constructor de la clase.
     * Toma una matriz 4D que representa la simulación de la población de bacterias
     * y la almacena en la variable de instancia simulacion.
     * También establece el tamaño preferido del panel y llama a paintComponent(getGraphics())
     * para dibujar la simulación.
     * */
    public VistaSimulacion(int[][][][]simulacion) {
        this.simulacion = simulacion;
        setPreferredSize(new Dimension(400, 400)); // el tamaño que va a tener

        // Crear un JScrollPane que envuelva este panel
        scrollPane = new JScrollPane(this);

        // Configurar las barras de desplazamiento para que siempre se muestren
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    // Método para obtener el JScrollPane
    public JScrollPane getScrollPane() {
        return scrollPane;
    }


    /**
     *  Este método se sobrescribe de la clase JPanel.
     *  Se llama automáticamente cuando el panel necesita ser redibujado.
     *  Si la simulación no es nula, recorre cada celda de la cuadrícula
     *  y llama al método drawCell(g, fila, columna) para dibujar la celda.
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (simulacion != null) {
            int duracion = simulacion.length;
            for (int dia = 0; dia < duracion; dia++) {
                for (int fila = 0; fila < 20; fila++) {
                    for (int columna = 0; columna < 20; columna++) {
                        drawCell(g, dia, fila, columna);
                    }
                }
            }
        }
    }
    /**
     * Este método dibuja una celda individual de la cuadrícula.
     * Recorre cada momento de la duración de la simulación y obtiene el número de bacterias
     * en la celda en ese momento. Luego, establece el color del gráfico en función del número de bacterias
     * y dibuja un rectángulo lleno en la posición de la celda.
     * */
    private void drawCell(Graphics g, int dia, int fila, int columna) {
        int numeroBacterias = simulacion[dia][fila][columna][0];
        g.setColor(getColorForCount(numeroBacterias));
        g.fillRect(dia * 20 + fila * 20, columna * 20, 20, 20);
    }
    /**
     * Este método devuelve un color en función del número de bacterias.
     * Si el número de bacterias es mayor que 100, devuelve rojo.
     * Si es mayor que 50, devuelve naranja.
     * Si es mayor que 20, devuelve amarillo.
     * Si es mayor que 10, devuelve verde.
     * Si no, devuelve blanco, lo que indica que no hay bacterias.
     * */
    private Color getColorForCount(int numeroBacterias) {
        if (numeroBacterias > 100) return Color.RED;
        if (numeroBacterias > 50) return Color.ORANGE;
        if (numeroBacterias > 20) return Color.YELLOW;
        if (numeroBacterias > 10) return Color.GREEN;
        return Color.BLUE; // Sin bacterias
    }
}
