package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public abstract class NaveDisparo extends Nave{
    public NaveDisparo(Fila f, int x, int y, Rectangle h, String skin){
        super(f, x, y, h, skin);
    }

    public void pasoXTiempo(){

    }

    public void generarProyectil(int x, int y){
      //  Proyectil p = new Proyectil(x, y, rect);
        //fila.agregarProyectil(p);
    }
}
