package Logica.Entidades.Aliens;

import Logica.Entidades.ColumnaFinal;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.Proyectil;

public interface Visitor {
    public void colisionProyectilAlien(Proyectil p);
    public void colisionNaveAlien(Nave nave);
    public void colisionColumnaFinal(ColumnaFinal f);
}
