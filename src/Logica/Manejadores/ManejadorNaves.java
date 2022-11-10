package Logica.Manejadores;


import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;
import Logica.Fila;

public class ManejadorNaves extends Thread {
    private int contadorSoles;

    private Fila[] tablero;

    public ManejadorNaves(Fila[] filas){
        tablero = filas;
    }

    public void run(){
        while (true){
            try {
                sleep(100);
                System.out.println("Paso X Tiempo");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for(Fila f: tablero){
                Iterable<Proyectil> proyectiles = f.getProyectiles();
                for(Proyectil p : proyectiles){
                    p.pasoXTiempo();
                }
                Iterable<Nave> naves = f.getNaves();

                for(Nave n : naves){
                    n.pasoXTiempo();
                }
            }

    }}




}
