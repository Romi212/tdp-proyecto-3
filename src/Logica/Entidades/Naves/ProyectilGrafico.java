package Logica.Entidades.Naves;

import javax.swing.*;
import java.awt.*;

public class ProyectilGrafico extends JLabel {
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



}
