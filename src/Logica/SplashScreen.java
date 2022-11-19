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
    protected int operacion;

    public SplashScreen(int tiempo) {
        tiempoVisible = tiempo;
        setUndecorated(true); //Deshabilita la barra superior
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);


        //Inicializamos el archivo de propiedades
        p = new Properties();
        InputStream input = getClass().getResourceAsStream("/resources/archivos/configNotificaciones.properties");
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

    /* Muestra la splashScreen e inicializa la ventana principal del juego */
    @Override
    public void run() {
        agregarFondo("inicial");
        setVisible(true);
        esperarYcerrar();

        Ventana window = new Ventana();
        window.initialize();

    }

    private void esperarYcerrar(){
        try{ hilo.sleep(tiempoVisible); } catch (InterruptedException e) {}
        setVisible(false);
        dispose(); //Libera todos los recursos que utiliza la splashScreen
    }

    /* Escala la imagen del URL pasado por parametro segun los atributos de clase alto y ancho, retorna un icono con la imagen */
    private void agregarFondo(String clave){
        ImageIcon icono = new ImageIcon(getClass().getResource(p.getProperty(clave)));
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon iconoFondo = new ImageIcon(img);

        JLabel Lfondo = new JLabel(iconoFondo);
        int x = (int) this.getBounds().getX();
        int y = (int) this.getBounds().getY();
        Lfondo.setBounds(x, y ,ancho, alto);
        add(Lfondo);
    }

}
