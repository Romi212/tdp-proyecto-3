package Logica.Entidades.Naves;

import Logica.Fila;

public class Satelite extends NaveSol{
	
	public Satelite(Fila f, int col, int x, int y) {
		super(f, col, x, y,"naveDImg");
		vida = 100;
	}
}
