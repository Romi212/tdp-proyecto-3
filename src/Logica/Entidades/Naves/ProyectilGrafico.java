package Logica.Entidades.Naves;

import Logica.Entidades.ObjetoGrafico;

public class ProyectilGrafico extends ObjetoGrafico {
    protected String imagen;

    public ProyectilGrafico(int x, int y){
        this.setAlignmentX(x);
        this.setAlignmentY(y);
        imagen = "proyectil";
        this.setBounds(x, y,32,14);
    }

    //Cambia su posicion en pantalla desplazandose a la derecha segun el parametro
    public void moverPixeles(int cant){
        this.setBounds(this.getX() + cant, this.getY(),32,14);
    }

    @Override
    public String getRefImagen() {
        return imagen;
    }

    //El proyectil ocupa toda la fila y cambia su imagen
    protected void setEstadoSuper(){
        imagen = "superProyectil";
        this.setBounds(this.getX(),this.getY(),600,73);
    }

}
