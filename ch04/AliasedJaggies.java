import javax.swing.*;
import java.awt.*;

public class AliasedJaggies {
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Jaggies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JComponent comp = new JComponent() {
          public void paintComponent(Graphics g) {
            g.fillRect(70, 20, 50, 50);
            g.fillRect(120, 70, 50, 50);
            g.fillRect(170, 120, 50, 50);
            g.fillRect(220, 170, 50, 50);
	    g.setColor(new Color(191, 128, 64));
            g.fillRect(20, 20, 50, 50);
            g.fillRect(70, 70, 50, 50);
            g.fillRect(120, 120, 50, 50);
            g.fillRect(170, 170, 50, 50);
	    g.setColor(new Color(64, 128, 191));
            g.fillRect(120, 20, 50, 50);
            g.fillRect(170, 70, 50, 50);
            g.fillRect(220, 120, 50, 50);
            g.fillRect(270, 170, 50, 50);
	  }
	};
        frame.add(comp, BorderLayout.CENTER);
        frame.setSize(350, 300);
        frame.setVisible(true);
      }
    };
    EventQueue.invokeLater(runner);
  }
}
