package Logica;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Factories.ObjectsFactory;
import Logica.Entidades.ObjetoGrafico;
import Logica.Manejadores.ManejadorAliens;
import Logica.Manejadores.ManejadorNaves;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Properties;
import java.awt.*;

public class Logica {

    private int soles;
    private int nivel;
    private ObjectsFactory factory;
    private String[] archivos;
    private Fila[] tablero;

    private int cantFilas =6;

    private ManejadorNaves M_Naves;
    private ManejadorAliens M_Aliens;

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

        M_Aliens = new ManejadorAliens(aliens, this,tablero);

        M_Naves = new ManejadorNaves();

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
            archivos = new String[2];
            archivos[0] ="archivoN1";
            archivos[1] = "archivoN2";
        }
        crearNivel(0);

    }

    public void aumentarSoles(int cant){
        soles = soles+ cant;
    }

    public void agregarNave(int x, int y, int tipo){
        Rectangle rect = null;
        switch (tipo){
            case 1:   tablero[x-1].agregarNave(factory.createNaveA(tablero[x-1], x, y, rect), y); break;
            case 2:   tablero[x-1].agregarNave(factory.createNaveB(tablero[x-1], x, y, rect), y); break;
            case 3:   tablero[x-1].agregarNave(factory.createNaveC(tablero[x-1], x, y, rect), y); break;
            case 4:   tablero[x-1].agregarNave(factory.createSatelite(tablero[x-1], x, y, rect), y); break;
        }
    }
    public void agregarObjetoGrafico(ObjetoGrafico o){
        ventana.agregarObjeto(o);
    }

    public void terminoNivel(boolean seGano){

    }
}
