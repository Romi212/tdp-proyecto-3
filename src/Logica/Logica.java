package Logica;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.Factories.FactoryDia;
import Logica.Entidades.Factories.FactoryNoche;
import Logica.Entidades.Factories.ObjectsFactory;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.ObjetoGrafico;
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

    private int soles;
    private int nivel;
    private ObjectsFactory factory;
    private String[] archivos;
    private Fila[] tablero;

    private int cantFilas = 6;

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

        Random rand = new Random();
        int filaElegida;
        int posx;
        int posy;
        Fila filaActual;
        Rectangle hitbox;

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
                    a = factory.createAlien1(posx, posy, hitbox, "alienAImg");
                    cantAlien1--;
                    break;
                case 1:
                    a = factory.createAlien2(posx, posy, hitbox, "alienBImg");
                    cantAlien2--;
                    break;
                case 2:
                    a = factory.createAlien3(posx, posy, hitbox, "alienCImg");
                    cantAlien3--;
                    break;
            }
            a.setFila(filaElegida);
            aliens.add(a);
            ventana.agregarObjeto(a.getAlienG());
        }


        M_Aliens = new ManejadorAliens(aliens,this, tablero);

        M_Naves = new ManejadorNaves();

        M_Aliens.start();

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

        //Creo la factory de aliens de acuerdo al modo de juego
        switch (modo) {
            case 0 -> factory = new FactoryDia();
            case 1 -> factory = new FactoryNoche();
        }

        crearNivel(0);



    }

    public void aumentarSoles(int cant){
        soles = soles+ cant;
    }

    public void agregarNave(int x, int y, int tipo){
        Rectangle rect = null;
        Nave n = null;
        switch (tipo){
            case 1:   n = factory.createNaveA(tablero[0], x, y, rect); break;
            case 2:   n = factory.createNaveB(tablero[0], x, y, rect); break;
            case 3:   n = factory.createNaveC(tablero[0], x, y, rect); break;
            case 4:   n = factory.createSatelite(tablero[0], x, y, rect); break;
        }
        tablero[0].agregarNave(n, 0);
        ventana.agregarObjeto(n.getNaveG());
    }
    public void agregarObjetoGrafico(ObjetoGrafico o){
        ventana.agregarObjeto(o);
    }

    public void terminoNivel(boolean seGano){

    }
}
