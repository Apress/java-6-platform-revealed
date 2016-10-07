import javax.activation.*;
import java.io.*;

public class FileTypes {
  public static void main(String args[]) {
    FileTypeMap map = FileTypeMap.getDefaultFileTypeMap();
    String path;
    if (args.length == 0) {
      path = ".";
    } else {
      path = args[0];
    }
    File dir = new File(path);
    File files[] = dir.listFiles();
    for (File file: files) {
      System.out.println(file.getName() + ": " +
        map.getContentType(file));
    }
  }
}
