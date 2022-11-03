package Logica.Entidades.Aliens;

import javax.swing.*;

public class AlienGrafico extends JLabel {

	protected String skin1;
	protected String skin2;
	protected String skin3;
	protected int x;
	protected int y;

	public AlienGrafico(int px, int py, String s1, String s2) {
		skin1 = s1;
		skin2 = s2;
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
