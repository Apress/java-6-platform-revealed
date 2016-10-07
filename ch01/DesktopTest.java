import java.awt.*;
import java.io.*;

public class DesktopTest {
  public static void main(String args[]) {
    if (!Desktop.isDesktopSupported()) {
      System.err.println("Desktop not supported!");
      System.exit(-1);
    }
    Desktop desktop = Desktop.getDesktop();
    String path;
    if (args.length == 0) {
      path = ".";
    } else {
      path = args[0];
    }
    File dir = new File(path);
    File files[] = dir.listFiles();
    for (File file: files) {
      System.out.println("Open " + file.getName() + "? [YES/NO] :");
      if (desktop.isSupported(Desktop.Action.OPEN)) {
        String line = System.console().readLine();
        if ("YES".equals(line)) {
          System.out.println("Opening... " + file.getName());
          try {
            desktop.open(file);
          } catch (IOException ioe) {
            System.err.println("Unable to open: " + file.getName());
          }
        }
      }
    }
  }
}
