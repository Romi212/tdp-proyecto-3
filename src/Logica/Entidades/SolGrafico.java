package Logica.Entidades;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolGrafico extends ObjetoGrafico  {

	protected Sol sol;
	protected int x, y;
	public SolGrafico(int x, int y) {
		sol = Sol.getInstancia();
		this.x = x;
		this.y = y;
		this.setBounds(x, y,30,30);
	}
	@Override
	public String getRefImagen() { return sol.getSkin(); }

	public int getCantSol(){
		return  sol.getSoles();
	}

	public int getX(){ return x; }
	public int getY(){ return y; }
}
