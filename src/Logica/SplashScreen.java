package Logica;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
        setLocationRelativeTo(null);
        setUndecorated(true); //Deshabilita la barra superior
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        this.setLayout(new FlowLayout());

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


    /*    Invoca a las operaciones segun la variable operacion
     *           0 Splash inicial
     *           1 Splash de trancision entre niveles
     *           2 Splash que notifica que se acerca una horda
     *           3 Splash de perder el juego
     *           4 Splash de ganar el juego  */
    @Override
    public void run() {

        switch(operacion){
            case 0:
                agregarFondo("inicial");
                setVisible(true);
                esperarYcerrar();

                Ventana window = new Ventana();
                window.initialize();
                break;
            case 1:
                showSplashNivel(p.getProperty("nivel"), p.getProperty("modo"));
                esperarYcerrar();
            break;
            case 2:
                agregarFondo("notificarHorda");
                setVisible(true);
                esperarYcerrar();
                break;
            case 3:
                agregarFondo("notificarPerdio");
                setVisible(true);
                esperarYcerrar();
                break;
            case 4:
                agregarFondo("notificarGano");
                setVisible(true);
                esperarYcerrar();
                break;
        }

    }

    /* Inicializa la variable utilizada en el metodo run para decidir que pantalla se debe mostrar */
    public void setOperacion(int operacion){ this.operacion = operacion; }
    private void esperarYcerrar(){
        try{ hilo.sleep(tiempoVisible); } catch (InterruptedException e) {}
        setVisible(false);
      //  hilo.stop();
        removeAll();             //Elimina todos los componentes para la siguiente llamada
    }

    /* Escala la imagen del URL pasado por parametro segun los atributos de clase alto y ancho, retorna un icono con la imagen */
    private void agregarFondo(String clave){
        ImageIcon icono = new ImageIcon(getClass().getResource(p.getProperty(clave)));
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon iconoFondo = new ImageIcon(img);

        JLabel Lfondo = new JLabel(iconoFondo);
        Lfondo.setSize(ancho, alto);
        //Lfondo.setBounds(0,0,ancho,alto);
        add(Lfondo);
    }

    private void showSplashNivel(String nivel, String modo){
        agregarFondo("notificarNivel");

        //Creamos las etiquetas con el texto y las agregamos a la splash

        JLabel Lmodo = new JLabel("MODO "+ modo);
        int x = (int) Math.round(this.getBounds().getX());
        int y = (int) Math.round(this.getBounds().getY());
        Lmodo.setBounds( x + 10, y + 10, ancho, alto/3);
        Lmodo.setFont(fuente.deriveFont(15f));
        Lmodo.setForeground(Color.white);

        JLabel Lnivel = new JLabel("NIVEL: "+ nivel);
        Lnivel.setBounds( x+alto/3, y+10, ancho, alto/3);
        Lnivel.setFont(fuente.deriveFont(15f));
        Lnivel.setForeground(Color.white);

        getContentPane().setLayout(new BorderLayout());
        add(Lmodo, "North");
        add(Lnivel, "South");

    }

}
