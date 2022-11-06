package Logica.Entidades.Naves;

import Logica.Entidades.ObjetoGrafico;

import javax.swing.*;

public class NaveGrafica extends ObjetoGrafico {
    protected String skin;

    public NaveGrafica(int x, int y, String skin){
        //this.setAlignmentX(x);
        //this.setAlignmentY(y);
        this.skin = skin;
        this.setBounds(x, y, 70, 70);
    }

    @Override
    public String getRefImagen() {
        return skin;
    }
}
