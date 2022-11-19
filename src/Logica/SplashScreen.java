package Logica;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.*;

public class SplashScreen extends JFrame implements Runnable{

    protected Properties p;
    protected int tiempoVisible;
    protected int ancho = 288;
    protected int alto = 144;
    protected Font fuente;
    protected Thread hilo;
    protected Ventana window;

    public SplashScreen(int tiempo) {
        tiempoVisible = tiempo;
        setLocationRelativeTo(null);
        setUndecorated(true); //Deshabilita la barra superior
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        //Inicializamos el archivo de propiedades
        p = new Properties();
        InputStream input = getClass().getResourceAsStream("/resources/archivos/configSplash.properties");
        try { p.load(input); } catch (IOException e) {  throw new RuntimeException(e);  }

        //Cargamos la fuente para el tecto
        try {
            String path = p.getProperty("fuente");
            fuente = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuente);
        } catch (IOException|FontFormatException e) {
            e.printStackTrace();
        }

        //Inicializamos el hilo
        hilo = new Thread(this);
        hilo.start();
    }


    @Override
    public void run() {
        agregarFondo("inicial");
        setVisible(true);
        esperarYcerrar();

        window = new Ventana();
        window.initialize();
    }

    /* La splashScreen se muestra los milisegundos que indique la variable tiempoVisible luego oculta el JFrame y libera los recursos asociados al mismo */
    private void esperarYcerrar(){
        try{ hilo.sleep(tiempoVisible); } catch (InterruptedException e) {}
        setVisible(false);;
        dispose();
    }

    /* Escala la imagen del URL pasado por parametro segun los atributos de clase alto y ancho, la coloca como fondo de la splashScreen */
    private void agregarFondo(String clave){
        ImageIcon icono = new ImageIcon(getClass().getResource(p.getProperty(clave)));
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon iconoFondo = new ImageIcon(img);

        JLabel Lfondo = new JLabel(iconoFondo);
        Lfondo.setSize(ancho, alto);
        add(Lfondo);
    }

}
