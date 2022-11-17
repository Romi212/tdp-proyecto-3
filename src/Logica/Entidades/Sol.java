package Logica.Entidades;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;
import Logica.Fila;
import Logica.Ventana;
import Logica.Entidades.SolGrafico;

import java.util.Iterator;

public class Sol{
    protected static SolGrafico solG;
    protected final int solesInicialesDelJugador = 300;
    protected final int aumento = 25;
    protected static Sol instancia;

    private Sol(int x, int y){
        solG = new SolGrafico(x,y,this);
    }

    public static Sol getInstancia(int x, int y) {
        if(instancia == null){
            instancia = new Sol(x,y);
        }

        if(solG.getX() != x || solG.getY() != y){
            instancia = new Sol(x,y);
        }

        return instancia;
    }

    public SolGrafico getSolGrafico(){ return solG; }

    public int getSoles(){
        return aumento;
    }


}
