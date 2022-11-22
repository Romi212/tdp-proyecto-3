package Logica.Entidades.Naves;

import Logica.Fila;

public abstract class NaveDisparo extends Nave{
    protected static final int MAXTIEMPO = 10;
    public NaveDisparo(Fila f, int col, int x, int y, String skin){
        super(f, col, x, y, skin);
    }

    /* Si hay aliens en la fila para dispararles y se llamo al metodo MAXTIEMPO veces genera un proyectil */
    public void pasoXTiempo(){
        if(fila.hayAliens()) {
            contadorC++;

            if (contadorC == MAXTIEMPO) {
                contadorC = 0;
                generarProyectil((int) getHitBox().getCenterX(), (int) getHitBox().getCenterY());
            }
        }
    }

    /* Crea un proyectil y lo agrega a su fila */
    public void generarProyectil(int x, int y){
        Proyectil p = new Proyectil(x, y);
        fila.agregarProyectil(p);
    }
}
