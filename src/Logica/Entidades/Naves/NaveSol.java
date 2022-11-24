package Logica.Entidades.Naves;

import Logica.Entidades.SolGrafico;
import Logica.Fila;

public abstract class NaveSol extends Nave {
    protected static int MAXTIEMPO = 50;
    public NaveSol(Fila f, int col, int x, int y, String skin) {
        super(f,col,x,y, skin);
    }

    /* Si se llamo al metodo MAXTIEMPO veces genera un sol */
    public void pasoXTiempo(){
        contadorC ++;
        if(contadorC == MAXTIEMPO){
            contadorC = 0;
            generarSol();
        }

    }

    /* Crea un sol grafico y lo muestra en pantalla, esto ultimo lo delega a la fila */
    public void generarSol(){
        SolGrafico s = new SolGrafico(naveG.getBounds().x,naveG.getBounds().y);
        fila.agregarSolEnPantalla(s);
    }
}
