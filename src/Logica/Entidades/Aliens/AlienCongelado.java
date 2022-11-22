package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Nave;

public class AlienCongelado implements Estado{
    protected Alien alien;
    protected final static int TIEMPO_ACCION = 10;
    protected int pasoXTiempo = 0;
    public AlienCongelado(Alien a){
        alien = a;
        a.getAlienG().congelado();
    }

    @Override
    public void hacerAccion() {
        pasoXTiempo++;
        if(pasoXTiempo == TIEMPO_ACCION){
            alien.cambiarAAlienCaminando();
            pasoXTiempo = 0;
        }
    }
}
