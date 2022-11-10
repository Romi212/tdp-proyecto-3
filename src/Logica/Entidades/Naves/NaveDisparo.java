package Logica.Entidades.Naves;

import Logica.Fila;

import java.awt.*;

public abstract class NaveDisparo extends Nave{


    public NaveDisparo(Fila f, int x, int y, Rectangle h, String skin){
        super(f, x, y, h, skin);
    }

    public void pasoXTiempo(){
        contadorC ++;
        System.out.println(contadorC);
        if(contadorC == 10){
            contadorC = 0;
            generarProyectil((int)getHitBox().getCenterX(), (int)getHitBox().getCenterY());
        }

    }

    public void generarProyectil(int x, int y){
       Proyectil p = new Proyectil(x, y);
        fila.agregarProyectil(p);
        System.out.println("SeGeneroP" );
    }
}
