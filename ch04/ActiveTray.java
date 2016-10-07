import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class ActiveTray {
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
      public void run() {
        if (SystemTray.isSupported()) {
          final SystemTray tray = SystemTray.getSystemTray();
          PropertyChangeListener propListener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
              TrayIcon oldTray[] = (TrayIcon[])evt.getOldValue();
              TrayIcon newTray[] = (TrayIcon[])evt.getNewValue();
              System.out.println(oldTray.length + " / " + newTray.length);
            }
          };
          tray.addPropertyChangeListener("trayIcons", propListener);
          Image image = Toolkit.getDefaultToolkit().getImage("jpgIcon.jpg");
          PopupMenu popup = new PopupMenu();
          MenuItem item = new MenuItem("Hello, World");
          final TrayIcon trayIcon = new TrayIcon(image, "Tip Text", popup);
          ActionListener menuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              trayIcon.displayMessage("Good-bye", "Cruel World",
                TrayIcon.MessageType.NONE);
            }
          };
          item.addActionListener(menuActionListener);
          popup.add(item);
          ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              tray.remove(trayIcon);
            }
          };
          trayIcon.addActionListener(actionListener);
          try {
            tray.add(trayIcon);
          } catch (AWTException e) {
            System.err.println("Unable to add to system tray: " + e);
          }
        } else {
          System.err.println("No system tray available");
        }
      }
    };
    EventQueue.invokeLater(runner);
  }
}
