package Logica;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica {
    protected String archivo;
    protected boolean reproduciendo;
    protected long posicionPausa;
    protected Clip clip;
    public Musica(String path){ System.out.println(path); this.archivo = path; }

    //inicia el hilo
    public void play(){
        reproduciendo = true;
        File file = new File(archivo);
        if(file.exists()){
            try{
                AudioInputStream audio = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                clip.loop(clip.LOOP_CONTINUOUSLY); //Reproduce la cancion en bucle
            } catch (UnsupportedAudioFileException noSoportado) {
                System.out.println("El formato de audio no es soportado");
                noSoportado.printStackTrace();
            } catch (IOException io) {
                System.out.println("Error de entrada/salida reproduciendo la musica");
                io.printStackTrace();
            } catch (LineUnavailableException noDisponible) {
                System.out.println("La musica no esta disponible debido a restricciones del recurso");
                noDisponible.printStackTrace();
            }
        }
        else{
            System.out.println("No se encuentra el archivo de musica");
        }
    }

    public void pausar(){
        reproduciendo = false;
        posicionPausa = clip.getMicrosecondPosition(); //Se guarda la posicion en la pista en caso de que se reinicie
        clip.stop();
    }

    public void restart(){
        reproduciendo = true;
        clip.setMicrosecondPosition(posicionPausa);
        clip.start();
    }

    public boolean estaReproduciendo(){ return reproduciendo; }
}
