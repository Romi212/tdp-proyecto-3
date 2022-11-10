package Logica.Entidades.Naves;

import Logica.Entidades.Sol;
import Logica.Fila;

import java.awt.*;

public abstract class NaveSol extends Nave {
    public NaveSol(Fila f, int col, int x, int y, String skin) {
        super(f,col,x,y, skin);
    }

    public void pasoXTiempo(){}
    
    public void generarSol(){
        Sol s = new Sol();
        fila.agregarSol(s);
    }
}
