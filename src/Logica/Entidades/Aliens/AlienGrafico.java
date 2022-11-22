package Logica.Entidades.Aliens;

import Logica.Entidades.ObjetoGrafico;

public class AlienGrafico extends ObjetoGrafico {

	protected String clave;
	protected int x;
	protected int y;
	protected static final int TAM = 50;

	//El ultimo parametro representa la cantidad de casillas que ocupa la imagen del alien grafico horizontalmente
	public AlienGrafico(int px, int py, String c, int anchoCasillas) {
		clave = c;
		x = px;
		y = py;
		super.setBounds(px, py, TAM*anchoCasillas, TAM);
	}

	//Se desplaza hacia la derecha en pantalla segun el parametro
	public void moverPixeles(int cant){
		x = (int) super.getBounds().getX();
		super.setBounds(x-cant, y, (int) getBounds().getWidth(), (int) getBounds().getHeight());
	}

	/* Cambia la clave de la imagen para mostrar el alien caminando. Actualiza la varible cambio heredada de Objeto Grafico */
	public void caminando(){
		clave = clave.replace("Freeze","");
		cambio = true;
	}

	/* Cambia la clave de la imagen para mostrar el alien congelado. Actualiza la varible cambio heredada de Objeto Grafico */
	public void congelado(){
		clave+="Freeze";
		cambio = true;
	}

	@Override
	public String getRefImagen() { return clave; }
}
