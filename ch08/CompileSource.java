import java.lang.reflect.*;
import java.io.*;
import javax.tools.*;
import javax.tools.JavaCompilerTool.CompilationTask;
import java.util.*;

public class CompileSource {
  public static void main(String args[]) throws IOException {
    JavaCompilerTool compiler = ToolProvider.getSystemJavaCompilerTool();
    DiagnosticCollector<JavaFileObject> diagnostics = 
        new DiagnosticCollector<JavaFileObject>();
    StringWriter writer = new StringWriter();
    PrintWriter out = new PrintWriter(writer);
    out.println("public class HelloWorld {");
    out.println("  public static void main(String args[]) {");
    out.println("    System.out.println(\"Hello, World\");");
    out.println("  }");
    out.println("}");
    out.close();
    JavaFileObject file =
      new JavaSourceFromString("HelloWorld", writer.toString());
    Iterable<? extends JavaFileObject> compilationUnits =
	    Arrays.asList(file);
    CompilationTask task = compiler.getTask(
        null, null, diagnostics, null, null, compilationUnits);
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
    System.out.println("Success: " + success);
    if (success) {
      try {
        System.out.println("-----Output-----");
        Class.forName("HelloWorld").getDeclaredMethod("main",
          new Class[] {String[].class}).invoke(null, new Object[] {null});
        System.out.println("-----Output-----");
      } catch (ClassNotFoundException e) {
        System.err.println("Class not found: " + e);
      } catch (NoSuchMethodException e) {
        System.err.println("No such method: " + e);
      } catch (IllegalAccessException e) {
        System.err.println("Illegal access: " + e);
      } catch (InvocationTargetException e) {
        System.err.println("Invocation target: " + e);
      }
    }
  }
}
