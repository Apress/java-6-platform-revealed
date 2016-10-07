import java.io.*;
import java.net.*;

public class FileURL {
  public static void main(String args[]) throws MalformedURLException {
    Console console = System.console();
    File file = new File("The End");
    URL url1 = file.toURL();
    URL url2 = file.toURI().toURL();
    console.printf("Bad url  %s%n", url1);
    console.printf("Good url %s%n", url2);
  }
}
