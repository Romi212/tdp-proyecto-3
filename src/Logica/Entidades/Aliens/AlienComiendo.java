package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Nave;

import java.util.concurrent.ThreadLocalRandom;

public class AlienComiendo implements Estado{
    protected Alien alien;
    protected Nave nave;
    protected int pasoXTiempo;
    protected final int tiempoDeAccion = 3;

    public AlienComiendo(Alien a, Nave n){
        nave = n;
        alien = a;
        pasoXTiempo = 0;
        a.getAlienG().comiendo();

    }

    @Override
    public void hacerAccion() {
        pasoXTiempo++;
        if (pasoXTiempo == tiempoDeAccion){
            nave.bajarVida(alien.getDanio());
            pasoXTiempo = 0;
        }

        if(!nave.estaViva()) {
            nave.destruir();
            alien.cambiarAAlienCaminando();
        }


    }
}
