package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public class SateliteNoche extends NaveSol{
	
	public SateliteNoche(Fila f, int col, int x, int y) {
		super(f, col, x, y,"naveDImg");
		vida = 100;
	}
}
