package Logica.Manejadores;


import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;
import Logica.Fila;

import java.util.Iterator;

/* Cuando pasa una cantidad determinada de milisegundos recorre todos los proyectiles y naves de las filas notificando que paso el tiempo.
*  Estos se mueven, disparan o generan soles segun se especifique en el metodo invocado */
public class ManejadorNaves extends Thread {
    private Fila[] tablero;
    private final int TIEMPO = 100;
    public ManejadorNaves(Fila[] filas){
        tablero = filas;
    }

    public void run(){
        while (true){
            try {
                sleep(TIEMPO);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for(Fila f: tablero){
                Iterable<Proyectil> proyectiles = f.getProyectiles();

                for(Proyectil p: proyectiles){
                    p.pasoXTiempo();
                }
                Iterable<Nave> naves = f.getNaves();

                for(Nave n : naves){
                    n.pasoXTiempo();
                }
            }

        }
    }

}
