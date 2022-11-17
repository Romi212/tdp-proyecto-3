package Logica.Manejadores;


import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;
import Logica.Entidades.Sol;
import Logica.Entidades.SolGrafico;
import Logica.Fila;
import Logica.Logica;

import java.util.Iterator;
import java.util.Random;

/* Cuando pasa una cantidad determinada de milisegundos recorre todos los proyectiles y naves de las filas notificando que paso el tiempo.
*  Estos se mueven, disparan o generan soles segun se especifique en el metodo invocado */
public class ManejadorNaves extends Thread {
    private Fila[] tablero;
    private final int TIEMPO = 100;

    private boolean noTerminoJuego;

    private Logica logica;
    public ManejadorNaves(Fila[] filas, Logica log){
        tablero = filas;
        logica = log;
        noTerminoJuego = true;
    }

    private int contador;

    public void run(){
        while (noTerminoJuego){
            try {
                sleep(TIEMPO);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for(Fila f: tablero){
                Iterator<Proyectil> it = f.getProyectiles();

                while(it.hasNext()){
                    Proyectil p = it.next();
                    p.pasoXTiempo();
                    logica.actualizarGrafico(p.getProyectilGrafico());
                }
                Iterable<Nave> naves = f.getNaves();

                for(Nave n : naves){
                    n.pasoXTiempo();
                }
            }
            contador++;
            if(contador == 150){
                Random rand = new Random();
                int posy = rand.nextInt(600) +300;
                tablero[0].agregarSol(new Sol(posy,10).getSolGrafico());
                contador = 0;
            }
        }
    }

    public void detener(){
        noTerminoJuego = false;
    }

}
