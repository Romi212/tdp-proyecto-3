import Logica.SplashScreen;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                  SplashScreen splash = new SplashScreen(3000);
            }
        });
    }

}