import javax.swing.*;
import java.awt.*;

public class SimpleTray {
  public static void main(String args[]) {
    Runnable runner = new Runnable() {
      public void run() {
        if (SystemTray.isSupported()) {
          SystemTray tray = SystemTray.getSystemTray();
          Image image = Toolkit.getDefaultToolkit().getImage("jpgIcon.jpg");
          PopupMenu popup = new PopupMenu();
          MenuItem item = new MenuItem("Hello, World");
          popup.add(item);
          TrayIcon trayIcon = new TrayIcon(image, "Tip Text", popup);
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
