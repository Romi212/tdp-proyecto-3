package Logica.Entidades.Naves;

import Logica.Fila;
import Logica.Entidades.Aliens.Visitor;

public abstract class Nave extends ObjetoColisionable{
	protected int vida;
	protected int precio;
	protected int contadorC;
	protected NaveGrafica naveG;
	protected Fila fila;
	protected int columna;

	public Nave(Fila f, int col, int x, int y, String skin) {
		fila = f;
		columna = col;
		contadorC = 0;
		naveG = new NaveGrafica(x,y,skin);
		setHitBox(naveG.getBounds());
	}

	public boolean estaViva() {
		return vida>0;
	}
	
	public int getPrecio() {
		return precio;
	}

	//Se elimina de su fila
	public void destruir(){ fila.removerNave(this); }

	public abstract void pasoXTiempo();

	public void bajarVida(int vida){
		this.vida-=vida;
	}
	
	public void accept(Visitor v) { v.colisionNaveAlien(this); }

	public NaveGrafica getNaveG(){ return naveG; }

	public int getColumna(){ return columna; }

}
