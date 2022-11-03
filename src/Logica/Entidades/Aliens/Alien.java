package Logica.Entidades.Aliens;

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
	protected int velocidad;
	
	public Alien(int x, int y, Rectangle h, String c){
		alienG = new AlienGrafico(x, y, c);
		estado = new AlienCaminando(this);
		hitbox = h;
		vida = 100;
	}

	public void daniar(int danio){
		vida -= danio;
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
		estado = new AlienComiendo(this, nave);
	}

	public void cambiarAAlienCaminando(){
		estado = new AlienCaminando(this);
	}

	public void cambiarAAlienCongelado(){ estado = new AlienCongelado(this);
	}

	public void hacerAccion(){
		estado.hacerAccion();
	}

	public void chequearColision(ObjetoColisionable o){
		if(o.getHitBox().intersects(hitbox))
			o.accept(this);
	}

	public void colisionNaveAlien(Nave n){
		cambiarAAlienComiendo(n);
	}

	public void colisionProyectilAlien(Proyectil p){
		if(vida - p.getDanio() > 0)
			vida -= p.getDanio();
		else
			vida = 0;
		p.destruir();
	}

	public int getVelocidad(){
		return velocidad;
	}

	public AlienGrafico getAlienG(){
		return alienG;
	}

	public int getDanio(){
		return danio;
	}

}
