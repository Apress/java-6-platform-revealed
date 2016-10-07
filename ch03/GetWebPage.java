import java.io.*;
import java.net.*;

public class GetWebPage {
  private static final int PORT = 80;
  public static void main(String args[])
      throws IOException, UnknownHostException {
    Console console = System.console();
    int slashPos;
    if (args.length != 1) {
      System.err.println("Usage: java GetWebPage <URL>");
      System.exit(-1);
    }
    if (!args[0].startsWith("http://")) {
      System.err.println("Please specify a legal http URL");
      System.exit(-2);
    }
    String resource = args[0].substring(7);   // skip http://
    slashPos = resource.indexOf('/');  // find host/file separator
    if (slashPos < 0) {
      resource = resource + "/";
      slashPos = resource.indexOf('/');
    }
    String host = resource.substring(0, slashPos);
    String file = resource.substring(slashPos); // isolate host and file parts
    System.out.println("Host to contact: '" + host +"'");
    System.out.println("File to fetch  : '" + file +"'");
    InetAddress addr = InetAddress.getByName(host);
    if (addr != null) {
      Socket socket = new Socket(addr, PORT);
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
      console.printf("GET %s HTTP/1.0\n", file);
           pw.printf("GET %s HTTP/1.0\n\n", file);
      String line;
      while ((line = br.readLine()) != null) {  // read until EOF
        console.printf("%s%n", line);
      }
    }
    System.out.println("\nDone.");
  }
}
