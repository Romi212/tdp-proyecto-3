package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public class NaveDiaB extends NaveDisparo{
	
	 public NaveDiaB(Fila f, int x, int y, Rectangle h){
		 super(f, x, y, h);
	 }
	 
	 //Redefine el metodo para generar dos proyectiles en lugar de uno
	 public void generarProyectil(int x, int y){
		 super.generarProyectil(x, y);
		// super.generarProyectil(x, y + ??? );
	 }
}
