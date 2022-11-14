package Logica.Entidades.Aliens;

import Logica.Entidades.ObjetoGrafico;

import javax.swing.*;

public class AlienGrafico extends ObjetoGrafico {

	protected String clave;
	protected int x;
	protected int y;

	public AlienGrafico(int px, int py, String c) {
		clave = c;
		x = px;
		y = py;
		super.setBounds(px, py, 50, 50);
	}

	public void moverPixeles(int cant){
		x = (int) super.getBounds().getX();
		super.setBounds(x-cant, y, 50, 50);
		//super.repaint();
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

	@Override
	public String getRefImagen() {
		return clave;
	}
}
