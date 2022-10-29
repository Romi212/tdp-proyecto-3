package Logica;

import java.util.LinkedList;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;
import Logica.Manejadores.ManejadorAliens;

public class Fila {
	protected int contadorSoles;
	protected int contadorDisparo;
	protected Nave primeraNave;
	protected LinkedList<Proyectil> listaProyectiles;
	protected LinkedList<Alien> listaAliens;
	protected ManejadorAliens manejadorAliens;
	//protected VisitorDisparar visitorDisparar;
	protected LinkedList<Celda> listaCeldas;
	
	
	public Fila() {}
	
	private void removerAlien(Alien z) {}
	private void removerProyectil(Proyectil p) {}
	public Proyectil getPrimerProyectil() {
		return listaProyectiles.getFirst();
	}
	public Alien getPrimerAlien() {
		return listaAliens.getFirst();
	}
	public Nave getPrimeraNave() {
		return primeraNave;
	}
	//public LinkedList<Nave> getNaves(){}
	public void agregarNave(Nave p, int posY) {}
	public void checkColisiones(Alien z) {}
	public void disparar() {}
	public void producirSoles() {}
	public void pasoXTiempo() {}
	public void actualizarContadorSoles() {}
	
	
}
