package Logica.Entidades.Aliens;

public class AlienGrafico {

	protected String skin1;
	protected String skin2;
	protected int x;
	protected int y;

	public AlienGrafico(int px, int py, String s1, String s2) {
		skin1 = s1;
		skin2 = s2;
		x = px;
		y = py;
	}

	public void moverPixeles(int cant){
		x -= cant;
	}
}
