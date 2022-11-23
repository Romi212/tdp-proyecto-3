package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Proyectil;

public class AlienDia3 extends Alien{

	protected int cantInvulnerable;

	public AlienDia3(int x, int y) {
		super(x, y,  "alienCImg");
		vida = 400;
		danio = 30;
		cantInvulnerable = 5;
	}

	/*Este alien se caracteriza por ser invulnerable los primeros 5 disparos*/
	public void colisionProyectilAlien(Proyectil p){
		if(chequearColision(p) && p.estaVivo()){
			cantInvulnerable--;
			if(cantInvulnerable <= 0){
				if(vida - p.getDanio() > 0)
					vida -= p.getDanio();
				else
					vida = 0;
			}
			p.destruir();


		}

	}

}
