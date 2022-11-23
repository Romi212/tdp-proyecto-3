package Logica.Entidades.Aliens;

import Logica.Entidades.ColumnaFinal;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Entidades.Naves.Proyectil;

import java.awt.*;

public abstract class Alien implements Visitor{
	
	protected int vida;
	protected int danio;
	protected AlienGrafico alienG;
	protected Rectangle hitbox;
	protected Estado estado;
	protected int fila;
	protected int velocidad;
	protected boolean estabaCaminando; /* Esta variable se utiliza para detectar las colisiones cuando un alien pasa a estar comiendo,
										de tal forma que se cree el estado comiendo una unica vez por colision */

	public Alien(int x, int y, String c){
		alienG = new AlienGrafico(x, y, c, 1);
		estado = new AlienCaminando(this);
		hitbox = alienG.getBounds();
		fila = -1;
		velocidad = 5;
		estabaCaminando = true;
	}

	//Reduce la vida del alien en d unidades
	public void daniar(int d){ vida -= d; }

	public int getVida(){ return vida; }

	public boolean estaVivo(){
		return vida > 0;
	}

	public Rectangle getHitbox(){ return hitbox; }

	//Delega la operacion al estado en el que se encuentre en el momento que se llamo el metodo
	public void hacerAccion(){
		estado.hacerAccion();
		hitbox = alienG.getBounds();
	}

	public AlienGrafico getAlienG(){ return alienG; }

	public int getDanio(){ return danio; }

	public void setFila(int f){ fila = f; }

	public int getFila(){ return fila; }

	public int getVelocidad(){ return velocidad; }

	public void destruir(){ vida = 0; }

	//Metodos de la interface Visitor, se utilizan para checkear las colisiones
	@Override
	public void colisionNaveAlien(Nave n){
		if(chequearColision(n)) {
			cambiarAAlienComiendo(n);
		}
	}

	@Override
	public void colisionProyectilAlien(Proyectil p){
		if(chequearColision(p) && p.estaVivo()){
			if(vida - p.getDanio() > 0)
				vida -= p.getDanio();
			else
				vida = 0;
			p.destruir();
		}

	}

	@Override
	public void colisionColumnaFinal(ColumnaFinal f) {
		if(chequearColision(f)){
			f.avistoTerminoJuego();
		}
	}

	/*Checkea las colisiones entre el alien grafico y el objeto pasado por parametro mediante el metodo intersect de la clase Rectangle
	 (las hitbox de los objetos colisionables son instancias de dicha clase) */
	private boolean chequearColision(ObjetoColisionable o){
		return o.getHitBox().intersects(hitbox);
	}

	//Metodos para cambiar el estado del alien

	public void cambiarAAlienComiendo(Nave nave){
		if(estabaCaminando) {
			estado = new AlienComiendo(this, nave);
			estabaCaminando = false;
		}
	}

	public void cambiarAAlienCaminando(){
		estado = new AlienCaminando(this);
		estabaCaminando = true;
	}

	public void cambiarAAlienCongelado(){ estado = new AlienCongelado(this); }

}
