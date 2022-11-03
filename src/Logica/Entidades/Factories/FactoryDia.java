package Logica.Entidades.Factories;

import Logica.Fila;
import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Aliens.AlienDia1;
import Logica.Entidades.Aliens.AlienDia2;
import Logica.Entidades.Aliens.AlienDia3;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.NaveDiaA;
import Logica.Entidades.Naves.NaveDiaB;
import Logica.Entidades.Naves.NaveDiaC;
import Logica.Entidades.Naves.Satelite;

import java.awt.*;

public class FactoryDia implements ObjectsFactory{

	public Alien createAlien1(int x, int y, Rectangle h, String c) {
		return new AlienDia1(x, y, h, c);
	} //Robot

	public Alien createAlien2(int x, int y, Rectangle h, String c) {
		return new AlienDia2(x, y, h, c);
	} //Chubby naranja

	public Alien createAlien3(int x, int y, Rectangle h, String c) {
		return new AlienDia3(x, y, h, c);
	}

	public Nave createNaveA(Fila f, int x, int y, Rectangle h) {
		return new NaveDiaA(f, x, y, h);
	} //Dispara simple

	public Nave createNaveB(Fila f, int x, int y, Rectangle h) {
		return new NaveDiaB(f, x, y, h);
	} //Disparo doble

	public Nave createNaveC(Fila f, int x, int y, Rectangle h) { return new NaveDiaC(f, x, y, h); } //Afecta todos los aliens de la fila

	public Nave createSatelite(Fila f, int x, int y, Rectangle h) {
		return new Satelite(f, x, y, h);
	}


}
