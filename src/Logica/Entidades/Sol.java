package Logica.Entidades;

import javax.swing.*;

public class Sol extends ObjetoGrafico {

    private SolGrafico solG;
    public Sol(int x, int y){
        //solG = solG.getInstance();
        this.setBounds(200,y,30,30);


    }
	public void actionPerformed(){

    }


    @Override
    public String getRefImagen() {
        return "sol";
    }
}
