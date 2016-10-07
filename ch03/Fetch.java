import java.io.*;
import java.net.*;
import java.util.*;

public class Fetch {
  public static void main(String args[]) throws Exception {
    Console console = System.console();
    if (args.length == 0) {
      System.err.println("URL missing");
      System.exit(-1);
    }
    String urlString = args[0];
    CookieManager manager = new CookieManager();
    CookieHandler.setDefault(manager);
    URL url = new URL(urlString);
    URLConnection connection = url.openConnection();
    Object obj = connection.getContent();
    url = new URL(urlString);
    connection = url.openConnection();
    obj = connection.getContent();
    CookieStore cookieJar = manager.getCookieStore();
    List<HttpCookie> cookies = cookieJar.getCookies();
    for (HttpCookie cookie: cookies) {
      console.printf("Cookie: %s%n", cookie);
    }
  }
}
