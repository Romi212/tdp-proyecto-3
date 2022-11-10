package Logica.Entidades.Aliens;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class AlienCaminando implements Estado{
    protected Alien alien;
    protected int pasoXTiempo;
    protected final int tiempoDeAccion = ThreadLocalRandom.current().nextInt(1, 5);
    protected final int cantPixelesMover = ThreadLocalRandom.current().nextInt(5, 10);
    public AlienCaminando(Alien a){
        alien = a;
        alien.getAlienG().caminando();
        pasoXTiempo = 0;
    }
    public void hacerAccion(){
        pasoXTiempo++;
        if(pasoXTiempo == tiempoDeAccion) {
            alien.getAlienG().moverPixeles(cantPixelesMover);
            pasoXTiempo = 0;
        }
    }
}
