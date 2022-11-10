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


            Random rand = new Random(); //instance of random class
            for (int i = 0; i < aliensPorHorda && quedan; i++) {
                if (proximosAliens.size() > 0) {
                    Alien a = proximosAliens.removeFirst();
                    tablero[a.getFila()].agregarAlien(a);
                } else quedan = false;
            }
        }
    }


    @Override
    public void run() {
        boolean hayZombies = false;
        while(noTerminoJuego){


            for(Fila f : tablero){
                Iterable<Alien> aliensFila= f.getAliens();
                for(Alien a: aliensFila){
                    hayZombies = true;

                    a.hacerAccion();
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if(f.hayNaveEnFila()) {
                        Iterable<ObjetoColisionable> objetos = f.getColisionables();
                        //Las filas vacias tiene como primer elemento a el null de la primera nave
                        //System.out.println("Cantidad de objetos colisionables en fila: " + f.getColisionables().size());
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
