package Logica.Entidades;

public class Sol{

    protected final int AUMENTO = 25;
    protected final String SKIN = "sol";
    protected static Sol instancia;

    //Utiliza el patron singleton, todas las instancias de Sol Grafico comparten la misma instancia de la clase Sol
    public static Sol getInstancia() {
        if(instancia == null){
            instancia = new Sol();
        }

        return instancia;
    }

    public int getAumento(){
        return AUMENTO;
    }

    public String getSkin(){
        return SKIN;
    }

}
