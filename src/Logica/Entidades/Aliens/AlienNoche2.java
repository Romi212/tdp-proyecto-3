package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Nave;

public class AlienNoche2 extends Alien{

	public AlienNoche2(int x, int y) {
		super(x, y, "alienBImg");
		vida = 390;
		danio = 10;
	}

	/*Este alien se caracteriza por ser un kamikaze, cuando toca una nave la mata y se destruye*/
	public void colisionNaveAlien(Nave n){
		if(chequearColision(n)) {
			n.destruir();
			this.destruir();
		}
	}

}
