package Logica.Entidades.Naves;

import Logica.Entidades.ObjetoGrafico;

import javax.swing.*;

public class NaveGrafica extends ObjetoGrafico {
    protected String skin;
    protected static final int TAM = 70;

    public NaveGrafica(int x, int y, String skin){
        this.skin = skin;
        this.setBounds(x, y, TAM, TAM);
    }

    @Override
    public String getRefImagen() {
        return skin;
    }
}
