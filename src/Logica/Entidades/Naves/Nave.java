package Logica.Entidades.Naves;

import Logica.Fila;
import Logica.Entidades.Aliens.Visitor;

import java.awt.*;


/* NAVES:
*
* SOLO DIA: la que dispara toda la fila, satelite
* SOLO NOCHE freeze, sateliteNoche  */
public abstract class Nave extends ObjetoColisionable{
	protected int vida;
	protected int precio;
	protected int contadorC;
	protected NaveGrafica naveG;
	protected int posX;
	protected int posY;
	protected Fila fila;
	protected int columna;

	public Nave(Fila f, int col, int x, int y, String skin) {
		fila = f;
		columna = col;
		//precio =
		contadorC = 0;
		//String s =
		naveG = new NaveGrafica(x,y,skin);
		setHitbox(naveG.getBounds());
		vida = 100;

	}

	public boolean estaViva() {
		return vida>0;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void destruir(){
		System.out.println("Destrui la nave");
		fila.removerNave(this);

	}

	public abstract void pasoXTiempo();

	public void bajarVida(int vida){
		this.vida-=vida;
	}

	public int getPosX(){
		return posX;
	}

	public int getPosY(){
		return posY;
	}
	
	public void accept(Visitor v) {
		v.colisionNaveAlien(this);
	}

	public NaveGrafica getNaveG(){
		return naveG;
	}

	public int getColumna(){
		return columna;
	}

	public int getVida(){
		return vida;
	}

}
