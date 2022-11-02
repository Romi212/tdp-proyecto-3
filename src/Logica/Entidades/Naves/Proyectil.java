package Logica.Entidades.Naves;

import java.awt.Rectangle;
import Logica.Entidades.Aliens.*;

public class Proyectil extends ObjetoColisionable {
	protected int danio;

	protected boolean estaVivo;
	protected ProyectilGrafico proyectilGrafico;
	
	
	
	public Proyectil(int x, int y, Rectangle h) {
		super(h);
		estaVivo = true;
		proyectilGrafico = new ProyectilGrafico(x,y);

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

	}
	public void pasoXTiempo() {

	}
	public ProyectilGrafico getProyectilGrafico() {
		return proyectilGrafico;
	}

	public void destruir() {

	}

	public boolean estaVivo(){
		return estaVivo;
	}
}
