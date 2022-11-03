package Logica.Entidades;

import javax.swing.JLabel;

public class SolGrafico {
	private static SolGrafico instancia;
	private String imagen;
	
	private SolGrafico() {
		//imagen = 
	}
	
	public SolGrafico getInstance() {
		if(instancia==null)
			instancia = new SolGrafico();
		return instancia;
	}
	
	public String getImagen() {
		return imagen;
	}
}
