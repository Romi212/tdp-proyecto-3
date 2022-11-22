import Logica.SplashScreen;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

    //Antes estaba lo mismo pero con EventQueue, supuestamente resuelve el error raro (concurrencia)
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                  SplashScreen splash = new SplashScreen(3000);
            }
        });
    }

}