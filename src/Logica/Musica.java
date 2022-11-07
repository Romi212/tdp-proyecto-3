package Logica;
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class Musica {
    protected String archivo;
    protected Player reproductor;
    protected long duracion, pausa;
    protected BufferedInputStream stream;
    protected Thread hiloPlay;
    protected boolean reproduciendo;
    //Hilo para iniciar
    protected Runnable iniciar = new Runnable() {
        @Override
        public void run() {
            try {
                while(reproduciendo){
                    FileInputStream fileStream = new FileInputStream(archivo);
                    stream = new BufferedInputStream(fileStream);
                    duracion = stream.available(); //Guarda la duracion total en caso de que se reinicie la musica

                    reproductor = new Player(stream);
                    reproductor.play();
                }

                pausa = stream.available(); //Guarda lo que resta por leer en caso de que se reinicie la musica
                reproductor.close();

            } catch(JavaLayerException jle){
                System.out.print("Error en el reproductor de musica ");
                jle.printStackTrace();
            } catch (java.io.IOException io) {
                System.out.print("Ocurrio un error del tipo I/O o se cerro el stream de la musica");
                io.printStackTrace();
            }
        }
    };


    public Musica(String path){
        this.archivo = path;
        System.out.println(path);
        reproduciendo = true;
    }

    //inicia el hilo
    public void play(){
        hiloPlay = new Thread(iniciar);
        hiloPlay.start();
    }

    //Guarda la longitud restante para terminar la cancion, cierra el reproductor y detiene la ejecucion de los hilos
    public void pausar(){
        reproduciendo = false;
        hiloPlay.stop();
    }

    public void restart(){
        try{
            stream.skip(duracion - pausa);
        } catch (java.io.IOException io) {
            System.out.print("Ocurrio un error del tipo I/O o se cerro el stream de la musica");
            io.printStackTrace();
        }
        hiloPlay.start();
    }

    public boolean estaReproduciendo(){ return reproduciendo; }
}
