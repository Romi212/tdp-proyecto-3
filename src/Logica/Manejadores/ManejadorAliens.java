package Logica.Manejadores;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Logica;
import Logica.Fila;

import java.util.LinkedList;
import java.util.Random;

public class ManejadorAliens implements Runnable {

    private LinkedList<Alien> proximosAliens;
    int aliensPorHorda;

    private Fila[] tablero;

    private Logica logica;

    boolean noTerminoJuego;

    public ManejadorAliens(LinkedList<Alien> aliens, Logica logica, Fila[] tablero){
        proximosAliens = aliens;
        aliensPorHorda = aliens.size()/3;

        this.logica = logica;
        this.tablero = tablero;
        noTerminoJuego = true;
    }

    private void generarHorda(){
        boolean quedan = proximosAliens.size()>0;

        if(!quedan){
            logica.terminoNivel(true);
        }else {


            Random rand = new Random(); //instance of random class
            for (int i = 0; i < aliensPorHorda && quedan; i++) {
                if (proximosAliens.size() > 0) {
                    Alien a = proximosAliens.removeFirst();
                    int fila = rand.nextInt(5);
                    tablero[fila].agregarAlien(a);
                } else quedan = false;
            }
        }
    }

    @Override
    public void run() {
        boolean hayZombies = false;
        while(noTerminoJuego){
            /*
            for(Fila f : tablero){
                Iterable<Alien> aliensFila= f.getAliens();
                for(Alien a: aliensFila){
                    hayZombies = true;
                    a.hacerAccion();
                    Iterable<ObjetoColisionable> objetos = f.getColisionables();
                    for(ObjetoColisionable o : objetos){
                        o.accept(a);
                    }

                }
            }
            if(!hayZombies) generarHorda();*/
        }

    }
}
