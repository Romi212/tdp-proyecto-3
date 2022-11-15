package Logica.Entidades.Naves;

import java.awt.Rectangle;
import Logica.Entidades.Aliens.*;

public class Proyectil extends ObjetoColisionable {
	protected int danio;

	protected boolean estaVivo;
	protected ProyectilGrafico proyectilGrafico;

	protected static final int MOV = 10;

	protected static final int FIN_TABLERO = 870;
	
	public Proyectil(int x, int y) {
		estaVivo = true;
		proyectilGrafico = new ProyectilGrafico(x,y);
		setHitbox(proyectilGrafico.getBounds());
		danio = 50;
	}
	
	public Rectangle getHitBox() {
		int x = proyectilGrafico.getX();
		int y = proyectilGrafico.getY();
		int heigh = proyectilGrafico.getHeight();
		int width = proyectilGrafico.getWidth();
		return new Rectangle(x,y,width,heigh);
	}
	public int getDanio() {
		return danio;
	}
	public void accept(Visitor v) {
		//Comento porque quiero probar las colisiones de alien-nave
		v.colisionProyectilAlien(this);
	}
	public void pasoXTiempo() {
		if(proyectilGrafico.getBounds().getX()>FIN_TABLERO){
			destruir();
		}
		proyectilGrafico.moverPixeles(MOV);
	}
	public ProyectilGrafico getProyectilGrafico() {
		return proyectilGrafico;
	}

	public void destruir() {
		estaVivo = false;
	}

	public boolean estaVivo(){
		return estaVivo;
	}
}
