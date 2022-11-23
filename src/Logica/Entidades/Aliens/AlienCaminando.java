package Logica.Entidades.Aliens;

import java.util.concurrent.ThreadLocalRandom;

public class AlienCaminando implements Estado{
    protected Alien alien;
    protected int pasoXTiempo;
    protected final int CANT_PIXELES_MOV = ThreadLocalRandom.current().nextInt(5, 10);
    public AlienCaminando(Alien a){
        alien = a;
        alien.getAlienG().caminando();
        pasoXTiempo = 0;
    }

    @Override
    public void hacerAccion(){
        pasoXTiempo++;
        if(pasoXTiempo == alien.getTiempoDeAccion()) {
            alien.getAlienG().moverPixeles(CANT_PIXELES_MOV);
            pasoXTiempo = 0;
        }
    }
}
