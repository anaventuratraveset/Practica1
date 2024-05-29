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
    }

    /**
     *  Este método se sobrescribe de la clase JPanel.
     *  Se llama automáticamente cuando el panel necesita ser redibujado.
     *  Si la simulación no es nula, recorre cada celda de la cuadrícula
     *  y llama al método drawCell(g, fila, columna) para dibujar la celda.
     * */
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        if (simulacion != null) {
//            int duracion = simulacion.length;
//            for (int dia = 0; dia < duracion; dia++) {
//                for (int fila = 0; fila < 20; fila++) {
//                    for (int columna = 0; columna < 20; columna++) {
//                        drawCell(g, dia, fila, columna);
//                    }
//                }
//            }
//        }
//    }

    public void paint(Graphics g) {
        Dimension size = new Dimension(12, 12);         // diameter
        int d = Math.min(size.width, size.height);
        int x = (size.width - d)/2;
        int y = (size.height - d)/2;
        // draw circle (color already set to foreground)
        g.fillOval(x, y, d, d);
        g.setColor(Color.black);
        g.drawOval(x, y, d, d);
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
     * TODO: rellenar esto
     * Si no, devuelve blanco, lo que indica que no hay bacterias.
     *
     * */
    private Color getColorForCount(int numeroBacterias) {
        if (numeroBacterias > 20) return Color.RED;
        if (numeroBacterias > 15) return Color.PINK;
        if (numeroBacterias > 10) return Color.ORANGE;
        if (numeroBacterias > 5) return Color.YELLOW;
        if (numeroBacterias > 1) return Color.GREEN;
        return Color.WHITE; // Sin bacterias
    }
}
