package Logica.Entidades.Aliens;

import Logica.Entidades.ObjetoGrafico;

import javax.swing.*;

public class AlienGrafico extends ObjetoGrafico {

	protected String clave;
	protected int x;
	protected int y;
	protected static final int TAM = 50;

	public AlienGrafico(int px, int py, String c) {
		clave = c;
		x = px;
		y = py;
		super.setBounds(px, py, TAM, TAM);
	}

	public void moverPixeles(int cant){
		x = (int) super.getBounds().getX();
		super.setBounds(x-cant, y, (int) getBounds().getWidth(), (int) getBounds().getHeight());
		//super.repaint();
	}

	public void caminando(){
		clave = clave.replace("Freeze","");
		cambio = true;
	}
	public void comiendo(){
		//this.setIcon(skin1);
	}
	public void congelado(){
		clave+="Freeze";
		cambio = true;
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
