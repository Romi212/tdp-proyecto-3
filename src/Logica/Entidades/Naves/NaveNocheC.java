package Logica.Entidades.Naves;
import Logica.Entidades.Aliens.Alien;
import Logica.Fila;

public class NaveNocheC extends NaveDisparo{
    public NaveNocheC(Fila f, int col, int x, int y){
        super(f, col, x, y,"naveCImg");
        vida = 400;
    }

    public void pasoXTiempo(){
        if(fila.hayAliens()) {
            contadorC++;

            if (vida > 0 && contadorC == MAXTIEMPO) {

                    contadorC = 0;
                    generarProyectil((int) getHitBox().getCenterX(), (int) getHitBox().getCenterY());

            }

            if(vida <= 0 && contadorC== MAXTIEMPO/2)  fila.removerNave(this);
        }
    }

    /* Redefine el metodo para congelar a todos los aliens de la fila. Muere luego de causar el daño */
    public void generarProyectil(int x, int y){

            SuperProyectil p = new SuperProyectil(x,y-naveG.getTam()/2);
            fila.agregarProyectil(p);
            for(Alien actual : fila.getAliens()){
                actual.cambiarAAlienCongelado();
            }
            vida = 0;

        }


}
