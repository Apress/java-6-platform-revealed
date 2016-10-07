import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

public class TextPrint {
  public static void main(final String args[]) {
    Runnable runner = new Runnable() {
      public void run() {
        JFrame frame = new JFrame("Text Print");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextArea textArea = new JTextArea();
        JScrollPane pane = new JScrollPane(textArea);
        frame.add(pane, BorderLayout.CENTER);
        textArea.paste();

        JButton button = new JButton("Print");
        frame.add(button, BorderLayout.SOUTH);
        ActionListener listener = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            try {
              textArea.print();
            } catch (PrinterException pe) {
              System.err.println("Unable to print...");
            }
          }
        };
        button.addActionListener(listener);

        frame.setSize(250, 150);
        frame.setVisible(true);
      }
    };
    EventQueue.invokeLater(runner);
  }
}
