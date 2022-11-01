package Logica.Entidades.Naves;

import java.awt.Rectangle;
import Logica.Entidades.Aliens.*;

public class Proyectil extends ObjetoColisionable {
	protected Rectangle hitBox;
	protected int danio;

	protected boolean estaVivo;
	protected ProyectilGrafico proyectilGrafico;
	
	
	
	public Proyectil() {}
	
	
	
	public Rectangle getHitBox() {
		return hitBox;
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
