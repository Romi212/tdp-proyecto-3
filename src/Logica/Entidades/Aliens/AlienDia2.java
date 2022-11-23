package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Proyectil;

public class AlienDia2 extends Alien{

	public AlienDia2(int x, int y) {
		super(x, y, "alienBImg");
		vida = 200;
		danio = 20;
	}

	/*Este alien se caracteriza por aumentar en 20 su daño cada que recibe daño*/
	public void colisionProyectilAlien(Proyectil p){
		if(chequearColision(p) && p.estaVivo()){
			if(vida - p.getDanio() > 0) {
				vida -= p.getDanio();
				danio += 20;
			}
			else
				vida = 0;
			p.destruir();
		}

	}

}
