package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public class NaveNocheB extends NaveDisparo {
	
	 protected int tiempoCongelar;
	 
	 public NaveNocheB(Fila f, int x, int y, Rectangle h){
		 super(f, x, y, h);
		 tiempoCongelar = 1000;
	 }
	 
	 /* Redefine el metodo para congelar a todos los zombies de su fila. Muere despues de realizar la operacion */
	 public void generarProyectil(){ 
	 }
}
