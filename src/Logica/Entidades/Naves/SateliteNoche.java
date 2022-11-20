package Logica.Entidades.Naves;

import Logica.Entidades.SolGrafico;
import Logica.Fila;

import java.awt.*;

public class SateliteNoche extends NaveSol{
	
	public SateliteNoche(Fila f, int col, int x, int y) {
		super(f, col, x, y,"naveDImg");
		vida = 100;
	}

	public void generarSol(){
		SolGrafico s = new SolGrafico(naveG.getBounds().x,naveG.getBounds().y);
		fila.agregarSol(s);
		SolGrafico s2 = new SolGrafico(naveG.getBounds().x+40,naveG.getBounds().y);
		fila.agregarSol(s2);

	}
}
