package Logica.Entidades.Naves;
import Logica.Entidades.Aliens.Alien;
import Logica.Fila;
import java.awt.*;
import java.util.LinkedList;

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

    /* Redefine el metodo para congelar a todos los aliens de la fila. Muere luego de causar el daño*/
    public void generarProyectil(int x, int y){

            SuperProyectil p = new SuperProyectil(x, y-32);
            fila.agregarProyectil(p);
            System.out.println("SeGeneroP" );
            // p.getProyectilGrafico().setDesintegrador();
            LinkedList<Alien> aliensFila = fila.getAliens();
            for(int i=0; i < aliensFila.size(); i++) {
                Alien actual = aliensFila.get(i);
                actual.cambiarAAlienCongelado();
            }

            vida = 0;

        }


}
