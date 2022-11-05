package Logica.Entidades.Naves;

import Logica.Entidades.Sol;
import Logica.Fila;

import java.awt.*;

public abstract class NaveSol extends Nave {
    public NaveSol(Fila f, int x, int y, Rectangle h, String skin) {
        super(f,x,y,h, skin);
    }

    public void pasoXTiempo(){}
    
    public void generarSol(){
        Sol s = new Sol();
        fila.agregarSol(s);
    }
}
