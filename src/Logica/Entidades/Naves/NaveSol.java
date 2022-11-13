package Logica.Entidades.Naves;

import Logica.Entidades.Sol;
import Logica.Fila;

import java.awt.*;

public abstract class NaveSol extends Nave {
    protected static int MAXTIEMPO = 30;
    public NaveSol(Fila f, int col, int x, int y, String skin) {
        super(f,col,x,y, skin);
    }

    public void pasoXTiempo(){
        contadorC ++;
        if(contadorC == MAXTIEMPO){
            contadorC = 0;
            generarSol();
        }

    }
    
    public void generarSol(){
        Sol s = new Sol(naveG.getBounds().x,naveG.getBounds().y);
        fila.agregarSol(s.getSolGrafico());
        //s.mostrarEnPantalla();
    }
}
