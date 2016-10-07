import java.io.*;
import java.net.*;
import java.util.*;

public class Fetch5 {
  public static void main(String args[]) throws Exception {
    if (args.length == 0) {
      System.err.println("URL missing");
      System.exit(-1);
    }
    String urlString = args[0];
    CookieHandler.setDefault(new ListCookieHandler());
    URL url = new URL(urlString);
    URLConnection connection = url.openConnection();
    Object obj = connection.getContent();
    url = new URL(urlString);
    connection = url.openConnection();
    obj = connection.getContent();
  }
}
