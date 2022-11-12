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

	public Alien createAlien1(int x, int y) { return new AlienDia1(x, y); } //Robot

	public Alien createAlien2(int x, int y) { return new AlienDia2(x, y); } //Chubby naranja

	public Alien createAlien3(int x, int y) {
		return new AlienDia3(x, y);
	}

	public Nave createNaveA(Fila f, int col, int x, int y) {
		return new NaveDiaA(f, col, x, y);
	} //Dispara simple

	public Nave createNaveB(Fila f, int col, int x, int y) {
		return new NaveDiaB(f, col, x, y);
	} //Disparo doble

	public Nave createNaveC(Fila f, int col, int x, int y) { return new NaveDiaC(f, col, x, y); } //Afecta todos los aliens de la fila

	public Nave createSatelite(Fila f, int col, int x, int y) {
		return new Satelite(f, col, x, y);
	}


}
