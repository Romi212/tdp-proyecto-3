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
	protected boolean estabaCaminando;

	
	public Alien(int x, int y, String c){
		alienG = new AlienGrafico(x, y, c);
		estado = new AlienCaminando(this);
		hitbox = alienG.getBounds();
		fila = -1;
		velocidad = 5;
		estabaCaminando = true;
	}

	public void daniar(int d){
		vida -= d;
	}

	public boolean estaViva(){
		return vida > 0;
	}

	public int getVida() {
		return vida;
	}

	public Rectangle getHitbox(){
		return hitbox;
	}

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

	public void cambiarAAlienCongelado(){ estado = new AlienCongelado(this);
	}

	public void hacerAccion(){
		estado.hacerAccion();
		hitbox = alienG.getBounds();
	}

	private boolean chequearColision(ObjetoColisionable o){
		return o.getHitBox().intersects(hitbox);
	}

	public void colisionNaveAlien(Nave n){
		if(chequearColision(n)) {
			cambiarAAlienComiendo(n);
		}
	}

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

	public AlienGrafico getAlienG(){
		return alienG;
	}

	public int getDanio(){
		return danio;
	}

	public void setFila(int f){
		fila = f;
	}

	public int getFila(){
		return fila;
	}

	public int getVelocidad(){
		return velocidad;
	}

}
