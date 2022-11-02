package Logica.Entidades.Factories;

import Logica.Fila;
import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Aliens.AlienDia1;
import Logica.Entidades.Aliens.AlienDia2;
import Logica.Entidades.Aliens.AlienDia3;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.NaveDiaA;
import Logica.Entidades.Naves.NaveDiaB;
import Logica.Entidades.Naves.Satelite;

public class FactoryDia implements ObjectsFactory{

	public Alien createAlien1(int x, int y) {
		return new AlienDia1(x,y);
	}

	public Alien createAlien2(int x, int y) {
		return new AlienDia2(x,y);
	}

	public Alien createAlien3(int x, int y) {
		return new AlienDia3(x,y);
	}

	public Nave createNaveA(Fila f, int x, int y) {
		return new NaveDiaA(f,x,y);
	}

	public Nave createNaveB(Fila f, int x, int y) {
		return new NaveDiaB(f,x,y);
	}

	public Nave createSatelite(Fila f, int x, int y) {
		return new Satelite(f,x,y);
	}


}
