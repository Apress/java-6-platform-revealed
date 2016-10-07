import java.io.*;

public class Space {
  public static void main(String args[]) {
    Console console = System.console();
    File roots[] = File.listRoots();
    for (File root: roots) {
      console.printf("%s has %,d of %,d free%n", root.getPath(),
          root.getUsableSpace(), root.getTotalSpace());
    }
  }
}
