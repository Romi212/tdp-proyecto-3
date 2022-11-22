package Logica.Entidades.Aliens;

public class AlienNoche3 extends Alien{

	public AlienNoche3(int x, int y) {
		super(x, y, "alienCImg");
		vida = 400;
		danio = 15;
		alienG = new AlienGrafico(x, y, "alienCImg", 3);
	}

}
