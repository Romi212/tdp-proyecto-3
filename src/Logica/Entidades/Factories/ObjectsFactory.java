package Logica.Entidades.Factories;

import Logica.Entidades.Naves.Nave;
import Logica.Fila;
import Logica.Entidades.Aliens.Alien;

import java.awt.*;

public interface ObjectsFactory {
	
	public Alien createAlien1(int x, int y, Rectangle h);
	public Alien createAlien2(int x, int y, Rectangle h);
	public Alien createAlien3(int x, int y, Rectangle h);
	public Nave createNaveA(Fila f, int x, int y, Rectangle h);
	public Nave  createNaveB(Fila f, int x, int y, Rectangle h);
	public Nave createSatelite(Fila f, int x, int y, Rectangle h);
	
}
