package Logica.Entidades.Naves;

import java.awt.Rectangle;
import Logica.Entidades.Aliens.*;

public class Proyectil extends ObjetoColisionable {
	protected ProyectilGrafico proyectilGrafico;

	protected static final int MOV = 10;
	protected static final int FIN_TABLERO = 870;
	protected static final int DANIO = 50;
	protected boolean estaVivo;
	
	public Proyectil(int x, int y) {
		estaVivo = true;
		proyectilGrafico = new ProyectilGrafico(x,y);
		setHitBox(proyectilGrafico.getBounds());
	}


	public Rectangle getHitBox() {
		int x = proyectilGrafico.getX();
		int y = proyectilGrafico.getY();
		int heigh = proyectilGrafico.getHeight();
		int width = proyectilGrafico.getWidth();
		return new Rectangle(x,y,width,heigh);
	}
	public int getDanio() { return DANIO; }
	public void accept(Visitor v) { v.colisionProyectilAlien(this); }

	/* Se mueve tantos pixeles a la derecha en pantalla como indique la constante MOV, delega esta operacion al proyectil grafico.
	* Si alcanza el final del tablero se destruye */
	public void pasoXTiempo() {
		if(proyectilGrafico.getBounds().getX()>FIN_TABLERO){
			destruir();
		}
		proyectilGrafico.moverPixeles(MOV);
	}

	public ProyectilGrafico getProyectilGrafico() { return proyectilGrafico; }

	public void destruir() { estaVivo = false; }

	public boolean estaVivo(){ return estaVivo; }

}
