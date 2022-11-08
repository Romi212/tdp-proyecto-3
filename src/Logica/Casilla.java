package Logica;

import javax.swing.*;

public class Casilla extends JButton {

    private int columna;
    private int fila;

    public Casilla(int f, int c){

        columna = c;
        fila = f;

        this.setOpaque(false);
        this.setBorder(null);

    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }
}
