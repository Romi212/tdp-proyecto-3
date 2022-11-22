package Logica.Entidades;

public class SolGrafico extends ObjetoGrafico  {

	protected Sol sol;
	public SolGrafico(int x, int y) {
		sol = Sol.getInstancia();
		this.setBounds(x, y,30,30);
	}

	@Override
	public String getRefImagen() { return sol.getSkin(); }

	public int getAumento(){
		return  sol.getAumento();
	}

}
