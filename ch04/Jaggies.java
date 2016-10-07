import javax.swing.*;
import java.awt.*;

public class Jaggies {
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Jaggies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JComponent comp = new JComponent() {
          public void paintComponent(Graphics g) {
            g.fillRect(20, 20, 50, 50);
            g.fillRect(70, 70, 50, 50);
            g.fillRect(120, 120, 50, 50);
            g.fillRect(170, 170, 50, 50);
	  }
	};
        frame.add(comp, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setVisible(true);
      }
    };
    EventQueue.invokeLater(runner);
  }
}
