package Logica.Entidades.Aliens;

public class AlienGraficoD extends AlienGrafico{
    public AlienGraficoD(int px, int py, String c){
        super(px, py, c);
        super.setBounds(px, py, TAM*3, TAM);
    }
}
