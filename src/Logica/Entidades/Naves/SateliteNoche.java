package Logica.Entidades.Naves;

import Logica.Entidades.SolGrafico;
import Logica.Fila;

public class SateliteNoche extends NaveSol{
	
	public SateliteNoche(Fila f, int col, int x, int y) {
		super(f, col, x, y,"naveDImg");
		vida = 100;
	}

	/* Redefine el metodo para generar dos soles */
	public void generarSol(){
		SolGrafico s = new SolGrafico(naveG.getBounds().x,naveG.getBounds().y);
		fila.agregarSolEnPantalla(s);

		SolGrafico s2 = new SolGrafico(naveG.getBounds().x+ naveG.getTam()/2,naveG.getBounds().y);
		fila.agregarSolEnPantalla(s2);
	}
}
