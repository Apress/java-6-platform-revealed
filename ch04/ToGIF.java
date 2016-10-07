import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.util.*;

public class ToGif {
  public static void main(String args[]) throws IOException {
    System.out.println("Supported Writer Formats:");
    System.out.println(Arrays.toString(ImageIO.getWriterFormatNames()));
    if (args.length == 0) {
      System.err.println("Missing input filename");
      System.exit(-1);
    }
    String name = args[0];
    File inputFile = new File(name);
    BufferedImage input = ImageIO.read(inputFile);
    File outputFile = new File(name+".gif");
    ImageIO.write(input, "GIF", outputFile);
  }
}
