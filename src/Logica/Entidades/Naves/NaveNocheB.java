package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public class NaveNocheB extends NaveDisparo {
	
	 protected int tiempoCongelar;
	 
	 public NaveNocheB(Fila f, int col, int x, int y){
		 super(f, col, x, y,"naveBImg");
		 tiempoCongelar = 1000;
	 }

	//Redefine el metodo para generar dos proyectiles en lugar de uno
	public void generarProyectil(int x, int y){
		super.generarProyectil(x, y);
		// super.generarProyectil(x, y + ??? );
	}
}
