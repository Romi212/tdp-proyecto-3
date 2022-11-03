package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public abstract class NaveDisparo extends Nave{
    public NaveDisparo(Fila f, int x, int y, Rectangle h){
        super(f, x, y, h);
    }

    public void pasoXTiempo(){

    }

    public void generarProyectil(int x, int y){
      //  Proyectil p = new Proyectil(x, y, rect);
        //fila.agregarProyectil(p);
    }
}
