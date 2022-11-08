package Logica.Entidades;

import Logica.Entidades.Aliens.Visitor;
import Logica.Entidades.Naves.ObjetoColisionable;

import java.awt.*;

public class ColumnaFinal extends ObjetoColisionable {


    public ColumnaFinal(Rectangle h) {
        super(h);
    }

    @Override
    public void accept(Visitor v) {
        v.colisionColumnaFinal();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
