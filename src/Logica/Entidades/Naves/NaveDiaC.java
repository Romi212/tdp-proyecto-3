package Logica.Entidades.Naves;
import Logica.Entidades.Aliens.Alien;
import Logica.Fila;
import java.awt.*;
import java.util.LinkedList;

public class NaveDiaC extends NaveDisparo {
    public NaveDiaC(Fila f, int x, int y, Rectangle h){ super(f, x, y, h,"naveCImg"); }

    /*Redefine el metodo para generar un laser que afecta a todos los aliens de su fila. Muere luego de causar el daño */
    public void generarProyectil(int x, int y) {
        super.generarProyectil(x, y);
        LinkedList<Alien> aliensFila = fila.getAliens();
        for(int i=0; i < aliensFila.size(); i++) {
            Alien actual = aliensFila.get(i);
            int danio = actual.getVida();
            actual.daniar(danio);
        }

        vida = 0;
    }
}
