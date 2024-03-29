package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Proyectil;

public class AlienNoche1 extends Alien{

	public AlienNoche1(int x, int y) {
		super(x, y, "alienAImg");
		vida = 260;
		danio = 5;
	}

	/*Este alien se caracteriza por aumentar su velocidad al maximo cuando recibe un disparo*/
	public void colisionProyectilAlien(Proyectil p){
		if(chequearColision(p) && p.estaVivo()){
			if(vida - p.getDanio() > 0) {
				vida -= p.getDanio();
				tiempoDeAccion = 1;
			}
			else
				vida = 0;
			p.destruir();
		}

	}

}
