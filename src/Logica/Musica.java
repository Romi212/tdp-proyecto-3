package Logica;
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class Musica {
    protected String archivo;
    protected Player reproductor;
    protected long duracion, pausa;
    protected BufferedInputStream bufferedInputStream;
    protected Thread hiloPlay;
    protected boolean reproduciendo;
    //Hilo para iniciar
    protected Runnable iniciar = new Runnable() {
        @Override
        public void run() {
            try {
                while(reproduciendo){
                    FileInputStream stream = new FileInputStream(archivo);
                    bufferedInputStream = new BufferedInputStream(stream);
                    duracion = bufferedInputStream.available(); //Guarda la duracion total en caso de que se reinicie la musica

                    reproductor = new Player(bufferedInputStream);
                    reproductor.play();
                }
                pausa = bufferedInputStream.available(); //Guarda lo que resta por leer en caso de que se reinicie la musica

            } catch(JavaLayerException jle){
                System.out.print("Error en el reproductor de musica ");
                jle.printStackTrace();
            } catch (java.io.IOException io) {
                System.out.print("Ocurrio un error del tipo I/O o se cerro el stream de la musica");
                io.printStackTrace();
            }
            reproductor.close();
            hiloPlay.stop();
        }
    };

    public Musica(){
        archivo = "src/resources/musica.mp3";
        reproduciendo = true;
        hiloPlay = new Thread(iniciar);
    }

    public void play(){ hiloPlay.start(); }

    //Guarda la longitud restante para terminar la cancion, cierra el reproductor y detiene la ejecucion de los hilos
    public void pausar(){ reproduciendo = false; }

    public void restart(){
        try{
            bufferedInputStream.skip(duracion - pausa);
        } catch (java.io.IOException io) {
            System.out.print("Ocurrio un error del tipo I/O o se cerro el stream de la musica");
            io.printStackTrace();
        }
        hiloPlay.start();
    }

    public boolean estaReproduciendo(){ return reproduciendo; }
}
