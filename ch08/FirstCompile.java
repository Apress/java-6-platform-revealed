import java.io.*;
import javax.tools.*;

public class FirstCompile {
  public static void main(String args[]) throws IOException {
    JavaCompilerTool compiler = ToolProvider.getSystemJavaCompilerTool();
    int results = compiler.run(null, null, null, "Foo.java");
    System.out.println("Success: " + (results == 0));
  }
}
