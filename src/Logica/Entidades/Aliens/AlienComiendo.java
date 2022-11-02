package Logica.Entidades.Aliens;

import Logica.Entidades.Naves.Nave;

public class AlienComiendo implements Estado{
    protected Alien alien;
    protected Nave nave;

    public AlienComiendo(Alien a, Nave n){
        nave = n;
        alien = a;
    }

    @Override
    public void hacerAccion() {
        while(nave.estaViva()){
            nave.bajarVida(alien.getDanio());
        }
        if(!nave.estaViva())
            nave.destruir();

    }
}
