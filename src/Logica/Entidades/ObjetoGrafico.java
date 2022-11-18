package Logica.Entidades;

import javax.swing.*;

abstract public class ObjetoGrafico extends JLabel {

    protected boolean cambio;

    public ObjetoGrafico(){
        cambio = false;
    }

    abstract public String getRefImagen();


    public boolean getCambio(){
        return cambio;
    }

    public void setCambio(boolean c){
        cambio = c;
    }


}
