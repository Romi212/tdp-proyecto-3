package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Nave;

public class AlienComiendo implements Estado{
    protected Alien alien;
    protected Nave nave;
    protected int pasoXTiempo;
    protected final int TIEMPO_ACCION = 3;

    public AlienComiendo(Alien a, Nave n){
        nave = n;
        alien = a;
        pasoXTiempo = 0;
    }

    @Override
    public void hacerAccion() {
        pasoXTiempo++;
        if (pasoXTiempo == TIEMPO_ACCION){
            nave.bajarVida(alien.getDanio());
            pasoXTiempo = 0;
        }

        if(!nave.estaViva()) {
            nave.destruir();
            alien.cambiarAAlienCaminando();
        }


    }
}
