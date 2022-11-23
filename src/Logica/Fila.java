package Logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.ColumnaFinal;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Entidades.Naves.Proyectil;
import Logica.Entidades.SolGrafico;

public class Fila {
	protected Nave primeraNave;
	protected Logica logica;
	protected LinkedList<Proyectil> listaProyectiles;
	protected LinkedList<Alien> listaAliens;
	protected Nave[] listaNaves;
	protected int xIni;
	protected int yIni;
	protected final int CANTCELDAS = 9;
	protected ColumnaFinal fin;

	public Fila(Logica l,int xinicial, int yInicial){
		xIni = xinicial;
		yIni = yInicial;
		this.logica = l;
		listaNaves = new Nave[CANTCELDAS];
		for(int i = 0; i < 9;i++){
			listaNaves[i] = null;
		}

		listaAliens = new LinkedList<>();
		listaProyectiles = new LinkedList<>();
	}

	public void setColumna(ColumnaFinal f){
		this.fin = f;
	}

	/* Elimina la nave pasada por parametro de la fila, recalcula la primer nave de la fila. Delega a logica el eliminarla de la pantalla */
	public void removerNave(Nave n){
		logica.sacarObjeto(n.getNaveG());
		listaNaves[n.getColumna()] = null;
		primeraNave = obtenerPrimeraNave();
	}

	/* Recorre las naves de la fila para retornar la primera */
	private Nave obtenerPrimeraNave(){
		Nave res = null;
		for(int i = listaNaves.length - 1; i >= 0 && res == null; i--){
			if(listaNaves[i] != null)
				res = listaNaves[i];
		}
		return res;
	}

	/* Remueve el proyectil pasado por parametro de la lista de proyectiles */
	public void removerProyectil(Proyectil p){
		listaProyectiles.remove(p);
	}

	/* Recorre la lista de aliens eliminando los que estan muertos tanto de la fila como de la pantalla, esto ultimo lo delega a logica.
	*  Retorna la lista actualizada. */
	public Iterable<Alien> getAliens(){
		List<Alien> eliminados = new ArrayList<Alien>();
		for(Alien a : listaAliens){
			if(!a.estaVivo()){
				eliminados.add(a);
				logica.sacarObjeto(a.getAlienG());
			}
		}

		listaAliens.removeAll(eliminados);

		return listaAliens;
	}

	/* Recorre la lista de proyectiles eliminando los que estan muertos tanto de la fila como de la pantalla, esto ultimo lo delega a logica.
	*  Retorna un iterable de la lista actualizada. */
	synchronized public Iterable<Proyectil> getProyectiles(){
		List<Proyectil> eliminados = new ArrayList<Proyectil>();
		for(Proyectil p : listaProyectiles){
			if(!p.estaVivo()){
				eliminados.add(p);
				logica.sacarObjeto(p.getProyectilGrafico());
			}
		}

		listaProyectiles.removeAll(eliminados);
		return listaProyectiles;
	}

	public Nave getPrimerNave(){
		return primeraNave;
	}

	/* Agrega la nave pasada por parametro a la lista de naves y actualiza la primeraNave en caso de ser necesario */
	public void agregarNave(Nave n){
		listaNaves[n.getColumna()] = n;
		if(primeraNave == null)
			primeraNave = n;
		else{
			if(primeraNave.getColumna() < n.getColumna())
				primeraNave = n;
		}

	}

	public void agregarAlien(Alien a){
		listaAliens.addLast(a);
		logica.agregarObjetoGrafico(a.getAlienG());
	}

	/* Agrega el proyectil pasado por parametro a la lista de proyectiles y lo muestra en pantalla, esto ultimo lo delega a logica */
	public void agregarProyectil(Proyectil p){
		listaProyectiles.addLast(p);
		logica.agregarObjetoGrafico(p.getProyectilGrafico());
	}

	/* Delega la operacion a logica */
	public void agregarSolEnPantalla(SolGrafico s){
		logica.agregarSol(s);
	}

	public ColumnaFinal getColumna(){
		return fin;
	}

	synchronized public LinkedList<ObjetoColisionable> getColisionables() {

		LinkedList<ObjetoColisionable> l = new LinkedList<ObjetoColisionable>();
		l.addLast(primeraNave);
		l.addAll(listaProyectiles); //NO PODRIAMOS HACER ESTO DIRECTO? Por ahora anda bien

	/*	Iterator<Proyectil> it = getProyectiles();
		while(it.hasNext()){
			Proyectil p = it.next();
			l.addLast(p);
		} */

		return l;
	}

	/* Retorna un iterable, con referencias no nulas, de la lista de naves */
	public Iterable<Nave> getNaves(){
		LinkedList<Nave> naves = new LinkedList<>();
		for(Nave n: listaNaves){
			if(n != null) naves.add(n);
		}
		return naves;
	}

	/* Retorna verdadero si hay al menos una nave en la fila, falso en caso contrario */
	public boolean hayNaveEnFila(){
		return primeraNave != null;
	}

	public int getxIni(){
		return xIni;
	}

	public int getyIni(){
		return yIni;
	}

	public int getCantCeldas(){
		return CANTCELDAS;
	}

	/* Retorna verdadero si la posicion en la fila, iniciando desde 0, esta ocupada por una nave. Falso en caso contrario*/
	public boolean estaOcupada(int col){
		return listaNaves[col]!= null;
	}

	public boolean hayAliens(){
		return listaAliens.size()>0;
	}

	/* Destruye proyectiles y aliens (se eliminan la proxima vez que los manejadores obtengan los aliens y proyectiles para mantener la sincronizacion),
	   elimina todas las naves de la fila */
	public void limpiar(){
		for(Alien a: listaAliens){
			a.destruir();
		}
		for(Proyectil p: listaProyectiles){
			p.destruir();
		}
		for(int i = 0; i < listaNaves.length; i++){
			if(listaNaves[i] != null){
				removerNave(listaNaves[i]);
			}
		}
	}



}
