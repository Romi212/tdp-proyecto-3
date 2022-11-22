package Logica.Entidades.Naves;

import Logica.Fila;

public class NaveNocheB extends NaveDisparo {
	
	 protected int tiempoCongelar;
	 
	 public NaveNocheB(Fila f, int col, int x, int y){
		 super(f, col, x, y,"naveBImg");
		 tiempoCongelar = 1000;
		 vida = 300;
	 }

	//Redefine el metodo para generar cuatro proyectiles
	public void generarProyectil(int x, int y){
		int cuartoImg = naveG.getTam()/4;
		int sextoImg = naveG.getTam()/6;
		super.generarProyectil(x, y - cuartoImg);
		super.generarProyectil(x, y + cuartoImg);

		super.generarProyectil(x - sextoImg, y - cuartoImg);
		super.generarProyectil(x - sextoImg, y + cuartoImg);
	}
}
