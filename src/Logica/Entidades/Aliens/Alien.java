package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.NaveGrafica;

public abstract class Alien implements Visitor{
	
	protected int vida;
	protected int da�o;
	protected AlienGrafico alienG;
	
	public Alien(int x, int y){
	//	alienG = new AlienGrafico(x, y, skin1, skin2);
	}
	
	public int getVida() {
		return vida;
	}
	
	public void da�ar(int cant) {
		da�o += cant;
	}
}
