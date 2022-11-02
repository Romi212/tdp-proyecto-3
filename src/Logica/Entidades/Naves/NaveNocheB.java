package Logica.Entidades.Naves;

import Logica.Fila;

public class NaveNocheB extends NaveDisparo {
	
	 protected int tiempoCongelar;
	 
	 public NaveNocheB(Fila f, int x, int y){
		 super(f, x, y);
		 tiempoCongelar = 1000;
	 }
	 
	 /* Redefine el metodo para congelar a todos los zombies de su fila. Muere despues de realizar la operacion */
	 public void generarProyectil(){ 
	 }
}
