package Logica.Entidades;

import Logica.Entidades.Aliens.Visitor;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Logica;

import java.awt.*;

public class ColumnaFinal extends ObjetoColisionable {

    private Logica logica;

    public ColumnaFinal(Rectangle h, Logica l) {
        setHitbox(h);
        this.logica = l;
    }

    @Override
    public void accept(Visitor v) {
        v.colisionColumnaFinal(this);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void avistoTerminoJuego(){
        logica.terminoJuego();
    }
}
