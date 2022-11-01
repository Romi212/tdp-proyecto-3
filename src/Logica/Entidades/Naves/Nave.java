package Logica.Entidades.Naves;

public abstract class Nave extends ObjetoColisionable{
	protected int vida;
	protected int precio;
	protected int contadorC;
	protected NaveGrafica naveG;
	protected int posX;
	protected int posY;
	
	public boolean estaViva() {
		return vida>0;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void destruir(){}

	public abstract void pasoXTiempo();

	public void bajarVida(int vida){
		this.vida-=vida;
	}

	public int getPosX(){
		return posX;
	}

	public int getPosY(){
		return posY;
	}

}
