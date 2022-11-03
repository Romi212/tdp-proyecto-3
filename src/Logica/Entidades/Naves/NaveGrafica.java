package Logica.Entidades.Naves;

import javax.swing.*;

public class NaveGrafica extends JLabel {
    protected String skin;

    public NaveGrafica(int x, int y, String skin){
        this.setAlignmentX(x);
        this.setAlignmentY(y);
        this.skin = skin;
    }

}
