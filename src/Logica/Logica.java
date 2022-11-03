package Logica;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Factories.FactoryDia;
import Logica.Entidades.Factories.FactoryNoche;
import Logica.Entidades.Factories.ObjectsFactory;
import Logica.Entidades.ObjetoGrafico;
import Logica.Manejadores.ManejadorAliens;
import Logica.Manejadores.ManejadorNaves;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Logica(Ventana v,Properties p,int xInicial, int yInicial, int tam){
        this.p = p;
        ventana = v;
        tablero = new Fila[cantFilas];
        for(int i =0; i < cantFilas; i++){
            Fila f = new Fila(this, xInicial,yInicial+(i*tam),tam);
            tablero[i] = f;
        }

    }

    private int elegirModoDeJuego(){
        return ventana.elegirModoDeJuego();
    }

    private void crearNivel(int nivel){
        InputStream input = getClass().getResourceAsStream(p.getProperty(archivos[nivel]));

        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(input));
        List lineas = new List();
        //Leo el archivo del nivel
        try {
            String linea = br.readLine();

            while ((linea != null) && (!linea.isEmpty())) {
                lineas.add(linea);
                linea = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        LinkedList<Alien> aliens = new LinkedList<>();
        int cantAlien1 = Integer.parseInt(lineas.getItem(0));
        int cantAlien2 = Integer.parseInt(lineas.getItem(1));
        int cantAlien3 = Integer.parseInt(lineas.getItem(2));
        soles  = Integer.parseInt(lineas.getItem(3));


        /*
        for(int i =0; i< cantAlien1;i++){
            aliens.add(factory.createAlien1());
        }

        for(int i = 0; i < cantAlien2; i++){
            aliens.add(factory.createAlien2());
        }

        for(int i = 0; i <cantAlien3; i++){
            aliens.add(factory.createAlien3());
        }
        */


        M_Aliens = new ManejadorAliens(aliens, this,tablero);

        M_Naves = new ManejadorNaves();

    }


    public void empezarJuego() {
        int modo = elegirModoDeJuego();
        //Se crean las factoies correspondientes y los archivos
        if (modo == 0) {
            archivos = new String[2];
            archivos[0] = "archivoD1";
            archivos[1] = "archivoD2";

        } else {
            archivos = new String[2];
            archivos[0] = "archivoN1";
            archivos[1] = "archivoN2";
        }
        crearNivel(0);

        //Creo la factory de aliens de acuerdo al modo de juego
        switch (modo) {
            case 0 -> factory = new FactoryDia();
            case 1 -> factory = new FactoryNoche();
        }
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
