package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public class NaveDiaA extends NaveDisparo{
	
	 public NaveDiaA(Fila f, int col, int x, int y){
		 super(f, col, x, y, "naveAImg");
		 vida = 200;
	 }
}
