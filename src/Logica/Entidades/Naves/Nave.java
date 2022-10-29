package Logica.Entidades.Naves;

public abstract class Nave extends ObjetoColisionable{
	protected int vida;
	protected int precio;
	protected int contadorC;
	protected NaveGrafica naveG;
	
	public int getVida() {
		return vida;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public abstract void producirSol();
	public abstract Proyectil producirDisparo();
	public void destruir() {}
}
