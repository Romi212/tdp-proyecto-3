package Logica.Entidades.Naves;

import Logica.Fila;
import Logica.Entidades.Aliens.Alien;

import java.awt.*;
import java.util.LinkedList;

public class NaveNocheA extends NaveDisparo{
	
	 public NaveNocheA(Fila f, int col, int x, int y){
		 super(f, col, x, y,"naveAImg");
		 vida = 200;

	 }

	public void generarProyectil(int x, int y) {

		super.generarProyectil(x, y);
		super.generarProyectil(x-15, y);
		super.generarProyectil(x-25, y);
	}

}
