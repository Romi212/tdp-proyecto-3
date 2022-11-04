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

		this.setBounds(px, py, 119, 84);
	}

	public void moverPixeles(int cant){

		this.setBounds(x-cant, y, 119, 84);
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

	public int getPX(){
		return x;
	}

	public int getPY(){
		return y;
	}

	public String getClave(){
		return clave;
	}
}
