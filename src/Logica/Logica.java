package Logica;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.ColumnaFinal;
import Logica.Entidades.Factories.FactoryDia;
import Logica.Entidades.Factories.FactoryNoche;
import Logica.Entidades.Factories.ObjectsFactory;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.Naves.ObjetoColisionable;
import Logica.Entidades.ObjetoGrafico;
import Logica.Entidades.SolGrafico;
import Logica.Manejadores.ManejadorAliens;
import Logica.Manejadores.ManejadorNaves;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.awt.*;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Logica {

    private int nivel;
    private ObjectsFactory factory;
    private String[] archivos;
    private Fila[] tablero;

    private int cantFilas = 6;

    private ManejadorNaves M_Naves;
    private ManejadorAliens M_Aliens;

    private Ventana ventana;
    private Properties p;
    private int xIni;
    private int yIni;
    private int sizeC;


    public Logica(Ventana v,Properties p,int xInicial, int yInicial, int tam){
        this.p = p;
        ventana = v;
        tablero = new Fila[cantFilas];
        xIni = xInicial;
        yIni = yInicial;
        sizeC = tam;
        for(int i =0; i < cantFilas; i++){
            Fila f = new Fila(this, xIni,yIni+(i*sizeC),sizeC);
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

        Random rand = new Random();
        int filaElegida;
        int posx;
        int posy;
        Fila filaActual;
        Rectangle hitbox;
        ColumnaFinal fin = new ColumnaFinal(new Rectangle(xIni-sizeC,yIni,sizeC,sizeC*9));

        while(cantAlien1 > 0 && cantAlien2 > 0 && cantAlien3 > 0){
            filaElegida = rand.nextInt(cantFilas);
            filaActual = tablero[filaElegida];
            posx = filaActual.getxIni() + (filaActual.cantCeldas()+2)*filaActual.getTam();
            posy = filaActual.getyIni();
            hitbox = new Rectangle();
            Alien a = null;
            int tipo = rand.nextInt(3);
            switch(tipo){
                case 0:
                    a = factory.createAlien1(posx, posy);
                    cantAlien1--;
                    break;
                case 1:
                    a = factory.createAlien2(posx, posy);
                    cantAlien2--;
                    break;
                case 2:
                    a = factory.createAlien3(posx, posy);
                    cantAlien3--;
                    break;
            }
            a.setFila(filaElegida);
            aliens.add(a);
            ventana.agregarObjeto(a.getAlienG());
        }


        M_Aliens = new ManejadorAliens(aliens,this, tablero,fin);

        M_Naves = new ManejadorNaves(tablero);

        M_Aliens.start();

        M_Naves.start();

    }


    public void empezarJuego() {
        int modo = elegirModoDeJuego();
        //Se crean las factories correspondientes y los archivos
        if (modo == 0) {
            archivos = new String[2];
            archivos[0] = "archivoD1";
            archivos[1] = "archivoD2";

        } else {
            archivos = new String[2];
            archivos[0] = "archivoN1";
            archivos[1] = "archivoN2";
        }

        //Creo la factory de aliens de acuerdo al modo de juego
        switch (modo) {
            case 0 -> factory = new FactoryDia();
            case 1 -> factory = new FactoryNoche();
        }

        crearNivel(0);

    }

    public void agregarNave(int x, int y,int fila, int columna, int tipo){

        Nave n = null;
        switch (tipo){
            case 1:   n = factory.createNaveA(tablero[fila], columna, x, y); break;
            case 2:   n = factory.createNaveB(tablero[fila], columna, x, y); break;
            case 3:   n = factory.createNaveC(tablero[fila], columna, x, y); break;
            case 4:   n = factory.createSatelite(tablero[fila], columna, x, y); break;
        }
        tablero[fila].agregarNave(n);
        ventana.agregarObjeto(n.getNaveG());
        System.out.println(tablero[fila].estaOcupada(columna));
    }
    public void agregarObjetoGrafico(ObjetoGrafico o){
        ventana.agregarObjeto(o);
    }

    public void terminoNivel(boolean seGano){

    }

    public boolean isCeldaOcupada(int x, int y){
        return tablero[x].estaOcupada(y);
    }

    public void sacarObjeto(ObjetoGrafico o){
        ventana.sacarObjeto(o);
    }

    public void mostrarCartelHorda(){
        ventana.cartelHorda();
    }

    public void agregarSol(SolGrafico s){
        ventana.agregarSol(s);
    }
}
