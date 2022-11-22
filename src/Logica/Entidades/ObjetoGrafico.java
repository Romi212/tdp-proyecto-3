package Logica.Entidades;

import javax.swing.*;

abstract public class ObjetoGrafico extends JLabel {

    protected boolean cambio; //Variable que simboliza el cambio del path de la imagen del objeto grafico, se utiliza para actualizar la JLabel en pantalla

    public ObjetoGrafico(){ cambio = false; }

    abstract public String getRefImagen();

    public boolean getCambio(){ return cambio; }

    public void setCambio(boolean c){ cambio = c; }


}
