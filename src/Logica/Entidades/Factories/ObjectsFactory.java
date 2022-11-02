package Logica.Entidades.Factories;

import Logica.Entidades.Naves.Nave;
import Logica.Fila;
import Logica.Entidades.Aliens.Alien;

public interface ObjectsFactory {
	
	public Alien createAlien1(int x, int y);
	public Alien createAlien2(int x, int y);
	public Alien createAlien3(int x, int y);
	public Nave createNaveA(Fila f, int x, int y);
	public Nave  createNaveB(Fila f, int x, int y);
	public Nave createSatelite(Fila f, int x, int y);
	
}
