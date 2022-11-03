package Logica;

import java.util.Iterator;
import java.util.LinkedList;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Entidades.Naves.Proyectil;
import Logica.Entidades.Sol;
import Logica.Manejadores.ManejadorAliens;

public class Fila {
	protected Nave primeraNave;
	protected Logica logica;
	protected LinkedList<Proyectil> listaProyectiles;
	protected LinkedList<Alien> listaAliens;
	protected Nave[] listaNaves;



	public Fila(Logica l){
		this.logica = l;
		listaNaves = new Nave[9];
		for(int i = 0; i < 9;i++){
			listaNaves[i] = null;
		}
	}

	private void removerAlien(Alien z){
		 listaAliens.remove(z);
	}

	private void removerProyectil(Proyectil p){
		listaProyectiles.remove(p);
	}

	public LinkedList<Alien> getAliens(){
		return listaAliens;
	}

	public LinkedList<Proyectil> getProyectiles(){
		return listaProyectiles;
	}

	public Nave getPrimerNave(){ return primeraNave; }
	public void agregarNave(Nave p, int posY){
		listaNaves[posY] = p;
	}

	public void agregarAlien(Alien a){
		listaAliens.addLast(a);
	}

	public void agregarProyectil(Proyectil p){
		listaProyectiles.addLast(p);
	}
	
	public void agregarSol(Sol s){
		logica.agregarObjetoGrafico(s);
	}
	
	public LinkedList<ObjetoColisionable> getColisionables() {
		LinkedList<ObjetoColisionable> l = new LinkedList<ObjetoColisionable>();
		l.addLast(primeraNave);
		Iterator<Proyectil> lP = listaProyectiles.iterator();
		while(lP.hasNext()) {
			l.addLast(lP.next());
		}
		return l;
		
	}




}
