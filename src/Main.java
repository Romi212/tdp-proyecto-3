import Logica.SplashScreen;
import Logica.Ventana;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                  SplashScreen splash = new SplashScreen(3000);
                  splash.setOperacion(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}