package Logica.Entidades;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;
import Logica.Fila;
import Logica.Ventana;
import Logica.Entidades.SolGrafico;

import java.util.Iterator;

public class Sol{
    protected SolGrafico solG;
   // protected int solesDelJugador;
    protected final int solesInicialesDelJugador = 300;
    private final int aumento = 25;
   // private Ventana ventana;
    private final int TIEMPO = 200;

    public Sol(int x, int y){
        solG = new SolGrafico(x,y,this);
        //solesDelJugador = solesInicialesDelJugador;
    }
    public SolGrafico getSolGrafico(){ return solG; }

    /* Actualiza los soles del jugador, tanto la variable como en pantalla */
    public int getSoles(){
        return aumento;
    }

    //public void quitarSolG(){ ventana.sacarObjeto(solG); }

  //  public void mostrarEnPantalla(){ ventana.agregarObjeto(solG); }

}
