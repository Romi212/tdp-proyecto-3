package Logica.Entidades.Naves;
import Logica.Entidades.Aliens.Alien;
import Logica.Fila;
import java.awt.*;
import java.util.LinkedList;

public class NaveDiaC extends NaveDisparo {
    public NaveDiaC(Fila f, int col, int x, int y){
        super(f, col,x, y,"naveCImg");
        vida = 400;
    }

    public void pasoXTiempo(){
        if(vida>0){
            if(fila.hayAliens()) {
                contadorC++;

                if ( contadorC == MAXTIEMPO) {

                    contadorC = 0;
                    generarProyectil((int) getHitBox().getCenterX(), (int) getHitBox().getCenterY());

                }

            }
        }else {
            contadorC++;
            if( contadorC>= MAXTIEMPO/2)  fila.removerNave(this);
        }

    }

    /*Redefine el metodo para generar un laser que afecta a todos los aliens de su fila. Muere luego de causar el daño */
    public void generarProyectil(int x, int y) {
        if(fila.hayAliens()){
            SuperProyectil p = new SuperProyectil(x, y-32);
            fila.agregarProyectil(p);
            System.out.println("SeGeneroP" );
           // p.getProyectilGrafico().setDesintegrador();
            LinkedList<Alien> aliensFila = fila.getAliens();
            for(int i=0; i < aliensFila.size(); i++) {
                Alien actual = aliensFila.get(i);
                int danio = actual.getVida();
                actual.daniar(danio);
            }

            vida = 0;
        }

    }
}
