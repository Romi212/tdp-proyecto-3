package Logica.Entidades.Naves;

import Logica.Entidades.ObjetoGrafico;

import javax.swing.*;
import java.awt.*;

public class ProyectilGrafico extends ObjetoGrafico {
    protected String imagen;

    public static final int DESINTEGRA = 1;
    public static final int STUN = 2;

    public ProyectilGrafico(int x, int y){
        this.setAlignmentX(x);
        this.setAlignmentY(y);
        imagen = "proyectil";
        this.setBounds(x,y,32,14);

    }

    public void moverPixeles(int cant){
        this.setBounds(this.getX()+cant,this.getY(),32,14);
        //this.repaint();
    }


    @Override
    public String getRefImagen() {
        return imagen;
    }

    public void setEstadoSuper(){


                imagen = "superProyectil";
                this.setBounds(this.getX(),this.getY(),600,73);


    }

}
