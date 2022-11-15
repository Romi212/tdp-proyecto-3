package Logica;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.*;

public class SplashScreen extends JWindow {

    protected Properties p;
    protected int tiempoVisible;
    protected int ancho = 216;
    protected int alto = 108;

    public SplashScreen(int tiempo) {
        tiempoVisible = tiempo;

        //Inicializamos el archivo de propiedades
        p = new Properties();
        InputStream input = getClass().getResourceAsStream("/resources/archivos/configSplash.properties");
        try { p.load(input); } catch (IOException e) {  throw new RuntimeException(e);  }

        //Configuracion de la ventana
        setSize(ancho, alto);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    public void showSplashInicial() {
        ImageIcon icono = new ImageIcon(getClass().getResource(p.getProperty("inicial")));
        add(new JLabel(icono));
        setLocationRelativeTo(null);

      //  try{ Thread.sleep(tiempoVisible); } catch (InterruptedException e) {}
     //   setVisible(false);
     //   dispose();
    }

    public void showSplashNivel(int nivel, String modo){ //VER
        JPanel contenido = new JPanel();
        contenido.setBackground(Color.BLACK);

        JLabel Lmodo = new JLabel("MODO "+ modo);
        Lmodo.setForeground(Color.white);
        JLabel Lnivel = new JLabel("NIVEL: "+nivel);
        Lnivel.setForeground(Color.white);

        contenido.add(Lmodo, "North");
        contenido.add(new JSeparator(), "Center");
        contenido.add(Lnivel, "South");

        add(contenido);
        setLocationRelativeTo(null);

        //  try{ Thread.sleep(tiempoVisible); } catch (InterruptedException e) {}
        //   setVisible(false);
        //   dispose();
    }

    public void showSplashHorda(){
        ImageIcon icono = new ImageIcon(getClass().getResource(p.getProperty("notificarHorda")));
        add(new JLabel(icono));
        setLocationRelativeTo(null);

        //  try{ Thread.sleep(tiempoVisible); } catch (InterruptedException e) {}
        //   setVisible(false);
        //   dispose();
    }

    public void showSplashPerdio(){
        ImageIcon icono = new ImageIcon(getClass().getResource(p.getProperty("notificarGano")));
        add(new JLabel(icono));
        setLocationRelativeTo(null);

        //  try{ Thread.sleep(tiempoVisible); } catch (InterruptedException e) {}
        //   setVisible(false);
        //   dispose();
    }

    public void showSplashGano(){
        ImageIcon icono = new ImageIcon(getClass().getResource(p.getProperty("notificarPerdio")));
        add(new JLabel(icono));
        setLocationRelativeTo(null);

        //  try{ Thread.sleep(tiempoVisible); } catch (InterruptedException e) {}
        //   setVisible(false);
        //   dispose();
    }

}
