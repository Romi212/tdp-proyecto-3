package Logica;

import java.util.LinkedList;

import Logica.Manejadores.ManejadorZombies;

public class Fila {
	protected int contadorSoles;
	protected int contadorDisparo;
	protected Nave primeraNave;
	protected LinkedList<Proyectil> listaProyectiles;
	protected LinkedList<Alien> listaAliens;
	protected ManejadorZombies manejadorZombie;
	//protected VisitorDisparar visitorDisparar;
	protected LinkedList<Celda> listaCeldas;
	
	
	public Fila() {}
	
	private void removerAlien(Alien z) {}
	private void removerProyectil(Proyectil p) {}
	public Proyectil getPrimerProyectil() {}
	public Alien getPrimerAlien() {}
	public Nave getPrimeraNave() {}
	public LinkedList<Nave> getNaves(){}
	public void agregarNave(Nave p, int posY) {}
	public void checkColisiones(Alien z) {}
	public void disparar() {}
	public void producirSoles() {}
	public void pasoXTiempo() {}
	public void actualizarContadorSoles() {}
	
	
}
