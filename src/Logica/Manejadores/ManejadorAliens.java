package Logica.Manejadores;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.ColumnaFinal;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Logica;
import Logica.Fila;

import java.util.LinkedList;
import java.util.Random;

public class ManejadorAliens extends Thread {

    private LinkedList<Alien> proximosAliens;
    int aliensPorHorda;

    private Fila[] tablero;

    private Logica logica;

    boolean noTerminoJuego;

    private ColumnaFinal fin;

    private final int TIEMPO = 50;

    public ManejadorAliens(LinkedList<Alien> aliens, Logica logica, Fila[] tablero, ColumnaFinal f){
        proximosAliens = aliens;
        aliensPorHorda = aliens.size()/3;

        this.logica = logica;
        this.tablero = tablero;
        noTerminoJuego = true;
        this.fin = f;
    }

    private void generarHorda(){
        boolean quedan = proximosAliens.size()>0;

        if(!quedan){
            logica.terminoNivel(true);
        }else {
            int resto = proximosAliens.size() % 3;
            for (int i = 0; i < aliensPorHorda  + resto && quedan; i++) {
                if (proximosAliens.size() > 0) {
                    Alien a = proximosAliens.removeFirst();
                    tablero[a.getFila()].agregarAlien(a);
                } else quedan = false;
            }
            logica.mostrarCartelHorda();
        }
    }


    @Override
    public void run() {
        while(noTerminoJuego){
            boolean hayZombies = false;

            for(Fila f : tablero){
                Iterable<Alien> aliensFila= f.getAliens();
                for(Alien a: aliensFila){
                    hayZombies = true;
                    a.hacerAccion();
                    logica.actualizarGrafico(a.getAlienG());
                    try {
                        sleep(TIEMPO);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if(f.hayNaveEnFila()) {
                        Iterable<ObjetoColisionable> objetos = f.getColisionables();
                        for (ObjetoColisionable o : objetos) {
                            o.accept(a);
                        }

                    }

                }
            }
            if(!hayZombies) generarHorda();



        }

    }

}
