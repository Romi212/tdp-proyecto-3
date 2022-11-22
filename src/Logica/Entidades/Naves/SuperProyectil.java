package Logica.Entidades.Naves;

public class SuperProyectil extends Proyectil{

    private int contador;
    private final static int MAXTIEMPO = 5;

    public SuperProyectil(int x, int y) {
        super(x, y);
        proyectilGrafico.setEstadoSuper();
    }

    //Redefine el metodo para destruirse despues de MAXTIEMPO llamados al metodo
    public void pasoXTiempo() {
       contador ++;
       if(contador == MAXTIEMPO) destruir();
    }
}
