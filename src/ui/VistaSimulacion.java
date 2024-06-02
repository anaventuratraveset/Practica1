package ui;

public class VistaSimulacion {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";

    public void VistaNumBacterias(int [][][][]matriz){
        for (int fila = 0; fila < 20; fila++) {
            String infoNumBacteria = "";
            int infoNumBacterias;
            int infoComida;
            for (int columna = 0; columna < 20; columna++) {
                String color = RED;

                simulacion[dia][fila][columna][0] = infoNumBacterias;
                simulacion[dia][fila][columna][1] = infoComida;
                try {
                    if (simulacion[dia][fila][columna][0] == 0){
                        color = WHITE;
                    } else if (simulacion[dia][fila][columna][0] >= 15){
                        color = RED;
                    } else if(simulacion[dia][fila][columna][0] >= 10){
                        color = PURPLE;
                    } else if(simulacion[dia][fila][columna][0] >= 5){
                        color = GREEN;
                    } else if(simulacion[dia][fila][columna][0] >= 1){
                        color = YELLOW;
                    }

                    infoNumBacteria = color + "["+String.format("%03d",simulacion[dia][fila][columna][0]) /*+ "," + String.format("%03d",simulacion[dia][fila][columna][1])*/+"] " + RESET;
                    //String stringFormateado = String.format(infoNumBacteria, "%02d");
                    System.out.print(infoNumBacteria);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception("Error en la matriz 3D");
                }
            }
            System.out.println();
        }
    }

    public void VistaCantidadComida(int [][][][]matriz){
        for (int dia = 0; dia < matriz.length; dia++) { //días
            for (int fila = 0; fila < matriz[0].length; fila++) { //filas
                String infoComida = "";
                int cantidadComida = 0;
                for (int columna = 0; columna < matriz[0][0].length; columna++) { //columnas
                    String color = null;
                    cantidadComida = (matriz[dia][fila][columna][1]);
                    if (cantidadComida == 0){
                        color = WHITE;
                    } else if (cantidadComida > 200){
                        color = RED;
                    } else if(cantidadComida > 100){
                        color = PURPLE;
                    } else if(cantidadComida > 50){
                        color = GREEN;
                    } else if(cantidadComida > 10){
                        color = YELLOW;
                    }
                    infoComida += " " + color + String.format("%03d",cantidadComida) + RESET;
                }
                System.out.println("Cantidad comida, dia " + String.format("%02d", (dia + 1)) + " x " + String.format("%02d", fila) + ": " + infoComida);
            }
            System.out.println();
        }
    }
}
