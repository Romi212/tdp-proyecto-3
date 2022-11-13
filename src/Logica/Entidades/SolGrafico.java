package Logica.Entidades;

import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolGrafico extends ObjetoGrafico  {

	protected Sol sol;
	public SolGrafico(int x, int y, Sol s) {
		sol = s;
		this.setBounds(x, y,30,30);
	}
	@Override
	public String getRefImagen() { return "sol"; }

	public int getCantSol(){
		return  sol.getSoles();
	}



}
