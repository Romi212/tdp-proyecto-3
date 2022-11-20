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

    private int nivel;
    private String[] archivos;
    private final int GANE = 1;
    private final int PERDI = 2;

    private ObjectsFactory factory;
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

    /* Delega la operacion a ventana, que se lo solicita al jugador y retorna el modo elegido */
    private int elegirModoDeJuego(){
        return ventana.elegirModoDeJuego();
    }

    /* Crea el nivel leyendo del archivo correspondiente la cantidad de cada uno de los tipos de aliens. Inicia los manejaadores */
    private void crearNivel(int nivel){
        ventana.solesIniciales(Integer.parseInt(p.getProperty("recolectadosInicial")));

        //Leemos el archivo del nivel linea por linea
        InputStream input = getClass().getResourceAsStream(p.getProperty(archivos[nivel]));
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(input));
        List lineas = new List();
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
        //Recuperamos las tres primeras lineas como la cantidad de aliens de cada tipo que deben aparecer a lo largo del nivel
        int cantAlien1 = Integer.parseInt(lineas.getItem(0));
        int cantAlien2 = Integer.parseInt(lineas.getItem(1));
        int cantAlien3 = Integer.parseInt(lineas.getItem(2));

        //Se inicializa la columna final segun el modo,
        //si es el normal esta se situa en el extremo izquierdo del tablero, si es el experto se situa dentro del tablero impidiendo al jugador colocar naves en algunas columnas
        ColumnaFinal fin;
        if(modo == 0)
            fin = new ColumnaFinal(new Rectangle(xIni+sizeC+15,0,sizeC,sizeC*10),this);
        else
            fin = new ColumnaFinal(new Rectangle(xIni+3*sizeC+15,0,sizeC,sizeC*10),this);
        for(int i = 0; i<cantFilas;i++){
            tablero[i].setColumna(fin);
        }

        //Se distribuyen los aliens por cada una de las filas aleatoriamente
        Random rand = new Random();
        int filaElegida;
        int posx;
        int posy;
        Fila filaActual;
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

        //Se inicializan y comienzan los manejadores
        M_Aliens = new ManejadorAliens(aliens,this, tablero);
        M_Naves = new ManejadorNaves(tablero, this);
        M_Aliens.start();
        M_Naves.start();

    }

    /* Se inicializa las factories de acuerdo alnivel*/
    public void empezarJuego() {
        modo = elegirModoDeJuego();

        //Se crean las factories correspondientes y los archivos
        if (modo == 0) {
            archivos = new String[2];
            archivos[0] = "archivoN1";
            archivos[1] = "archivoN2";

        } else {
            archivos = new String[2];
            archivos[0] = "archivoE1";
            archivos[1] = "archivoE2";
        }

        //Creo la factory de aliens de acuerdo al nivel de juego
        nivel = 0;
        factory = new FactoryDia();
        crearNivel(nivel);


    }

    /* Crea una nave del tipo especificado en el parametro, lo agrega a su fila correspondiente asi como tambien en la pantalla */
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

    /* Delega la operacion a ventana, que agrega el objeto grafico a la pantalla */
    public void agregarObjetoGrafico(ObjetoGrafico o){
        ventana.agregarObjeto(o);
    }

    /* Detiene los manejadores, si se completo el ultimo nivel se lo informa a ventana. En caso contrario le informa a ventana que actualice la pantalla y pause la musica,
    * cambia la factory (para modificar los tipos de naves y aliens que aparecen en el nivel) y crea el nuevo nivel */
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

    /* Delega la operacion a ventana, que elimina el objeto grafico de la pantalla */
    public void sacarObjeto(ObjetoGrafico o){
        ventana.sacarObjeto(o);
    }

    /* Delega la operacion a ventana, que le informa al jugador que se aproxima una horda */
    public void mostrarCartelHorda(){
        ventana.cartelHorda();
    }

    /* Delega la operacion a ventana que agrega soles, independientes de las naves que los generan, para que los recolecte el jugador */
    public void agregarSol(SolGrafico s){
        ventana.agregarSol(s);
    }

    /* Delega la operacion a ventana, que actualiza el estado en pantalla del objeto grafico */
    public void actualizarGrafico(ObjetoGrafico o){ventana.actualizarGrafico(o);}

    /* Delega la operacion a ventana, que informa al jugador que perdio */
    public void terminoJuego(){
        ventana.finDelJuego(PERDI);
    }

    /* Detiene los manejadores */
    public void detenerHilos(){
        M_Aliens.detener();
        M_Naves.detener();
    }
}
