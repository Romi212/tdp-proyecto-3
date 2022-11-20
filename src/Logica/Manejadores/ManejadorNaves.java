package Logica.Manejadores;


import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;
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

    private int contador;

    private int nivel;

    public ManejadorNaves(Fila[] filas, Logica log, int nivel){
        tablero = filas;
        logica = log;
        noTerminoJuego = true;
        this.nivel = nivel;
    }

    /* Recorre los proyectiles de todas las filas, les notifica que paso el tiempo y delega la actualizacion de su grafica en la pantalla a logica.
    *  Recorre las neves de todas las filas informandoles que paso el tiempo.
    *  Utiliza la variable contador para controlar la aparicion de los soles en pantalla que recolecta el usuario y son independientes a las naves */
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
            if(nivel == logica.NIVEL_0){
                contador++;
                if(contador == 80){
                    Random rand = new Random();
                    int posx = rand.nextInt(600) +300;
                    tablero[0].agregarSol(new SolGrafico(posx,10));
                    contador = 0;
                }
            }

        }
    }

    /* Informa a la fila que elimine todos sus naves, aliens y proyectiles. Se utiliza cuando termina el juego */
    public void detener(){
        for(Fila f: tablero){
            f.limpiar();
        }
        noTerminoJuego = false;
    }

}
