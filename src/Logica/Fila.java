package Logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Entidades.Naves.Proyectil;
import Logica.Entidades.Sol;
import Logica.Entidades.SolGrafico;
import Logica.Manejadores.ManejadorAliens;

public class Fila {
	protected Nave primeraNave;
	protected Logica logica;
	protected LinkedList<Proyectil> listaProyectiles;
	protected LinkedList<Alien> listaAliens;
	protected Nave[] listaNaves;
	protected int xIni;
	protected int yIni;
	protected int tam;

	public Fila(Logica l,int xinicial, int yInicial, int tam){
		xIni = xinicial;
		yIni = yInicial;
		this.tam = tam;
		this.logica = l;
		listaNaves = new Nave[9];
		for(int i = 0; i < 9;i++){
			listaNaves[i] = null;
		}

		listaAliens = new LinkedList<>();
		listaProyectiles = new LinkedList<>();
	}


	public void removerNave(Nave n){
		logica.sacarObjeto(n.getNaveG());
		listaNaves[n.getColumna()] = null;
		primeraNave = obtenerPrimeraNave();
	}

	private Nave obtenerPrimeraNave(){
		Nave res = null;
		for(int i = listaNaves.length - 1; i >= 0 && res == null; i--){
			if(listaNaves[i] != null)
				res = listaNaves[i];
		}
		return res;
	}

	public void removerProyectil(Proyectil p){
		listaProyectiles.remove(p);
	}

	public LinkedList<Alien> getAliens(){
		List<Alien> eliminados = new ArrayList<Alien>();
		for(Alien a : listaAliens){
			if(!a.estaViva()){
				eliminados.add(a);
				logica.sacarObjeto(a.getAlienG());
			}
		}

		listaAliens.removeAll(eliminados);

		return listaAliens;
	}

	synchronized public Iterator<Proyectil> getProyectiles(){
		List<Proyectil> eliminados = new ArrayList<Proyectil>();
		for(Proyectil a : listaProyectiles){
			if(!a.estaVivo()){
				eliminados.add(a);
				logica.sacarObjeto(a.getProyectilGrafico());
			}
		}

		listaProyectiles.removeAll(eliminados);

		Iterator<Proyectil> iteratorRes = listaProyectiles.iterator();
		return iteratorRes;
	}

	public Nave getPrimerNave(){
		return primeraNave;
	}
	public void agregarNave(Nave p){
		listaNaves[p.getColumna()] = p;
		if(primeraNave == null)
			primeraNave = p;
		else{
			if(primeraNave.getColumna() < p.getColumna())
				primeraNave = p;
		}

	}

	public void agregarAlien(Alien a){
		listaAliens.addLast(a);
	}

	public void agregarProyectil(Proyectil p){
		listaProyectiles.addLast(p);
		logica.agregarObjetoGrafico(p.getProyectilGrafico());
	}
	
	public void agregarSol(SolGrafico s){
		logica.agregarSol(s);
	}
	
	synchronized public LinkedList<ObjetoColisionable> getColisionables() {
		LinkedList<ObjetoColisionable> l = new LinkedList<ObjetoColisionable>();
		l.addLast(primeraNave);
		Iterator<Proyectil> it = getProyectiles();
		while(it.hasNext()){
			Proyectil p = it.next();
			l.addLast(p);
		}


		//Iterator<Proyectil> lP = listaProyectiles.iterator();
		//while(lP.hasNext()) {
		//	Proyectil p = lP.next();
		//	if(p.estaVivo()) l.addLast(p);
		//	//else listaProyectiles.remove(p);
		//}
		return l;
		
	}

	public Iterable<Nave> getNaves(){
		LinkedList<Nave> naves = new LinkedList<>();
		for(Nave n: listaNaves){
			if(n != null) naves.add(n);
		}
		return naves;
	}

	public boolean hayNaveEnFila(){
		return primeraNave != null;
	}

	public int getxIni(){
		return xIni;
	}

	public int getyIni(){
		return yIni;
	}

	public int getTam(){
		return tam;
	}

	public int cantCeldas(){
		return listaNaves.length;
	}

	public boolean estaOcupada(int col){
		return listaNaves[col]!= null;
	}

}
