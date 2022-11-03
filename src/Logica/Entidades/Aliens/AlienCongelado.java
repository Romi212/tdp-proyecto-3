package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Nave;

public class AlienCongelado implements Estado{
    protected Alien alien;
    protected int efectoMax = 10;
    protected int timing = 0;
    public AlienCongelado(Alien a){
        alien = a;
        a.getAlienG().congelado();
    }

    public void hacerAccion() {
        timing++;
        if(timing == efectoMax){
            alien.cambiarAAlienCaminando();
            timing = 0;
        }
    }
}
