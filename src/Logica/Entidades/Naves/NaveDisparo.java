package Logica.Entidades.Naves;

import Logica.Fila;

public abstract class NaveDisparo extends Nave{
    public NaveDisparo(Fila f, int x, int y) {
        super(f, x, y);
    }

    public void pasoXTiempo(){

    }

    public void generarProyectil(){
        //Proyectil p = new Proyectil();
        //fila.agregarProyectil(p);
    }
}
