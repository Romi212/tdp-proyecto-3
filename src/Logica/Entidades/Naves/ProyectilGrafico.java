package Logica.Entidades.Naves;

import Logica.Entidades.ObjetoGrafico;

import javax.swing.*;
import java.awt.*;

public class ProyectilGrafico extends ObjetoGrafico {
    protected String imagen;


    public ProyectilGrafico(int x, int y){
        this.setAlignmentX(x);
        this.setAlignmentY(y);
        imagen = "";

    }

    public void moverPixeles(int cant){
        this.setAlignmentX(this.getAlignmentX()+cant);
        this.repaint();
    }


    @Override
    public String getRefImagen() {
        return imagen;
    }
}
