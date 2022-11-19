package Logica;

import Logica.Entidades.Aliens.Alien;
import Logica.Entidades.ColumnaFinal;
import Logica.Entidades.Factories.FactoryDia;
import Logica.Entidades.Factories.FactoryNoche;
import Logica.Entidades.Factories.ObjectsFactory;
import Logica.Entidades.Naves.Nave;
import Logica.Entidades.ObjetoGrafico;
import Logica.Entidades.SolGrafico;
import Logica.Manejadores.ManejadorAliens;
import Logica.Manejadores.ManejadorNaves;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;
import java.awt.*;
import java.util.Random;

public class Logica {

    private final int GANE = 1;
    private final int PERDI = 2;

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

    private int modo;



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
        ventana.solesIniciales(Integer.parseInt(p.getProperty("recolectadosInicial")));
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
        ColumnaFinal fin;
        if(modo == 0)
            fin = new ColumnaFinal(new Rectangle(xIni+sizeC+15,0,sizeC,sizeC*10),this);
        else
            fin = new ColumnaFinal(new Rectangle(xIni+3*sizeC+15,0,sizeC,sizeC*10),this);
        for(int i = 0; i<cantFilas;i++){
            tablero[i].setColumna(fin);
        }


        while(cantAlien1 > 0 && cantAlien2 > 0 && cantAlien3 > 0){
            filaElegida = rand.nextInt(cantFilas);
            filaActual = tablero[filaElegida];

            posx = filaActual.getxIni() + (filaActual.cantCeldas()+2)*filaActual.getTam();
            posy = filaActual.getyIni()-10;
            Alien a = null;
            int tipo = rand.nextInt(3);
            if(tipo == 0 && cantAlien1 > 0){
                a = factory.createAlien1(posx, posy);
                cantAlien1--;
            }
            else if(tipo == 1 && cantAlien2 > 0){
                a = factory.createAlien2(posx, posy);
                cantAlien2--;
            }
            else if(tipo == 2 && cantAlien3 > 0){
                a = factory.createAlien3(posx, posy);
                cantAlien3--;
            }
            a.setFila(filaElegida);
            aliens.add(a);
            ventana.agregarObjeto(a.getAlienG());
        }


        M_Aliens = new ManejadorAliens(aliens,this, tablero);

        M_Naves = new ManejadorNaves(tablero, this);

        M_Aliens.start();

        M_Naves.start();

    }


    public void empezarJuego() {
        modo = elegirModoDeJuego();

        //Se crean las factories correspondientes y los archivos
        if (modo == 0) {
            archivos = new String[2];
            archivos[0] = "archivoD1";
            archivos[1] = "archivoD2";

        } else {
            archivos = new String[2];
            archivos[0] = "archivoD1";
            archivos[1] = "archivoD2";
        }

        //Creo la factory de aliens de acuerdo al nivel de juego
        nivel = 0;
        factory = new FactoryDia();
        crearNivel(nivel);


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

    }
    public void agregarObjetoGrafico(ObjetoGrafico o){
        ventana.agregarObjeto(o);
    }

    public void terminoNivel(){
        M_Aliens.detener();
        M_Naves.detener();
        nivel++;
        if(nivel == 2){
            ventana.finDelJuego(GANE);
        }
        else {
            ventana.pausarMusica();
            ventana.organizarVentana(nivel);
            factory = new FactoryNoche();
            crearNivel(nivel);
        }
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

    public void actualizarGrafico(ObjetoGrafico o){ventana.actualizarGrafico(o);}

    public void terminoJuego(){
        ventana.finDelJuego(PERDI);
    }

    public void detenerHilos(){
        M_Aliens.detener();
        M_Naves.detener();
    }
}
