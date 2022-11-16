package Logica;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import javax.swing.*;

public class SplashScreen extends Thread{

    protected Properties p;
    protected int tiempoVisible;
    protected int ancho = 288;
    protected int alto = 144;
    protected JFrame splash;
    protected Font fuente;

    public SplashScreen(int tiempo) {
        tiempoVisible = tiempo;

        //Inicializamos el JFrame
        splash = new JFrame();
        splash.setUndecorated(true); //Deshabilita la barra superior
        splash.setSize(ancho, alto);
        splash.setLocationRelativeTo(null);
        splash.setAlwaysOnTop(true);
        splash.setVisible(true);

        //Inicializamos el archivo de propiedades
        p = new Properties();
        InputStream input = getClass().getResourceAsStream("/resources/archivos/configSplash.properties");
        try { p.load(input); } catch (IOException e) {  throw new RuntimeException(e);  }

        //Cargamos la fuente para el tecto
        try {
            String path = getClass().getResource(p.getProperty("fuente")).toString();
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuente);
        } catch (IOException|FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void esperarYcerrar(){
        try{ this.sleep(tiempoVisible); } catch (InterruptedException e) {}
        splash.setVisible(false);
        splash.removeAll();             //Elimina todos los componentes para la siguiente llamada
    }

    /* Escala la imagen del URL pasado por parametro segun los atributos de clase alto y ancho, retorna un icono con la imagen */
    private void agregarFondo(URL path){
        ImageIcon icono = new ImageIcon(path);
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon iconoFondo = new ImageIcon(img);

        JLabel Lfondo = new JLabel(iconoFondo);
        Lfondo.setSize(ancho, alto);
        splash.add(Lfondo);
    }
    public void showSplashInicial() {
        agregarFondo(getClass().getResource(p.getProperty("inicial")));
      //  esperarYcerrar();
    }

    public void showSplashNivel(int nivel, String modo){
        agregarFondo(getClass().getResource(p.getProperty("notificarNivel")));

        //Creamos las etiquetas con el texto y las agregamos a la splash
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-ancho)/2;
        int y = (screen.height-alto)/2;

        //System.out.println(splash.getBounds());
        JLabel Lmodo = new JLabel("MODO "+ modo);
        Lmodo.setBounds( x+alto/3, y+10, ancho, alto/3);
        Lmodo.setFont(fuente);
        //System.out.println(Lmodo.getBounds());
        Lmodo.setForeground(Color.white);
        Lmodo.setVisible(true);

        JLabel Lnivel = new JLabel("NIVEL: "+ nivel);
        Lnivel.setBounds( x+alto/3, y+10, ancho, alto/3);
        Lnivel.setFont(fuente);
        Lnivel.setForeground(Color.white);

        splash.getContentPane().setLayout(new BorderLayout());
        splash.add(Lmodo, "North");
        splash.add(Lnivel, "South");

      //  esperarYcerrar();
    }

    public void showSplashHorda(){ //VER PORQUE NO CARGA ESTA
        agregarFondo(getClass().getResource(p.getProperty("notificarHorda")));
        //esperarYcerrar();
    }

    public void showSplashPerdio(){
        agregarFondo(getClass().getResource(p.getProperty("notificarPerdio")));
       //esperarYcerrar()
    }

    public void showSplashGano(){
        agregarFondo(getClass().getResource(p.getProperty("notificarGano")));
        //esperarYcerrar()
    }

}
