package Logica.Entidades;

import Logica.Entidades.Aliens.Visitor;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Logica;

import java.awt.*;

public class ColumnaFinal extends ObjetoColisionable {

    private Logica logica;

    public ColumnaFinal(Rectangle h, Logica l) {
        setHitBox(h);
        this.logica = l;
    }

    @Override
    public void accept(Visitor v) {
        v.colisionColumnaFinal(this);
    }

    /* Delega la terminacion del juego a logica, se llama cuando un alien intersecta con la columna final (el jugador pierde) */
    public void avistoTerminoJuego(){
        logica.terminoJuego();
    }
}
