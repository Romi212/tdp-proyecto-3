package Logica.Manejadores;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Logica;
import Logica.Fila;

import java.util.LinkedList;

public class ManejadorAliens extends Thread {

    private LinkedList<Alien> proximosAliens;
    private int aliensPorHorda;

    private Fila[] tablero;

    private Logica logica;

    private boolean noTerminoJuego;

    private final int TIEMPO = 30;

    public ManejadorAliens(LinkedList<Alien> aliens, Logica logica, Fila[] tablero){
        proximosAliens = aliens;
        aliensPorHorda = aliens.size()/3;

        this.logica = logica;
        this.tablero = tablero;
        noTerminoJuego = true;
    }

    /* Remueve los aliens de la lista de proximos aliens, los agrega a su fila correspondiente y le informa a logica que muestre el cartel de la horda.
    Si ya se generaron todos los aliens de la lista le informa a logica que termino el nivel.*/
    private void generarHorda(){
        boolean quedan = proximosAliens.size()>0;

        if(!quedan){
            logica.terminoNivel();
        }else {
            Alien a;
            int resto = proximosAliens.size() % 3;
            for (int i = 0; i < aliensPorHorda  + resto && quedan; i++) {
                if (proximosAliens.size() > 0) {
                    a = proximosAliens.removeFirst();
                    tablero[a.getFila()].agregarAlien(a);
                } else quedan = false;
            }
            logica.mostrarCartelHorda();
        }
    }

    /* Por cada fila del tablero les informa a los aliens que realicen una accion (segun su estado sera caminar, comer una nave, permanecer congelado o cambiar de estado).
    *  Checkea las colisiones con la columna final y, si hay naves en la fila, las colisiones de los aliens con los proyectiles ambos mediante el patron visitor */
    @Override
    public void run() {
        Iterable<Alien> aliensFila;
        boolean hayZombies;
        Iterable<ObjetoColisionable> objetos;

        while(noTerminoJuego){
            hayZombies = false;
            for(Fila f : tablero){
                aliensFila= f.getAliens();
                for(Alien a: aliensFila){
                    hayZombies = true;
                    a.hacerAccion();
                    logica.actualizarGrafico(a.getAlienG());
                    try {
                        sleep(TIEMPO);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    f.getColumna().accept(a);
                    if(f.hayNaveEnFila() ) {
                        objetos = f.getColisionables();
                        for (ObjetoColisionable o : objetos) {
                            o.accept(a);
                        }

                    }

                }
            }
            if(!hayZombies) generarHorda();

        }

    }

    /* Destruye todos los aliens de la lista, se utiliza cuando termina el juego */
    public void detener(){
        for(Alien a : proximosAliens){
            a.destruir();
        }
        noTerminoJuego = false;

    }

}
