package Logica.Entidades.Naves;

import Logica.Fila;

public class NaveDiaB extends NaveDisparo{
	
	 public NaveDiaB(Fila f, int col, int x, int y){
		 super(f, col, x, y, "naveBImg");
		 vida = 300;
	 }
	 
	 //Redefine el metodo para generar dos proyectiles en lugar de uno
	 public void generarProyectil(int x, int y){
		 int cuartoImg = naveG.getTam()/4;
		 super.generarProyectil(x, y - cuartoImg);
		 super.generarProyectil(x, y + cuartoImg);

	 }
}
