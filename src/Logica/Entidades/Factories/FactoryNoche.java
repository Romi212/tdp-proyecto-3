package Logica.Entidades.Factories;

import Logica.Fila;
import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Aliens.AlienNoche1;
import Logica.Entidades.Aliens.AlienNoche2;
import Logica.Entidades.Aliens.AlienNoche3;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.NaveNocheA;
import Logica.Entidades.Naves.NaveNocheB;
import Logica.Entidades.Naves.NaveNocheC;
import Logica.Entidades.Naves.SateliteNoche;

import java.awt.*;

public class FactoryNoche implements ObjectsFactory{

	public Alien createAlien1(int x, int y, Rectangle h) {
		return new AlienNoche1(x, y, h);
	} //Robot

	public Alien createAlien2(int x, int y, Rectangle h) {
		return new AlienNoche2(x, y, h);
	}  //Chubby verde

	public Alien createAlien3(int x, int y, Rectangle h) {
		return new AlienNoche3(x, y, h);
	} //Dragon

	public Nave createNaveA(Fila f, int x, int y) {
		return new NaveNocheA(f, x, y, new Rectangle(x,y,74,74));
	} //Disparo simple

	public Nave createNaveB(Fila f, int x, int y) {
		return new NaveNocheB(f, x, y, new Rectangle(x,y,74,74));
	} //Disparo doble

	public Nave createNaveC(Fila f, int x, int y){ return new NaveNocheC(f, x, y, new Rectangle(x,y,74,74)); } //Congela todos los aliens de la fila

	public Nave createSatelite(Fila f, int x, int y) {
		return new SateliteNoche(f,x,y,new Rectangle(x,y,74,74));
	}

}
