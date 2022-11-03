package Logica.Entidades.Aliens;

import java.awt.*;

public class AlienCaminando implements Estado{
    protected Alien alien;

    public AlienCaminando(Alien a){
        alien = a;
        alien.getAlienG().caminando();
    }
    public void hacerAccion(){
        Rectangle hitbox = alien.getHitbox();
        hitbox.setLocation((int) hitbox.getX() - alien.getVelocidad(), (int) hitbox.getY());
        alien.getAlienG().moverPixeles(alien.getVelocidad());
    }
}
