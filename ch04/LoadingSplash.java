import javax.swing.*;
import java.awt.*;

public class LoadingSplash {
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
      public void run() {
        SplashScreen splash = SplashScreen.getSplashScreen();
        Graphics2D g = splash.createGraphics();
        if (splash != null) {
          for (int i=0; i < 100; i++) {
            g.setColor(Color.WHITE);
            g.fillRect(50, 50, i, 25);
            if (splash.isVisible()) {
              splash.update();
            } else {
              break;
            }
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
            }
          }
        }
        JFrame frame = new JFrame("Java 6 Revealed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel(
             " Java 6 Revealed", JLabel.CENTER);
        frame.add(label, BorderLayout.CENTER);
        frame.setSize(300, 95);
        frame.setVisible(true);
     }
    };
    EventQueue.invokeLater(runner);
  }
}
