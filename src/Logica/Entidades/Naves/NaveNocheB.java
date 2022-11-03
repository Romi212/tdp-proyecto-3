package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public class NaveNocheB extends NaveDisparo {
	
	 protected int tiempoCongelar;
	 
	 public NaveNocheB(Fila f, int x, int y, Rectangle h){
		 super(f, x, y, h);
		 tiempoCongelar = 1000;
	 }

	//Redefine el metodo para generar dos proyectiles en lugar de uno
	public void generarProyectil(int x, int y){
		super.generarProyectil(x, y);
		// super.generarProyectil(x, y + ??? );
	}
}
