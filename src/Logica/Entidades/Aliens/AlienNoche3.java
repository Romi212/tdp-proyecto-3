package Logica.Entidades.Aliens;

import java.awt.*;

public class AlienNoche3 extends Alien{

	public AlienNoche3(int x, int y) {
		super(x, y, "alienCImg");
		vida = 400;
		danio = 15;
		alienG = new AlienGraficoD(x, y, "alienCImg");
	}

}
