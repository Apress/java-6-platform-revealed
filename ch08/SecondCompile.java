import java.io.*;
import java.util.*;
import javax.tools.*;

public class SecondCompile {
  public static void main(String args[]) throws IOException {
    JavaCompilerTool compiler = ToolProvider.getSystemJavaCompilerTool();
    DiagnosticCollector<JavaFileObject> diagnostics = 
        new DiagnosticCollector<JavaFileObject>();
    StandardJavaFileManager fileManager = 
        compiler.getStandardFileManager(diagnostics);
    Iterable<? extends JavaFileObject> compilationUnits =
        fileManager.getJavaFileObjectsFromStrings(Arrays.asList("Bar.java"));
    Iterable<String> options = Arrays.asList(
        "-d", "classes", "-sourcepath", "src");
    JavaCompilerTool.CompilationTask task = compiler.getTask(
        null, fileManager, diagnostics, options, null, compilationUnits);
    task.run();
    boolean success = task.getResult();
    for (Diagnostic diagnostic : diagnostics.getDiagnostics())
        System.console().printf(
            "Code: %s%n" +
            "Kind: %s%n" +
            "Position: %s%n" +
            "Start Position: %s%n" +
            "End Position: %s%n" +
            "Source: %s%n" +
            "Message:  %s%n",
            diagnostic.getCode(), diagnostic.getKind(),
            diagnostic.getPosition(), diagnostic.getStartPosition(),
            diagnostic.getEndPosition(), diagnostic.getSource(),
            diagnostic.getMessage(null));
    fileManager.close();
    System.out.println("Success: " + success);
  }
}
