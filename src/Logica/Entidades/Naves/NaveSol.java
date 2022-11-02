package Logica.Entidades.Naves;

import Logica.Entidades.Sol;
import Logica.Fila;

public abstract class NaveSol extends Nave {
    public NaveSol(Fila f, int x, int y) {
        super(f,x,y);
    }

    public void pasoXTiempo(){}
    
    public void generarSol(){
        Sol s = new Sol();
        fila.agregarSol(s);
    }
}
