package Logica;

import Logica.Entidades.Aliens.Alien;
import Logica.Manejadores.ManejadorAliens;
import Logica.Manejadores.ManejadorNaves;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

public class Logica {

    private int soles;
    private int nivel;
    private String[] archivos;
    private Fila[] tablero;

    private int cantFilas =6;

    private ManejadorNaves manejadorN;
    private ManejadorAliens manejadorA;

    private Ventana ventana;
    private Properties p;
    public Logica(Ventana v,Properties p){
        this.p = p;
        ventana = v;
        tablero = new Fila[cantFilas];
        for(int i =0; i < cantFilas; i++){
            Fila f = new Fila(this);
            tablero[i] = f;
        }

    }

    private int elegirModoDeJuego(){
        return ventana.elegirModoDeJuego();
    }

    private void crearNivel(int nivel){
        InputStream input = getClass().getResourceAsStream(p.getProperty(archivos[nivel]));

        BufferedReader br
                = null;
        br = new BufferedReader(new InputStreamReader(input));

        String linea;

        try {
            linea= br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int cantAliens = Integer.parseInt(linea);
        LinkedList<Alien> aliens = new LinkedList<>();

        for(int i =0; i< cantAliens;i++){
            //Crea los aliens
        }

        manejadorA = new ManejadorAliens(aliens, this,tablero);

        manejadorN = new ManejadorNaves();

    }


    public void empezarJuego(){
        int modo= elegirModoDeJuego();
        //Se crean las factoies correspondientes y los archivos
        if (modo == 0){
            archivos = new String[2];
            archivos[0] ="archivoD1";
            archivos[1] = "archivoD2";

        }
        else{

        }
        crearNivel(0);

    }

    public void agregarObjetoGrafico(JLabel o){
        ventana.agregarObjeto(o);
    }

    public void terminoNivel(boolean seGano){

    }
}
