package Logica.Entidades.Aliens;

import javax.swing.*;

public class AlienGrafico extends JLabel {

	protected String clave;
	protected int x;
	protected int y;

	public AlienGrafico(int px, int py, String c) {
		clave = c;
		x = px;
		y = py;
	}

	public void moverPixeles(int cant){
		x -= cant;
	}

	public void caminando(){
		//this.setIcon(skin1);
	}
	public void comiendo(){
		//this.setIcon(skin1);
	}
	public void congelado(){
		//this.setIcon(skin1);
	}
}
