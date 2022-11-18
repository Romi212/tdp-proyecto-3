package Logica.Entidades;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;
import Logica.Fila;
import Logica.Ventana;
import Logica.Entidades.SolGrafico;

import java.util.Iterator;

public class Sol{

    //protected final int solesInicialesDelJugador = 300;
    protected final int aumento = 25;
    protected final String skin = "sol";
    protected static Sol instancia;

    private Sol(){

    }

    public static Sol getInstancia() {
        if(instancia == null){
            instancia = new Sol();
        }

        //if(solG.getX() != x || solG.getY() != y){
        //    instancia = new Sol(x,y);
        //}

        return instancia;
    }

    //public SolGrafico getSolGrafico(){ return solG; }

    public int getSoles(){
        return aumento;
    }

    public String getSkin(){
        return skin;
    }


}
