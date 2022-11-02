package Logica.Entidades.Naves;

import Logica.Fila;
import Logica.Entidades.Aliens.Alien;

import java.awt.*;
import java.util.LinkedList;

public class NaveNocheA extends NaveDisparo{
	
	 public NaveNocheA(Fila f, int x, int y, Rectangle h){
		 super(f, x, y, h);
	 }
	 
	 /*Redefine el metodo para generar un laser que afecta a todos los aliens de su fila. Muere luego de causar
	  * el da�o */
	 public void generarProyectil(){ 
		 LinkedList<Alien> aliensFila = fila.getAliens();
		 for(int i=0; i < aliensFila.size(); i++) {
			 Alien actual = aliensFila.get(i);
			 int danio = actual.getVida();
			 actual.daniar(danio);
		 }
		 
		 vida = 0;
	 }
}
