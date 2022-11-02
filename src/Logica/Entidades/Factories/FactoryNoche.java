package Logica.Entidades.Factories;

import Logica.Fila;
import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Aliens.AlienNoche1;
import Logica.Entidades.Aliens.AlienNoche2;
import Logica.Entidades.Aliens.AlienNoche3;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.NaveNocheA;
import Logica.Entidades.Naves.NaveNocheB;
import Logica.Entidades.Naves.SateliteNoche;

public class FactoryNoche implements ObjectsFactory{

	public Alien createAlien1(int x, int y) {
		return new AlienNoche1(x,y);
	}

	public Alien createAlien2(int x, int y) {
		return new AlienNoche2(x,y);
	}

	public Alien createAlien3(int x, int y) {
		return new AlienNoche3(x,y);
	}

	public Nave createNaveA(Fila f, int x, int y) {
		return new NaveNocheA(f,x,y);
	}

	public Nave createNaveB(Fila f, int x, int y) {
		return new NaveNocheB(f,x,y);
	}

	public Nave createSatelite(Fila f, int x, int y) {
		return new SateliteNoche(f,x,y);
	}

}
