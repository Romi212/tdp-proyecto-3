package Logica.Entidades.Naves;

public class SuperProyectil extends Proyectil{

    private int contador;

    public SuperProyectil(int x, int y) {
        super(x, y);
        proyectilGrafico.setEstadoSuper();


    }



    public void pasoXTiempo() {
       contador ++;
       if(contador == 5) destruir();
    }
}
