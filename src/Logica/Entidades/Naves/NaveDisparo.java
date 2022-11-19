package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public abstract class NaveDisparo extends Nave{
    protected static final int MAXTIEMPO = 10;
    public NaveDisparo(Fila f, int col, int x, int y, String skin){
        super(f, col, x, y, skin);
    }

    public void pasoXTiempo(){
        if(fila.hayAliens()) {
            contadorC++;

            if (contadorC == MAXTIEMPO) {
                contadorC = 0;
                generarProyectil((int) getHitBox().getCenterX(), (int) getHitBox().getCenterY());
            }
        }
    }

    public void generarProyectil(int x, int y){
        Proyectil p = new Proyectil(x, y);
        fila.agregarProyectil(p);

    }
}
