package Logica.Entidades.Naves;

import Logica.Fila;

public class NaveNocheA extends NaveDisparo{
	
	 public NaveNocheA(Fila f, int col, int x, int y){
		 super(f, col, x, y,"naveAImg");
		 vida = 200;

	 }

	 /* Redefine el metodo para generar tres proyectiles por disparo */
	public void generarProyectil(int x, int y) {
		int sextoImg = naveG.getTam()/6;
		super.generarProyectil(x, y);
		super.generarProyectil(x-sextoImg, y);
		super.generarProyectil(x-sextoImg*2, y);
	}

}
