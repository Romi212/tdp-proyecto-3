package Logica.Entidades.Naves;
import Logica.Entidades.Aliens.Alien;
import Logica.Fila;
import java.awt.*;
import java.util.LinkedList;

public class NaveNocheC extends NaveDisparo{
    public NaveNocheC(Fila f, int x, int y, Rectangle h){ super(f, x, y, h); }

    /* Redefine el metodo para congelar a todos los aliens de la fila. Muere luego de causar el daño*/
    public void generarProyectil(int x, int y){
        super.generarProyectil(x, y);
        LinkedList<Alien> aliensFila = fila.getAliens();
        for(int i=0; i < aliensFila.size(); i++) {
            Alien actual = aliensFila.get(i);
            actual.cambiarAAlienCongelado();
        }

        vida = 0;
    }
}
