import Logica.SplashScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                  SplashScreen splash = new SplashScreen(3000);
            }
        });
    }

}