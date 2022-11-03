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
    protected Thread hiloPlay, hiloRestart;

    //Hilo para iniciar
    protected Runnable iniciar = new Runnable() {
        @Override
        public void run() {
            try {
                reproductor = new Player(bufferedInputStream);
                duracion = bufferedInputStream.available();
                reproductor.play();
                System.out.println("Se supone que hace play");
            } catch (java.io.IOException io) {
                System.out.print("Ocurrio un error del tipo I/O o se cerro el stream de la musica");
                io.printStackTrace();
            }
            catch(JavaLayerException jle){
                System.out.print("Error en el reproductor de musica ");
                jle.printStackTrace();
            }
        }
    };

    //Hilo para continuar reproduciendo luego de que se pauso
    protected Runnable pausar = new Runnable() {
        @Override
        public void run() {
            try {
                reproductor = new Player(bufferedInputStream);
                bufferedInputStream.skip(duracion - pausa);
                reproductor.play();
            } catch (java.io.IOException io) {
                System.out.print("Ocurrio un error del tipo I/O o se cerro el stream de la musica");
                io.printStackTrace();
            } catch(JavaLayerException jle){
                System.out.print("Error en el reproductor de musica ");
                jle.printStackTrace();
            }
        }
    };

    public Musica(){
        archivo = "src/resources/musica.mp3";

        try{
            FileInputStream stream = new FileInputStream(archivo);
            bufferedInputStream = new BufferedInputStream(stream);
        }catch(java.io.FileNotFoundException f){
            System.out.print("No se encuentra el archivo mp3 de la musica ");
            f.printStackTrace();
        }

        hiloPlay = new Thread(iniciar);
        hiloRestart = new Thread(pausar);
    }

    public void play(){ hiloPlay.start(); }

    //Guarda la longitud restante para terminar la cancion y cierra el reproductor
    public void pausar(){
        try{
            pausa = bufferedInputStream.available();
            reproductor.close();
        } catch (java.io.IOException io) {
            System.out.print("Ocurrio un error del tipo I/O o se cerro el stream de la musica");
            io.printStackTrace();
        }

    }

    public void restart(){ hiloRestart.start(); }
}
